/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.network.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class OperationalRegionDirectory {

    private List<OperationalRegion> operationalRegionList;

    public OperationalRegionDirectory() {
        this.operationalRegionList = new ArrayList<>();

    }

    public List<OperationalRegion> getOperationalRegionList() {
        return operationalRegionList;
    }

    public void setOperationalRegionList(List<OperationalRegion> operationalRegionList) {
        this.operationalRegionList = operationalRegionList;
    }

    public OperationalRegion addOperationalRegion(OperationalRegionType type) {
        OperationalRegion region = new OperationalRegion(type);
        this.operationalRegionList.add(region);
        return region;

    }

}
