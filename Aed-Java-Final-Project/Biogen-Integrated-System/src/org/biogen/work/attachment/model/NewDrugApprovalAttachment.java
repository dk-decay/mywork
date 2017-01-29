/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.work.attachment.model;

import org.biogen.business.drug.model.Drug;

/**
 *
 * @author deveshkandpal
 */
public class NewDrugApprovalAttachment extends Attachment {

    public NewDrugApprovalAttachment(AttachmentType type) {

        setAttachmentType(type);

    }

    private Drug drug;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

}
