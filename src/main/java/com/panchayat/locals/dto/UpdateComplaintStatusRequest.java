package com.panchayat.locals.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateComplaintStatusRequest {

    @NotBlank(message = "Status cannot be empty")
    private String status; // PENDING, IN_PROGRESS, RESOLVED, REJECTED

    public UpdateComplaintStatusRequest() {
    }

    public UpdateComplaintStatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

