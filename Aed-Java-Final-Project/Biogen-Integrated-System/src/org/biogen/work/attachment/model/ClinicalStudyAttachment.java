/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.work.attachment.model;

import org.biogen.business.medicare.model.ClinicalStudy;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalStudyAttachment extends Attachment {

    private ClinicalStudy clinicalStudy;
    private int initialDosage;
    private int initialParticipatingPatients;

    public ClinicalStudyAttachment(AttachmentType type) {

        setAttachmentType(type);
    }

    public ClinicalStudy getClinicalStudy() {
        return clinicalStudy;
    }

    public void setClinicalStudy(ClinicalStudy clinicalStudy) {
        this.clinicalStudy = clinicalStudy;
    }

    public int getInitialDosage() {
        return initialDosage;
    }

    public void setInitialDosage(int initialDosage) {
        this.initialDosage = initialDosage;
    }

    public int getInitialParticipatingPatients() {
        return initialParticipatingPatients;
    }

    public void setInitialParticipatingPatients(int initialParticipatingPatients) {
        this.initialParticipatingPatients = initialParticipatingPatients;
    }

    @Override
    public String toString() {
        return clinicalStudy.toString();
    }

}
