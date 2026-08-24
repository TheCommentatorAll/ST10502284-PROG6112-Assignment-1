package test.java.com.medicare.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.medicare.model.Bed;
import main.java.com.medicare.model.Inpatient;
import main.java.com.medicare.model.Patient;
import main.java.com.medicare.model.PatientCategory;
import main.java.com.medicare.service.BedManager;

import static org.junit.jupiter.api.Assertions.*;

public class BedManagerTest {
    private BedManager bedManager;
    private Inpatient inpatient1;
    private Inpatient inpatient2;

    @BeforeEach
    public void setUp() {
        bedManager = new BedManager();
        inpatient1 = new Inpatient("P001", "John", "Doe", 45, "Male", "Pneumonia", PatientCategory.INPATIENT, "Unassigned");
        inpatient2 = new Inpatient("P002", "Jane", "Smith", 30, "Female", "Flu", PatientCategory.INPATIENT, "Unassigned");
    }

    // 5. Allocate a bed
    @Test
    public void testAllocateBed() {
        boolean allocated = bedManager.allocateBeds(inpatient1);
        assertTrue(allocated);
    }

    // 6. Release a bed
    @Test
    public void testReleaseBed() {
        bedManager.allocateBeds(inpatient1);
        boolean released = bedManager.releaseBed("B01");

        assertTrue(released);
    }

    // 8. Prevent allocating an occupied bed
    @Test
    public void testPreventAllocatingOccupiedBed() {

        bedManager.allocateBeds(inpatient1);
        bedManager.allocateBeds(inpatient2);

        assertEquals("B02", inpatient2.getBedNumber());
    }

    // 9. Prevent bed allocation when all beds are occupied
    @Test
    public void testPreventBedAllocationWhenAllBedsOccupied() {
        // Fill all 20 ward beds
        for (int i = 1; i <= 20; i++) {
            String id = String.format("P%02d", i);
            Patient p = new Patient(id, "Patient" + i, "Test", 30, "Male", "Condition", PatientCategory.INPATIENT);
            bedManager.allocateBeds(p);
        }

        Patient extraPatient = new Patient("P999", "Extra", "Patient", 25, "Female", "Condition", PatientCategory.INPATIENT);
        boolean result = bedManager.allocateBeds(extraPatient);

        assertFalse(result, "Allocation should fail when ward reaches max capacity (20 beds).");
    }
}