/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.db4oUtil;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

/**
 *
 * @author deveshkandpal
 */
public class TestLogic {
    
    
    public static void main(String[] args) throws JsonMappingException, IOException {
        
        MasterConfiguration config = MasterConfiguration.getInstance();
        config.loadData();
    }
}
