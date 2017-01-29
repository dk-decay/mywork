/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.admin.view;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JPanel;
import org.biogen.business.db4oUtil.MasterConfiguration;
import org.biogen.business.drug.model.Drug;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.enterprise.model.MedicareBusiness;
import org.biogen.business.medicare.model.ClinicalPatient;
import org.biogen.business.medicare.model.ClinicalStudy;
import org.biogen.business.medicare.model.ClinicalTrial;
import org.biogen.business.medicare.model.Disease;
import org.biogen.business.medicare.model.LifestyleType;
import org.biogen.business.medicare.model.SocialHabits;
import org.biogen.business.medicare.model.TrialPhaseType;
import org.biogen.business.medicare.model.TrialResult;
import org.biogen.business.medicare.model.TrialState;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.network.model.OperationalRegion;
import org.biogen.network.model.OperationalRegionType;
import org.biogen.organization.model.Hospital;
import org.biogen.organization.work.model.WorkRequest;
import org.biogen.user.model.BusinessUser;
import org.biogen.user.model.Ethinicity;
import org.biogen.user.model.PatientUser;
import org.biogen.user.model.RoleType;
import org.biogen.user.model.Sex;
import org.biogen.user.model.UserType;
import org.biogen.work.attachment.model.ClinicalStudyAttachment;

/**
 *
 * @author deveshkandpal
 */
public class SimulatePhase1ClinicalTrial {

    private Ecosystem system;
    private JPanel container;
    //private BusinessUser user;
    private MedicareBusiness enterprise;
    private OperationalRegion network;
    private Hospital organization;
    //  private WorkRequest workRequest;
    private ClinicalStudy clinicalStudy;
    private MasterConfiguration config;

    public SimulatePhase1ClinicalTrial(
            Ecosystem system,
            JPanel container,
            //BusinessUser user,
            MedicareBusiness enterprise,
            OperationalRegion network,
            Hospital organization,
            ClinicalStudy clinicalStudy
    ) {
        this.system = system;
        this.container = container;
        //this.user = user;
        this.enterprise = enterprise;
        this.network = network;
        this.organization = organization;
        this.clinicalStudy = clinicalStudy;
        this.config = MasterConfiguration.getInstance();

    }

    public void startClinicalTrialPhase1() {
        try {
            this.config.loadData();
        } catch (JsonMappingException ex) {
            Logger.getLogger(SimulatePhase1ClinicalTrial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimulatePhase1ClinicalTrial.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClinicalTrial ct = clinicalStudy.getClinicalTrialDirectory().addNewClinicalTrial(organization.getOrgId());
        organization.getClinicalTrialList().add(ct);
        ct.setPhaseType(TrialPhaseType.PHASE1);
        List<ClinicalPatient> clinicalPatientList = createPatientsAndUserProfile(ct);
        List<Map<String, Integer>> result = calculateScorePerFactorPerPatient(clinicalPatientList);
        checkResult(result, ct);

    }

// no of patients with success rate > 30%
    // divided by the total patients, then in that case this clinical trial was a success
    public boolean checkResult(List<Map<String, Integer>> result, ClinicalTrial ct) {

        int nofOfPeoplePassed = result.stream()
                .filter(f -> f.get("successRate") > 50)
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

    public List<ClinicalPatient> createPatientsAndUserProfile(ClinicalTrial ct) {

        return IntStream.range(0, clinicalStudy.getParticipatingCandidates()
        )
                .mapToObj(a -> {

                    PatientUser patientUser = (PatientUser) this.system.getUserDirectory()
                            .addUser(UserType.PU,
                                    null,
                                    null,
                                    null, null);

                    patientUser.setEthinicity(getEthinicity());
                    patientUser.setFirstname("FirstName" + a);
                    patientUser.setLastname("Lastname" + a);
                    Sex[] sexArr = {Sex.FEMALE, Sex.MALE};
                    patientUser.setSex(sexArr[getRandom(sexArr)]);
                    // create corresponding patient

                    ClinicalPatient clinicalPatient = this.system.getPatientDirectory()
                            .addNewClinicalPatient(organization.getOrgId(),
                                    patientUser);
                    clinicalPatient.setDisease(clinicalStudy.getDrug().getDisease());
                    // use random
                    Integer[] ageArr = new Integer[]{24, 32, 42, 48, 55, 67};
                    clinicalPatient.setAge(ageArr[getRandom(ageArr)]);
                    setSocialHabits(clinicalPatient);
                    clinicalPatient.getClinicalTrialResultDirectory()
                            .setClinicalStudyId(clinicalStudy.getClinicalStudyId());

                    this.organization.getPatientList().add(clinicalPatient);
                    this.enterprise.getPatientList().add(clinicalPatient);
                    ct.getClinicalTrialPatientList().add(clinicalPatient);
                    return clinicalPatient;
                }).collect(Collectors.toList());

    }

    public List<Map<String, Integer>> calculateScorePerFactorPerPatient(List<ClinicalPatient> clinicalPatientList) {

        return IntStream.range(0, clinicalPatientList.size())
                .mapToObj(i -> {

                    ClinicalPatient clinicalPatient = clinicalPatientList.get(i);
                    PatientUser patientUser = (PatientUser) clinicalPatient.getUser();

                    Map<String, Integer> obj = new HashMap<>();
                    Ethinicity ethinicity = patientUser.getEthinicity();
                    Sex sex = patientUser.getSex();
                    Disease disease = clinicalPatient.getDisease();
                    Drug drug = clinicalStudy.getDrug();
                    int age = clinicalPatient.getAge();
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

                    System.out.println("Ethincity ***** " + ethinicity.toString());
                    
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
                    int successRate = computeScore(obj);
                    TrialResult tr = clinicalPatient.getClinicalTrialResultDirectory().getTrialResultDirectory()
                            .addTrialResult(clinicalStudy.getClinicalStudyId());
                    tr.setScoreMap(obj);
                    tr.setScore(successRate);
                    tr.setPhaseType(TrialPhaseType.PHASE1);

                    return obj;

                }).collect(Collectors.toList());

    }

    public int computeScore(Map<String, Integer> scoreMap) {
        int averageTotalScore = (int) scoreMap.entrySet()
                .stream()
                .mapToInt(m -> m.getValue())
                .average().getAsDouble();

        System.out.println("config : " + scoreMap);
        int successRate = config.getScoreChart()
                .ceilingEntry(averageTotalScore).getValue();

        scoreMap.put("averageTotalScore", averageTotalScore);
        scoreMap.put("successRate", successRate);

        return successRate;

    }

    public void getScorePerGene(Disease disease, Map<String, Integer> obj) {
        Integer[] geneScore = new Integer[]{1, 2, 3, 4, 5, 6};
        disease.getAffectedGenes()
                .stream()
                .forEach(gene -> {
                    obj.put(gene, geneScore[getRandom(geneScore)]);
                });
    }

    public void getScorePerCompound(Drug drug, Map<String, Integer> obj) {
        Integer[] compoundScore = new Integer[]{4, 3, 2, 1, 0, -1, -2, -3, -4};
        drug.getDrugCompositionCatalog().getDrugCompositionList()
                .stream()
                .forEach(dc -> {
                    obj.put(dc.getCompound().toString(),
                            compoundScore[getRandom(compoundScore)]);
                });
    }

    public SocialHabits setSocialHabits(ClinicalPatient clinicalPatient) {
        SocialHabits socialHabits = clinicalPatient.addSocialHabits();
        Boolean[] alcoholSmokeArr = {true, false};
        LifestyleType[] lsArr = {LifestyleType.ACTIVE, LifestyleType.SEDENTARY};
        socialHabits.setAlcoholic(alcoholSmokeArr[getRandom(alcoholSmokeArr)]);
        socialHabits.setLifestyleType(lsArr[getRandom(lsArr)]);
        socialHabits.setSmoke(alcoholSmokeArr[getRandom(alcoholSmokeArr)]);

        return socialHabits;
    }

    public Ethinicity getEthinicity() {
        Map<OperationalRegionType, Ethinicity[]> values = new HashMap<OperationalRegionType, Ethinicity[]>() {
            {

                put(OperationalRegionType.ASIA, new Ethinicity[]{Ethinicity.ASIAN});
                put(OperationalRegionType.EUROPE, new Ethinicity[]{Ethinicity.WHITE});
                put(OperationalRegionType.NORTH_AMERICA, new Ethinicity[]{Ethinicity.NATIVE_AMERICAN, Ethinicity.AFRICAN_AMERICAN});
                put(OperationalRegionType.SOUTH_AMERICA, new Ethinicity[]{Ethinicity.LATINO, Ethinicity.HISPANIC});
            }

        };

        return values.get(network.getType())[getRandom(values.get(network.getType()))];
    }

    public int getRandom(Object[] array) {
        return new Random().nextInt(array.length);

    }
}
