package main.java.com.medicare.service;

import java.util.ArrayList;
import java.util.List;

import main.java.com.medicare.model.Patient;
import main.java.com.medicare.model.PatientCategory;

public class PatientManager {

    private List<Patient> patientList;
    private int nextIdNumber;

    public PatientManager() {
        this.patientList = new ArrayList<>();
        loadTestData();
        this.nextIdNumber = patientList.size() + 1;
    }

    //temporary patient data entry for testing
    //comment for testing, uncomment for program
    public void loadTestData() {
        patientList.add(new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT));
        patientList.add(new Patient("P002", "Jane", "Smith", 30, "Female", "Appendicitis", PatientCategory.EMERGENCY));
        patientList.add(new Patient("P003", "Michael", "Johnson", 62, "Male", "Pneumonia", PatientCategory.INPATIENT));
        patientList.add(new Patient("P004", "Emily", "Davis", 25, "Female", "Migraine", PatientCategory.OUTPATIENT));
        patientList.add(new Patient("P005", "Robert", "Brown", 78, "Male", "Heart Failure", PatientCategory.INPATIENT));
        patientList.add(new Patient("P006", "Sarah", "Wilson", 40, "Female", "Fractured Arm", PatientCategory.EMERGENCY));
        patientList.add(new Patient("P007", "David", "Lee", 55, "Male", "Type 2 Diabetes", PatientCategory.OUTPATIENT));
        patientList.add(new Patient("P008", "Laura", "Garcia", 29, "Female", "Severe Asthma", PatientCategory.INPATIENT));
        patientList.add(new Patient("P009", "James", "Martinez", 12, "Male", "Tonsillitis", PatientCategory.OUTPATIENT));
        patientList.add(new Patient("P010", "Maria", "Rodriguez", 50, "Female", "Covid-19", PatientCategory.INPATIENT));
        patientList.add(new Patient("P011", "William", "Taylor", 34, "Male", "Acute Pancreatitis", PatientCategory.INPATIENT));
        patientList.add(new Patient("P012", "Olivia", "Anderson", 22, "Female", "Concussion", PatientCategory.EMERGENCY));
        patientList.add(new Patient("P013", "Thomas", "Moore", 67, "Male", "Chronic Bronchitis", PatientCategory.INPATIENT));
        patientList.add(new Patient("P014", "Sophia", "Jackson", 19, "Female", "Urinary Tract Infection", PatientCategory.OUTPATIENT));
        patientList.add(new Patient("P015", "Benjamin", "White", 81, "Male", "Hip Fracture", PatientCategory.INPATIENT));
        patientList.add(new Patient("P016", "Charlotte", "Harris", 36, "Female", "Gallstones", PatientCategory.EMERGENCY));
        patientList.add(new Patient("P017", "Alexander", "Martin", 48, "Male", "Gastrointestinal Bleeding", PatientCategory.INPATIENT));
        patientList.add(new Patient("P018", "M5ia", "Thompson", 28, "Female", "Allergic Reaction", PatientCategory.OUTPATIENT));
        patientList.add(new Patient("P019", "Henry", "Clark", 60, "Male", "Pulmonary Embolism", PatientCategory.INPATIENT));
        patientList.add(new Patient("P020", "Amelia", "Lewis", 53, "Female", "Severe Sepsis", PatientCategory.INPATIENT));

        System.out.println("20 Test Patients Loaded Successfully.");
    }

    /**
     * register a patient to the arrayList
     *
     * @param patient
     * @return message telling the user the patient was registered successfully
     */
    public boolean registerPatient(Patient patient) {

        if(searchPatient(patient.getPatientID()) != null){
            System.out.println("[!] Registration failed: Patient ID " + patient.getPatientID() + " already exists.");
            return false;
        }

        patientList.add(patient);

        //increment nextIdNumber only when registerPatient is successful to prevent over incrementation on exit
        nextIdNumber++;

        System.out.println("\n[*] Patient " + patient.getPatientID() + " registered Successfully!");
        return true;
    }

    /**
     * Search for a patient by patientID
     *
     * @param patientID
     * @return will return all attributes of the patient class
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
     *
     * @param patientID
     * @param updatedInfo
     * @return return true if a successful update occurs, false if not
     */
    public boolean updatePatientDetails(String patientID, Patient updatedInfo) {

        Patient foundPatient = searchPatient(patientID);

        if (foundPatient != null) {

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
     * Deletes patient from arrayList will not reformat patient IDs on deletion
     * as each patient's ID is unique and assigned once in accordance with
     * proper primary key implementation
     *
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
    public void listIntpatients() {

        System.out.println("\n--- List of Inpatients ---");
        boolean foundInpatient = false;

        for (Patient p : patientList) {

            if (p.getPatientCategory() == PatientCategory.INPATIENT) {

                System.out.println("{ID: " + p.getPatientID() + "} Name: " + p.getFirstName() + " " + p.getLastName());
                foundInpatient = true;
            }
        }

        if (!foundInpatient) {
            System.out.println("\n[!] No Inpatients in Patient Registry.\n");
        }
    }

    /**
     * @return formatted String
     */
    public String generateNextPatientId() {

        String newId = String.format("P%03d", nextIdNumber).toUpperCase();
        return newId;
    }

    /**
     * @return
     */
    public List<Patient> getPatientList() {
        return patientList;
    }

    public void sortByPatientId() {

        int n = patientList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Patient p1 = patientList.get(j);
                Patient p2 = patientList.get(j + 1);

                if (p1.getPatientID().compareToIgnoreCase(p2.getPatientID()) > 0) {

                    patientList.set(j, p2);
                    patientList.set(j + 1, p1);
                }
            }

        }

        System.out.println("\n[*] Patients sorted by Patient ID.\n");
    }

    public void sortByAge() {

        int n = patientList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Patient p1 = patientList.get(j);
                Patient p2 = patientList.get(j + 1);

                if (p1.getAge() > p2.getAge()) {
                    patientList.set(j, p2);
                    patientList.set(j + 1, p1);
                }
            }
        }

        System.out.println("\n[*] Patients sorted by Patient Age.\n");
    }

    public void sortByCategory() {

        int n = patientList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Patient p1 = patientList.get(j);
                Patient p2 = patientList.get(j + 1);

                int rank1;

                if (p1.getPatientCategory() == PatientCategory.INPATIENT) {
                    rank1=1;
                }else if(p1.getPatientCategory() == PatientCategory.EMERGENCY){
                    rank1=2;
                }else{
                    rank1=3;
                }

                int rank2;

                if(p2.getPatientCategory() == PatientCategory.INPATIENT){
                    rank2=1;
                }else if(p2.getPatientCategory() == PatientCategory.EMERGENCY){
                    rank2=2;
                }else{
                    rank2=3;
                }

                if(rank1>rank2){

                    patientList.set(j, p2);
                    patientList.set(j+1, p1);
                }
            }
        }

        System.out.println("\n[*] Patients sorted by Category.\n");
    }

    public void sortByLastName() {

        int n = patientList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Patient p1 = patientList.get(j);
                Patient p2 = patientList.get(j + 1);

                if (p1.getLastName().compareToIgnoreCase(p2.getLastName()) > 0) {

                    patientList.set(j, p2);
                    patientList.set(j + 1, p1);
                }
            }
        }

        System.out.println("\n[*] Patients sorted by Last Name.\n");
    }

}
