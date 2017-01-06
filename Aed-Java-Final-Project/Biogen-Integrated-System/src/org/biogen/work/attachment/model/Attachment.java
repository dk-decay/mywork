/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.work.attachment.model;

/**
 *
 * @author deveshkandpal
 */
public abstract class Attachment {

    private int workRequestId;
    private AttachmentType attachmentType;

    public int getWorkRequestId() {
        return workRequestId;
    }

    public void setWorkRequestId(int workRequestId) {
        this.workRequestId = workRequestId;
    }

    public AttachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

}
