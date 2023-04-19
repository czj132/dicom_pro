package com.zjut.Dicom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@ToString
public class ExpertEvaluation {
    private Integer id;
    private Integer projectId;
    private Integer patientId;
    private Integer studyId;
    private Integer expertId;
    private Byte expertType;
    private Date evaluateTime;
    private Byte status;
    private Byte validStatus;
    private Byte targetFocus;
    private Byte nonTargetFocus;
    private Byte newFocus;
    private Byte supplementary;
    private String report;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getStudyId() {
        return studyId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Byte getExpertType() {
        return expertType;
    }

    public void setExpertType(Byte expertType) {
        this.expertType = expertType;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Byte validStatus) {
        this.validStatus = validStatus;
    }

    public Byte getTargetFocus() {
        return targetFocus;
    }

    public void setTargetFocus(Byte targetFocus) {
        this.targetFocus = targetFocus;
    }

    public Byte getNonTargetFocus() {
        return nonTargetFocus;
    }

    public void setNonTargetFocus(Byte nonTargetFocus) {
        this.nonTargetFocus = nonTargetFocus;
    }

    public Byte getNewFocus() {
        return newFocus;
    }

    public void setNewFocus(Byte newFocus) {
        this.newFocus = newFocus;
    }

    public Byte getSupplementary() {
        return supplementary;
    }

    public void setSupplementary(Byte supplementary) {
        this.supplementary = supplementary;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report == null ? null : report.trim();
    }

    public ExpertEvaluation(Integer projectId, Integer patientId, Integer studyId, Integer expertId, Byte expertType, Byte status) {
        this.projectId = projectId;
        this.patientId = patientId;
        this.studyId = studyId;
        this.expertId = expertId;
        this.expertType = expertType;
        this.status = status;
    }
}
