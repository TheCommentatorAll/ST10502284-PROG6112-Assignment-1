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
        System.out.println("10 Test Patients Loaded Successfully.");
    }

    /*
     * register a patient to the arrayList
     * @param patient : Patient
     * @return printLn          message telling the user the patient was registered successfully
     */
    public void registerPatient(Patient patient) {

        patientList.add(patient);

        System.out.println("Patient registered Successfully!");
    }

    /*
     * Search for a patient by patientID
     * @param patientID : String    
     * @return p                    will return all attributes of the patient class
     */
    public Patient searchPatient(String patientID) {

        for (Patient p : patientList) {

            if (p.getPatientID().equalsIgnoreCase(patientID)) {

                return p;
            }
        }

        return null;
    }

    /*
     * Update patient details
     * @param patientID : String
     * @return boolean          return true if a successful update occurs, false if not
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
     * @return printLn              message if arrayList is empty, Patient toString if not
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

    /*
     * Deletes patient from arrayList
     * @param patientID : String
     * @return boolean
     */
    public boolean deletePatient(String patientID) {

        Patient patient = searchPatient(patientID);

        if (patient != null) {
            patientList.remove(patient);
            return true;
        }

        return false;
    }
}
