package com.medicare.services;

import java.util.ArrayList;
import java.util.List;

import com.medicare.model.Patient;

public class PatientManager {

    private List<Patient> patientList;

    public PatientManager() {
        this.patientList = new ArrayList<>();
    }

    //temporary patient data entry for testing
   public void loadTestData() {
    patientList.add(new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", "Outpatient"));
    patientList.add(new Patient("P002", "Jane", "Smith", 30, "Female", "Appendicitis", "Emergency"));
    patientList.add(new Patient("P003", "Michael", "Johnson", 62, "Male", "Pneumonia", "Inpatient"));
    patientList.add(new Patient("P004", "Emily", "Davis", 25, "Female", "Migraine", "Outpatient"));
    patientList.add(new Patient("P005", "Robert", "Brown", 78, "Male", "Heart Failure", "Inpatient"));
    patientList.add(new Patient("P006", "Sarah", "Wilson", 40, "Female", "Fractured Arm", "Emergency"));
    patientList.add(new Patient("P007", "David", "Lee", 55, "Male", "Type 2 Diabetes", "Outpatient"));
    patientList.add(new Patient("P008", "Laura", "Garcia", 29, "Female", "Severe Asthma", "Inpatient"));
    patientList.add(new Patient("P009", "James", "Martinez", 12, "Male", "Tonsillitis", "Outpatient"));
    patientList.add(new Patient("P010", "Maria", "Rodriguez", 50, "Female", "Covid-19", "Inpatient"));
    patientList.add(new Patient("P011", "William", "Taylor", 34, "Male", "Acute Pancreatitis", "Inpatient"));
    patientList.add(new Patient("P012", "Olivia", "Anderson", 22, "Female", "Concussion", "Emergency"));
    patientList.add(new Patient("P013", "Thomas", "Moore", 67, "Male", "Chronic Bronchitis", "Inpatient"));
    patientList.add(new Patient("P014", "Sophia", "Jackson", 19, "Female", "Urinary Tract Infection", "Outpatient"));
    patientList.add(new Patient("P015", "Benjamin", "White", 81, "Male", "Hip Fracture", "Inpatient"));
    patientList.add(new Patient("P016", "Charlotte", "Harris", 36, "Female", "Gallstones", "Emergency"));
    patientList.add(new Patient("P017", "Alexander", "Martin", 48, "Male", "Gastrointestinal Bleeding", "Inpatient"));
    patientList.add(new Patient("P018", "M5ia", "Thompson", 28, "Female", "Allergic Reaction", "Outpatient"));
    patientList.add(new Patient("P019", "Henry", "Clark", 60, "Male", "Pulmonary Embolism", "Inpatient"));
    patientList.add(new Patient("P020", "Amelia", "Lewis", 53, "Female", "Severe Sepsis", "Inpatient"));
    
    System.out.println("20 Test Patients Loaded Successfully.");
}

    /**
     * register a patient to the arrayList
     * @param patient
     * @return              message telling the user the patient was registered successfully
     */
    public void registerPatient(Patient patient) {

        patientList.add(patient);

        System.out.println("Patient registered Successfully!");
    }

    /**
     * Search for a patient by patientID
     * @param patientID
     * @return              will return all attributes of the patient class
     */
    public Patient searchPatient(String patientID) {

        //using for-each loop to read every item in patientList sequentially, don't need to care for its position
        //read it as "for each patient (p) in (:) patientList"
        for (Patient p : patientList) {

            if (p.getPatientID().equalsIgnoreCase(patientID)) {

                return p;
            }
        }

        return null;
    }

    /**
     * Update patient details
     * @param patientID
     * @param updatedInfo
     * @return              return true if a successful update occurs, false if not
     */
    public boolean updatePatientDetails(String patientID, Patient updatedInfo) {

        Patient foundPatient = searchPatient(patientID);

        if (foundPatient != null) {

            foundPatient.setPatientID(updatedInfo.getPatientID());
            foundPatient.setFirstName(updatedInfo.getFirstName());
            foundPatient.setLastName(updatedInfo.getLastName());
            foundPatient.setAge(updatedInfo.getAge());
            foundPatient.setGender(updatedInfo.getGender());
            foundPatient.setPatientCategory(updatedInfo.getPatientCategory());
            foundPatient.setMedicalCondition(updatedInfo.getMedicalCondition());
            return true;
        }

        return false;
    }

    /*
     * Display a list of all patients
     * @param none
     * @return none
     * printLn             error message if arrayList is empty, Patient toString if not
     */
    public void displayAllPatients() {

        if (patientList.isEmpty()) {
            System.out.println("There are no patients currently registered. Please register a patient.");
        } else {
            for (Patient p : patientList) {

                System.out.println(p.toString());
            }
        }

    }

    /**
     * Deletes patient from arrayList
     * @param patientID
     * @return
     */
    public boolean deletePatient(String patientID) {

        Patient patient = searchPatient(patientID);

        if (patient != null) {
            patientList.remove(patient);
            return true;
        }

        return false;
    }

    /**
     * 
     */
    public void listIntpatients(){

        System.out.println("\n--- List of Inpatients ---");
        boolean foundInpatient = false;

        for(Patient p : patientList){

            if("Inpatient".equalsIgnoreCase(p.getPatientCategory())){

                System.out.println("{ID: " + p.getPatientID() + "} Name: " + p.getFirstName() + " " + p.getLastName());
                foundInpatient = true;
            }
        }

        if(!foundInpatient){
            System.out.println("[!] No Inpatients in Patient Registry.");
        }
    }

    /**
     * @return formatted String
     */
    public String generateNextPatientId(){

        int nextNumber = patientList.size()+1;
        return String.format("P%03d", nextNumber);
    }

}
