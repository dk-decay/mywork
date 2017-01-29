/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.admin.view;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.biogen.business.db4oUtil.MasterConfiguration;
import org.biogen.business.drug.model.Drug;
import org.biogen.business.enterprise.model.MedicareBusiness;
import org.biogen.business.medicare.model.ClinicalPatient;
import org.biogen.business.medicare.model.ClinicalStudy;
import org.biogen.business.medicare.model.ClinicalTrial;
import org.biogen.business.medicare.model.Disease;
import org.biogen.business.medicare.model.Patient;
import org.biogen.business.medicare.model.SocialHabits;
import org.biogen.business.medicare.model.TrialPhaseType;
import org.biogen.business.medicare.model.TrialResult;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.user.model.Ethinicity;
import org.biogen.user.model.PatientUser;
import org.biogen.user.model.Sex;

/**
 *
 * @author deveshkandpal
 */
public class SimulatePhase2Trial {

    private List<ClinicalPatient> patientList;
    private Ecosystem system;
    private ClinicalStudy clinicalStudy;
    private MedicareBusiness enterprise;
    private MasterConfiguration config;

    SimulatePhase2Trial(List<ClinicalPatient> patientList,
            Ecosystem system,
            ClinicalStudy clinicalStudy,
            MedicareBusiness enterprise) {
        this.patientList = patientList;
        this.system = system;
        this.enterprise = enterprise;
        this.clinicalStudy = clinicalStudy;
        this.config = MasterConfiguration.getInstance();
    }

    public void simlulatePhase2Trial() {

        try {
            this.config.loadData();
        } catch (JsonMappingException ex) {
            Logger.getLogger(SimulatePhase1ClinicalTrial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimulatePhase1ClinicalTrial.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClinicalTrial ct = clinicalStudy.getClinicalTrialDirectory()
                .addNewClinicalTrial(this.enterprise.getEnterpriseId());
        updateClinicalTrialWithPatientList(ct);
        ct.setPhaseType(TrialPhaseType.PHASE2);
        List<Map<String, Integer>> result = calculateScorePerFactorPerPatient(ct.getClinicalTrialPatientList());
        checkResult(result, ct);

    }

    public boolean checkResult(List<Map<String, Integer>> result, ClinicalTrial ct) {

        int nofOfPeoplePassed = result.stream()
                .filter(f -> f.get("successRate") > 70)
                .collect(Collectors
                        .toList()).size() * 100;

        int totalPeople = result.size();
        System.out.println("totalPeople :" + totalPeople);
        double divisionScore = nofOfPeoplePassed / totalPeople;
        System.out.println("nofOfPeoplePassed :" + nofOfPeoplePassed);
        System.out.println("divisionScore :" + divisionScore);
        boolean val = (divisionScore) > 50;
        System.out.println("final outcome of phase1 :" + val);
        ct.setResult(val);
        ct.setAverageScore(divisionScore);
        return val;

    }

    public void updateClinicalTrialWithPatientList(ClinicalTrial ct) {

        IntStream.range(0, this.patientList.size())
                .forEach(a -> {
                    ClinicalPatient patient = this.patientList.get(a);
                    if (patient.getClinicalTrialResultDirectory().getClinicalStudyId()
                            == clinicalStudy.getClinicalStudyId()) {
                        ct.getClinicalTrialPatientList().add(patient);

                    }

                });
    }

    public List<Map<String, Integer>> calculateScorePerFactorPerPatient(List<Patient> clinicalPatientList) {

        return IntStream.range(0, clinicalPatientList.size())
                .mapToObj(i -> {
                    Map<String, Integer> obj = new HashMap<>();
                    ClinicalPatient clinicalPatient = (ClinicalPatient) clinicalPatientList.get(i);

                    TrialResult phase1Result = clinicalPatient.getClinicalTrialResultDirectory()
                            .getTrialResultDirectory()
                            .getTrialResultList()
                            .stream()
                            .filter(ctr -> ctr.getPhaseType() == TrialPhaseType.PHASE1)
                            .findFirst()
                            .orElse(null);

                    System.out.println("phase1Result : " + phase1Result);

                    if (phase1Result.getScore() > 30) {

                        PatientUser patientUser = (PatientUser) clinicalPatient.getUser();

                        Ethinicity ethinicity = patientUser.getEthinicity();
                        Sex sex = patientUser.getSex();
                        Disease disease = clinicalPatient.getDisease();
                        Drug drug = clinicalStudy.getDrug();

                        int age = clinicalPatient.getAge() + 1;

                        SocialHabits socialHabits = clinicalPatient.getSocialHabits();
                        String alcoholicVal = clinicalPatient
                                .getSocialHabits()
                                .isAlcoholic() ? "ALCOHOLIC" : "NONALCOHOLIC";

                        String smokingVal = clinicalPatient
                                .getSocialHabits()
                                .isSmoke() ? "SMOKER" : "NONSMOKER";

                        String ls = clinicalPatient.getSocialHabits().getLifestyleType().toString();

                        int ageScore = config
                                .getAgeChart()
                                .entrySet()
                                .stream()
                                .filter(map -> map.getKey()
                                .equalsIgnoreCase(sex.toString()))
                                .map(m -> m.getValue().ceilingEntry(
                                age))
                                .findFirst()
                                .get()
                                .getValue();

                        obj.put("age", ageScore);

                        int lifestyleScore = config.getLifestylChart()
                                .entrySet()
                                .stream()
                                .filter(map -> map.getKey().equalsIgnoreCase(sex.toString()))
                                .map(m -> m.getValue().ceilingEntry(age).getValue().get(socialHabits.getLifestyleType().toString()))
                                .findFirst()
                                .get();

                        obj.put("lifestyle", lifestyleScore);

                        int alcoholScore = config.getAlcoholChart()
                                .entrySet()
                                .stream()
                                .filter(map -> map.getKey().equalsIgnoreCase(sex.toString()))
                                .map(m -> m.getValue().ceilingEntry(age).getValue().get(alcoholicVal))
                                .findFirst()
                                .get();

                        obj.put("alcohol", alcoholScore);

                        int smokingScore = config.getSmokingChart()
                                .entrySet()
                                .stream()
                                .filter(map -> map.getKey().equalsIgnoreCase(sex.toString()))
                                .map(m -> m.getValue().ceilingEntry(age).getValue().get(smokingVal))
                                .findFirst()
                                .get();

                        obj.put("smoking", smokingScore);

                        int ethinicityScore = config.getEthinicityChart()
                                .entrySet()
                                .stream()
                                .filter(map -> map.getKey().equalsIgnoreCase(sex.toString()))
                                .map(mapper -> mapper.getValue().get(ethinicity.toString()))
                                .findFirst()
                                .get();

                        obj.put("ethinicity", ethinicityScore);
                        getScorePerGene(disease, obj);
                        getScorePerCompound(drug, obj);
                        int successRate = computeScore(obj, phase1Result.getScore());

                        TrialResult tr = clinicalPatient.getClinicalTrialResultDirectory().getTrialResultDirectory()
                                .addTrialResult(clinicalStudy.getClinicalStudyId());
                        tr.setScoreMap(obj);
                        tr.setScore(successRate);
                        tr.setPhaseType(TrialPhaseType.PHASE2);
                        System.out.println("Success rate in phase2 is : " + tr.getScore());

                    } else {
                        System.out.println("Phase1 trial failed " + phase1Result.getScore());
                    }

                    return obj;

                }).collect(Collectors.toList());

    }

    public int computeScore(Map<String, Integer> scoreMap, int phase1Score) {
        int averageTotalScore = (int) scoreMap.entrySet()
                .stream()
                .mapToInt(m -> m.getValue())
                .average().getAsDouble();

        int successRate = config.getScoreChart()
                .ceilingEntry(averageTotalScore).getValue();

        scoreMap.put("averageTotalScore", averageTotalScore);
        scoreMap.put("successRate", (successRate + phase1Score) / 2);

        return successRate;

    }

    public void getScorePerGene(Disease disease, Map<String, Integer> obj) {
        Integer[] geneScore = new Integer[]{-1, -2, -3, -4, -5, -6};
        disease.getAffectedGenes()
                .stream()
                .forEach(gene -> {
                    obj.put(gene, geneScore[getRandom(geneScore)]);
                });
    }

    public void getScorePerCompound(Drug drug, Map<String, Integer> obj) {
        Integer[] compoundScore = new Integer[]{4, 3, 2, 1, 0, -1, -2, -3, -4, -5, -6};
        drug.getDrugCompositionCatalog().getDrugCompositionList()
                .stream()
                .forEach(dc -> {
                    obj.put(dc.getCompound().toString(),
                            compoundScore[getRandom(compoundScore)]);
                });
    }

    public int getRandom(Object[] array) {
        return new Random().nextInt(array.length);

    }
}
