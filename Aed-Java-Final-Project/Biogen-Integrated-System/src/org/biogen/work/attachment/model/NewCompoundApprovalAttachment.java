/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.work.attachment.model;

import org.biogen.business.chem.model.Compound;

/**
 *
 * @author deveshkandpal
 */
public class NewCompoundApprovalAttachment extends Attachment {
    
    private Compound compound;
    
    public NewCompoundApprovalAttachment(AttachmentType type) {
        
        setAttachmentType(type);
        
    }

    public Compound getCompound() {
        return compound;
    }
    
    public void setCompound(Compound compound) {
        this.compound = compound;
    }
    
}
