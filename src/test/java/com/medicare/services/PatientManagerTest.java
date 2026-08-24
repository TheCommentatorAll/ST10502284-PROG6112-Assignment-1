package test.java.com.medicare.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.medicare.model.Patient;
import main.java.com.medicare.model.PatientCategory;
import main.java.com.medicare.service.PatientManager;


public class PatientManagerTest {

    private PatientManager patientManager;

    @BeforeEach
    public void setUp() {
        patientManager = new PatientManager();
    }

    // 1. Register a patient
    @Test
    public void testRegisterPatient() {
        Patient p = new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT);
        assertTrue(patientManager.registerPatient(p));
        assertNotNull(patientManager.searchPatient("P001"));
    }

    // 2. Search for a patient
    @Test
    public void testSearchPatient() {
        Patient p = new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT);
        patientManager.registerPatient(p);

        Patient found = patientManager.searchPatient("P001");
        assertNotNull(found);
        assertEquals("John", found.getFirstName());
    }

    // 3. Update patient details
    @Test
    public void testUpdatePatientDetails() {
        Patient p = new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT);
        patientManager.registerPatient(p);

        Patient updatedData = new Patient("P001", "John", "Smith", 46, "Male", "Recovered", PatientCategory.OUTPATIENT);
        boolean updated = patientManager.updatePatientDetails("P001", updatedData);

        assertTrue(updated);
        assertEquals("Smith", patientManager.searchPatient("P001").getLastName());
    }

    // 4. Delete a patient
    @Test
    public void testDeletePatient() {
        Patient p = new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT);
        patientManager.registerPatient(p);

        assertTrue(patientManager.deletePatient("P001"));
        assertNull(patientManager.searchPatient("P001"));
    }

    // 7. Prevent duplicate Patient IDs
    @Test
    public void testPreventDuplicatePatientIds() {
        Patient p1 = new Patient("P001", "John", "Doe", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P001", "Jane", "Smith", 30, "Female", "Flu", PatientCategory.OUTPATIENT);

        patientManager.registerPatient(p1);
        boolean duplicateResult = patientManager.registerPatient(p2);

        assertFalse(duplicateResult, "Registration should fail when using an existing Patient ID.");
    }

    // 10. Sort patients by surname or Patient ID
    @Test
    public void testSortPatientsBySurname() {
        Patient p1 = new Patient("P002", "Jane", "Smith", 30, "Female", "Flu", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P001", "John", "Adams", 45, "Male", "Hypertension", PatientCategory.OUTPATIENT);

        patientManager.registerPatient(p1);
        patientManager.registerPatient(p2);

        patientManager.sortByLastName();

        assertEquals("Adams", patientManager.getPatientList().get(0).getLastName());
    }
}
