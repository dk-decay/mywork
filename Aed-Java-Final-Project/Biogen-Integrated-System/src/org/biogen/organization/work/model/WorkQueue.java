/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.work.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class WorkQueue {

    private List<WorkRequest> workRequestList;

    public WorkQueue() {
        this.workRequestList = new ArrayList<>();

    }

    public List<WorkRequest> getWorkRequestList() {
        return workRequestList;
    }

    public void setWorkRequestList(List<WorkRequest> workRequestList) {
        this.workRequestList = workRequestList;
    }

    public WorkRequest addWorkRequest() {

        WorkRequest work = new WorkRequest();
        this.workRequestList.add(work);
        return work;

    }

}
