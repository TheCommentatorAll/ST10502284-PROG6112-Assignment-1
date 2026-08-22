package com.medicare.services;

import java.util.List;

import com.medicare.model.Bed;
import com.medicare.model.Patient;

public class ReportManager {

    private BedManager bedManager;
    private PatientManager patientManager;

    public ReportManager(BedManager bedManager, PatientManager patientManager) {

        this.bedManager = bedManager;
        this.patientManager = patientManager;

    }

    public void displayAllPatientsReport() {

        System.out.println("\n--- Patients Report ---");

        List<Patient> patients = patientManager.getPatientList();
        if (patients.isEmpty()) {
            System.out.println("[!] No patients currently registered");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }

    }

    public void displayBedStatusReport(boolean status) {

        String statusLabel;

        if (status) {
            statusLabel = "Occupied";
        } else {
            statusLabel = "Available";
        }

        System.out.println("\n--- " + statusLabel.toUpperCase() + " beds ---");

        Bed[][] layout = bedManager.getWardLayout();
        int matchCount = 0;

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {

                Bed currentBed = layout[i][j];

                if (currentBed != null) {

                    if (status && currentBed.isOccupied()) {

                        System.out.printf("Bed: %-5s | Status: " + statusLabel.toUpperCase() + " | Patient ID: %s\n", currentBed.getBedNumber(), currentBed.getAssignedPatientId());
                        matchCount++;
                    } else if (!status && !currentBed.isOccupied()) {
                        System.out.printf("Bed: %-5s | Status: " + statusLabel.toUpperCase(), currentBed.getBedNumber());
                        matchCount++;
                    }
                }
            }
        }

        if (matchCount == 0) {
            if (status) {
                System.out.println("[!] No beds are currently Occupied");
            } else {
                System.out.println("[!] No beds are currently available (Ward is full)");
            }
        }
        System.out.println("Total " + statusLabel.toUpperCase() + ": " + matchCount);
    }

    public void displaySummaryStatistics() {

        System.out.println("---------------------------------");
        System.out.println("--- STATISTICS SUMMARY REPORT ---");
        System.out.println("---------------------------------");
        System.out.println("Total Patients in Registry : " + patientManager.getPatientList().size());
        System.out.println("Total Occupied Beds        : " +  bedManager.getOccupiedBedCount());
        System.out.println("Total Available Beds       : " + bedManager.getAvailableBedCount());
        System.out.printf("Ward Occupancy             : " + "%.2f%%", bedManager.getOccupancyPercentage());

    }

}
