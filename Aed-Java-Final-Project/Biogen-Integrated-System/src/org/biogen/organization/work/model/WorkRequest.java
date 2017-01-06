/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.work.model;

import org.biogen.work.attachment.model.Attachment;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.biogen.user.model.BusinessUser;
import org.biogen.work.attachment.model.AttachmentType;
import org.biogen.work.attachment.model.ClinicalStudyAttachment;
import org.biogen.work.attachment.model.InventoryUpdateAttachment;
import org.biogen.work.attachment.model.NewCompoundApprovalAttachment;
import org.biogen.work.attachment.model.NewDrugApprovalAttachment;

/**
 *
 * @author deveshkandpal
 */
public class WorkRequest {

    private BusinessUser sender;
    private BusinessUser receiver;
    private String comments;
    private Date startDate;
    private Date endDate;
    private WorkRequestStatus status;
    private static int id = 1;
    private int workRequestId;
    private Attachment attachment;

    public WorkRequest() {

        this.workRequestId = id++;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public int getWorkRequestId() {
        return workRequestId;
    }

    public void setWorkRequestId(int workRequestId) {
        this.workRequestId = workRequestId;
    }

    public BusinessUser getSender() {
        return sender;
    }

    public WorkRequestStatus getStatus() {
        return status;
    }

    public void setStatus(WorkRequestStatus status) {
        this.status = status;
    }

    public void setSender(BusinessUser sender) {
        this.sender = sender;
    }

    public BusinessUser getReceiver() {
        return receiver;
    }

    public void setReceiver(BusinessUser receiver) {
        this.receiver = receiver;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Attachment addAttachment(AttachmentType type) {
        Map<AttachmentType, Attachment> values = new HashMap<AttachmentType, Attachment>() {

            {
                put(AttachmentType.NewCompoundApprovalAttachment, new NewCompoundApprovalAttachment(type));
                put(AttachmentType.InventoryUpdateAttachment, new InventoryUpdateAttachment(type));
                put(AttachmentType.NewDrugApprovalAttachment, new NewDrugApprovalAttachment(type));
                put(AttachmentType.ClinicalStudyAttachment, new ClinicalStudyAttachment(type));
            }

        };
        this.attachment = values.get(type);
        return this.attachment;

    }

    @Override
    public String toString() {

        return this.sender.toString();
    }

}
