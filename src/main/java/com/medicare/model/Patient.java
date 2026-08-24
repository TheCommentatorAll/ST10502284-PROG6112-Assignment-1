package main.java.com.medicare.model;

public class Patient {

    private final String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory patientCategory;

    public Patient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition,
            PatientCategory patientCategory) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.patientCategory = patientCategory;
    }

    public String getPatientID() {
        return patientId;
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

    public PatientCategory getPatientCategory() {
        return patientCategory;
    }

    public void setPatientCategory(PatientCategory patientCategory){
        this.patientCategory = patientCategory;
    }

    public void displayDetails() {
        System.out.printf("| patientId :: %-5s | first-Name :: %-10s | last-Name :: %-15s | age :: %-4d | gender :: %-10s | condition :: %-25s | category :: %-10s |",
                patientId, firstName, lastName, age, gender, medicalCondition, patientCategory);
    }

    @Override
    public String toString() {
        return String.format(
                "| patientId :: %-5s | first-Name :: %-10s | last-Name :: %-10s | age :: %-4d | gender :: %-8s | condition :: %-25s | category :: %-10s |",
                patientId, firstName, lastName, age, gender, medicalCondition, patientCategory);
    }

}
