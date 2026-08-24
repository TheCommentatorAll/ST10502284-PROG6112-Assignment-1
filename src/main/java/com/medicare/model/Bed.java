package com.medicare.model;

public class Bed {

    private String bedNumber;
    private boolean isOccupied;
    private String assignedPatientId;

    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        this.isOccupied = false;
        this.assignedPatientId = null;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String getAssignedPatientId() {
        return assignedPatientId;
    }

    public void setAssignedPatientId(String assignedPatientId) {
        this.assignedPatientId = assignedPatientId;
    }

}
