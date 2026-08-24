package main.java.com.medicare.model;

public class Inpatient extends Patient {

    private String wardNumber;
    private String bedNumber;

    public Inpatient(String patientID, String firstName, String lastName, int age, String gender,
            String medicalCondition, PatientCategory patientCategory, String bedNumber) {
        super(patientID, firstName, lastName, age, gender, medicalCondition, patientCategory);
        this.wardNumber = "Ward 1";
        this.bedNumber = bedNumber;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    @Override
    public String toString() {
        super.toString();
        return String.format("  └─ Ward Number: %s | Bed Number: %s%n", wardNumber, bedNumber);
    }

}
