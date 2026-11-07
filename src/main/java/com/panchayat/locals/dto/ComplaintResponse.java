package com.panchayat.locals.dto;

import com.panchayat.locals.entity.Complaint;
import java.time.LocalDateTime;

public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String status;
    private String location;
    private LocalDateTime createdAt;
    private Long citizenId;
    private String citizenName;
    private String citizenMobile;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Long id, String title, String description, String category,
                             String status, String location, LocalDateTime createdAt,
                             Long citizenId, String citizenName, String citizenMobile) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.location = location;
        this.createdAt = createdAt;
        this.citizenId = citizenId;
        this.citizenName = citizenName;
        this.citizenMobile = citizenMobile;
    }

    public static ComplaintResponse fromEntity(Complaint complaint) {
        Long citizenId = null;
        String citizenName = null;
        String citizenMobile = null;

        if (complaint.getUser() != null) {
            citizenId = complaint.getUser().getId();
            citizenName = complaint.getUser().getName();
            citizenMobile = complaint.getUser().getMobile();
        }

        return new ComplaintResponse(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getCategory(),
                complaint.getStatus(),
                complaint.getLocation(),
                complaint.getCreatedAt(),
                citizenId,
                citizenName,
                citizenMobile
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getCitizenMobile() {
        return citizenMobile;
    }

    public void setCitizenMobile(String citizenMobile) {
        this.citizenMobile = citizenMobile;
    }
}

