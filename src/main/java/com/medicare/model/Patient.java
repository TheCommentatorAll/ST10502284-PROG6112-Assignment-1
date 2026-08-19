package com.medicare.model;

public class Patient {

    private String patientID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private String patientCategory;

    public Patient(String patientID, String firstName, String lastName, int age, String gender, String medicalCondition, String patientCategory) {

        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.patientCategory = patientCategory;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getPatientCategory() {
        return patientCategory;
    }

    public void setPatientCategory(String patientCategory) {
        this.patientCategory = patientCategory;
    }

    @Override
    public String toString() {
        return String.format("patientId=%-6s | firstName=%-15s | lastName=%-15s | age=%-4d | gender=%-10s | medicalCondition=%-20s | patientCategory=%-10s", patientID, firstName, lastName, age, gender, medicalCondition, patientCategory);
    }

}
