/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.db4oUtil;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class MasterConfiguration {

    private static MasterConfiguration instance;

    // charts
    private Map<String, NavigableMap<Integer, Integer>> ageChart;
    private Map<String, NavigableMap<Integer, Map<String, Integer>>> alcoholChart;
    private Map<String, NavigableMap<Integer, Map<String, Integer>>> smokingChart;
    private Map<String, NavigableMap<Integer, Map<String, Integer>>> lifestylChart;
    private Map<String, NavigableMap<String, Integer>> ethinicityChart;
    private NavigableMap<Integer, Integer> scoreChart;
    
    private NavigableMap<String, List<String>> organizationData;
    private NavigableMap<String, List<String>> diseaseData;

    private MasterConfiguration() {

    }

    public static MasterConfiguration getInstance() {
        if (instance == null) {
            instance = new MasterConfiguration();
        }
        return instance;

    }

    public void loadData() throws JsonParseException, JsonMappingException,
            IOException {

        ObjectMapper mapper = new ObjectMapper();

        this.ageChart = mapper
                .readValue(
                        new File(FIleConstants.AGE_CHART_LOCATION),
                        new TypeReference<Map<String, NavigableMap<Integer, Integer>>>() {
                });
        
        

        this.alcoholChart = mapper
                .readValue(
                        new File(FIleConstants.ALCOHOL_CHART_LOCATION),
                        new TypeReference<Map<String, NavigableMap<Integer, Map<String, Integer>>>>() {
                });
        
       

        this.ethinicityChart = mapper
                .readValue(
                        new File(FIleConstants.ETHINICITY_CHART_LOCATION),
                        new TypeReference<Map<String, NavigableMap<String, Integer>>>() {
                });
        
        System.out.print("**************ethinicityChart : "+ethinicityChart);
        

        
        this.lifestylChart = mapper
                .readValue(
                        new File(FIleConstants.LIFESTYLE_CHART_LOCATION),
                        new TypeReference<Map<String, NavigableMap<Integer, Map<String, Integer>>>>() {
                });

       
        
        this.smokingChart = mapper
                .readValue(
                        new File(FIleConstants.SMOKING_CHART_LOCATION),
                        new TypeReference<Map<String, NavigableMap<Integer, Map<String, Integer>>>>() {
                });
        
      

        this.diseaseData = mapper
                .readValue(
                        new File(FIleConstants.DISEASE_LOCATION),
                        new TypeReference<NavigableMap<String, List<String>>>() {
                });
        
        
        
        this.organizationData = mapper
                .readValue(
                        new File(FIleConstants.MASTER_DATA_LOCATION),
                        new TypeReference<NavigableMap<String, List<String>>>() {
                });
        
        this.scoreChart = mapper
                .readValue(
                        new File(FIleConstants.SCORE_CHART_LOCATION),
                        new TypeReference<NavigableMap<Integer, Integer>>() {
                });
        
       

    }

    public NavigableMap<String, List<String>> getOrganizationData() {
        return organizationData;
    }

    public void setOrganizationData(NavigableMap<String, List<String>> organizationData) {
        this.organizationData = organizationData;
    }

    public NavigableMap<String, List<String>> getDiseaseData() {
        return diseaseData;
    }

    public void setDiseaseData(NavigableMap<String, List<String>> diseaseData) {
        this.diseaseData = diseaseData;
    }
    
    
    

    public NavigableMap<Integer, Integer> getScoreChart() {
        return scoreChart;
    }

    public void setScoreChart(NavigableMap<Integer, Integer> scoreChart) {
        this.scoreChart = scoreChart;
    }

    public Map<String, NavigableMap<Integer, Integer>> getAgeChart() {
        return ageChart;
    }

    public void setAgeChart(Map<String, NavigableMap<Integer, Integer>> ageChart) {
        this.ageChart = ageChart;
    }

    public Map<String, NavigableMap<Integer, Map<String, Integer>>> getAlcoholChart() {
        return alcoholChart;
    }

    public void setAlcoholChart(Map<String, NavigableMap<Integer, Map<String, Integer>>> alcoholChart) {
        this.alcoholChart = alcoholChart;
    }

    public Map<String, NavigableMap<Integer, Map<String, Integer>>> getSmokingChart() {
        return smokingChart;
    }

    public void setSmokingChart(Map<String, NavigableMap<Integer, Map<String, Integer>>> smokingChart) {
        this.smokingChart = smokingChart;
    }

    public Map<String, NavigableMap<Integer, Map<String, Integer>>> getLifestylChart() {
        return lifestylChart;
    }

    public void setLifestylChart(Map<String, NavigableMap<Integer, Map<String, Integer>>> lifestylChart) {
        this.lifestylChart = lifestylChart;
    }

    public Map<String, NavigableMap<String, Integer>> getEthinicityChart() {
        return ethinicityChart;
    }

    public void setEthinicityChart(Map<String, NavigableMap<String, Integer>> ethinicityChart) {
        this.ethinicityChart = ethinicityChart;
    }

}
