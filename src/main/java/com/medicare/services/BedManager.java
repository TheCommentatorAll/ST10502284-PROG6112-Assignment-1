package com.medicare.services;

import com.medicare.model.Bed;
import com.medicare.model.Patient;
import com.medicare.model.PatientCategory;

/**
 * BedManager
 */
public class BedManager {

    private Bed[][] wardLayout;

    /**
     *
     */
    public BedManager() {

        wardLayout = new Bed[4][5];
        initialiseBeds();
    }

    /**
     *
     */
    public void initialiseBeds() {
        int bedCounter = 1;

        for (int i = 0; i < wardLayout.length; i++) {

            for (int j = 0; j < wardLayout[i].length; j++) {

                String bedID = String.format("B%02d", bedCounter);

                wardLayout[i][j] = new Bed(bedID);

                bedCounter++;
            }

        }

    }

    /**
     * @param patient
     * @return
     */
    public boolean allocateBeds(Patient patient) {

        if (patient == null || patient.getPatientCategory() != PatientCategory.INPATIENT) {
            System.out.println("Allocation failed: Patient must be categorised as 'Inpatient'.");

            return false;
        }

        for (int i = 0; i < wardLayout.length; i++) {
            for (int j = 0; j < wardLayout[i].length; j++) {

                Bed currentBed = wardLayout[i][j];

                if (!currentBed.isOccupied()) {
                    currentBed.setOccupied(true);
                    currentBed.setAssignedPatientId(patient.getPatientID());
                    System.out.println("Success: " + patient.getFirstName() + " assigned to bed {" + currentBed.getBedNumber() + "}");
                    return true;
                }
            }
        }

        System.out.println("Allocation Failed: Ward is Full. No available beds.");
        return false;
    }

    /**
     * @param bedNumber
     * @return
     */
    public boolean releaseBed(String bedNumber) {

        for (int i = 0; i < wardLayout.length; i++) {
            for (int j = 0; j < wardLayout[i].length; j++) {

                Bed currentBed = wardLayout[i][j];

                if (currentBed.getBedNumber().equalsIgnoreCase(bedNumber)) {

                    if (!currentBed.isOccupied()) {
                        System.out.println("Release Bed Failed: " + currentBed.getBedNumber() + " is already empty.");
                    }

                    currentBed.setAssignedPatientId(null);
                    currentBed.setOccupied(false);
                    System.out.println("Successfully cleared bed " + currentBed.getBedNumber() + ".");
                    return true;
                }
            }

        }

        System.out.println("Release Bed Failed: " + bedNumber + " does not exist");
        return false;
    }

    /**
     *
     */
    public void displayCompleteLayout() {

        for (int i = 0; i < wardLayout.length; i++) {
            for (int j = 0; j < wardLayout[i].length; j++) {

                Bed currentBed = wardLayout[i][j];

                if (currentBed.isOccupied()) {
                    System.out.print("[" + currentBed.getBedNumber() + " - " + currentBed.getAssignedPatientId() + "]");
                } else {
                    System.out.print("[" + currentBed.getBedNumber() + " - Empty]");
                }
            }

            System.out.println();
        }

    }

    /**
     *
     */
    public void displayAvailableBeds() {

        System.out.println("--- Available Beds ---");
        boolean hasAvailable = false;
        for (int i = 0; i < wardLayout.length; i++) {
            for (int j = 0; j < wardLayout[i].length; j++) {

                Bed currentBed = wardLayout[i][j];

                if (!currentBed.isOccupied()) {

                    System.out.print("[" + currentBed.getBedNumber() + "]");
                    hasAvailable = true;
                }
            }

            System.out.println();
        }
        if (!hasAvailable) {
            System.out.println("No Avaialble beds found (Ward is Full)");
        }

    }

    /**
     *
     */
    public void displayOccupiedBeds() {

        System.out.println("--- Occupied Beds ---");
        boolean hasOccupied = false;
        for (int i = 0; i < wardLayout.length; i++) {
            for (int j = 0; j < wardLayout[i].length; j++) {

                Bed currentBed = wardLayout[i][j];

                if (currentBed.isOccupied()) {

                    System.out.print("[" + currentBed.getBedNumber() + " - " + currentBed.getAssignedPatientId() + "]");
                    hasOccupied = true;
                }
            }

            System.out.println();
        }

        if (!hasOccupied) {
            System.out.println("No occupied beds at the moment.");
        }

    }

    public Bed[][] getWardLayout() {
        return wardLayout;
    }

    public int getTotalBedCount(){

        int total=0;

        for (int i = 0; i < wardLayout.length; i++){
            total += wardLayout[i].length;
        }

        return total;
    }

    public int getOccupiedBedCount(){

        int count = 0;

        for (int i = 0; i < wardLayout.length; i++) {
            for(int j = 0; j < wardLayout[i].length; j++){

                Bed bed = wardLayout[i][j];

                if(bed != null && bed.isOccupied()){
                    count++;
                }
            }
            
        }

        return count;
    }

    public int getAvailableBedCount(){

        return getTotalBedCount() - getOccupiedBedCount();
    }

    public double getOccupancyPercentage(){

        int total = getTotalBedCount();

        if(total==0)
            return 0.0;

        return ((double) getOccupiedBedCount() / total) * 100.0;
    }

}
