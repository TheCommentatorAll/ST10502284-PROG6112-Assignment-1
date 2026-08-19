package com.medicare;

import java.util.Scanner;

import com.medicare.model.Patient;
import com.medicare.services.PatientManager;
import com.medicare.util.ReturnToMenuExeption;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PatientManager patientManager = new PatientManager();

        patientManager.loadTestData();

        boolean running = true;

        while (running) {

            try {
                System.out.println("Welcome to Medicare, type \"exit\" at any point to return to the main menu...");
                System.out.println();
                System.out.println("\n--- MEDICARE PATIENT MANAGEMENT SYSTEM ---");
                System.out.println("--------------------------------------------");
                System.out.println("1. Register Patient");
                System.out.println("2. Search for Patient");
                System.out.println("3. Update existing Patient details");
                System.out.println("4. Delete Patient");
                System.out.println("5. Display all Patients");
                System.out.println("6. Exit");

                String input = promptUser(sc, "Select Menu Option: ");

                //TODO: #1 handle input validation
                //TODO: #2 allow user to dial back to main menu at any input time
                switch (input) {

                    case "1":
                        System.out.println("\n--- Patient Registry ---");
                        String id = promptUser(sc, "Enter Patient ID: ");
                        String fName = promptUser(sc, "Enter Patient First Name: ");
                        String lName = promptUser(sc, "Enter Patient Last Name: ");

                        // age input validation
                        boolean isValidAge = false;
                        int age = 0;
                        while (!isValidAge) {

                            try {
                                age = Integer.parseInt(promptUser(sc, "Enter Age: "));

                                if (age > 0 && age < 150) {
                                    isValidAge = true;
                                } else {
                                    System.out.println("Please enter a realistic age greater than 0.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a numerical value for age.");
                            }

                        }

                        String gender = promptUser(sc, "Enter Patient Gender: ");
                        String condition = promptUser(sc, "Enter Patient Condition:");
                        String category = promptUser(sc, "Enter Patient Category (Inpatient/Emergency/Outpatient)");

                        Patient newPatient = new Patient(id, fName, lName, age, gender, condition, category);
                        patientManager.registerPatient(newPatient);
                        break;

                    case "2":
                        String patientId = promptUser(sc, "Enter Patient ID to search for: ");
                        Patient found = patientManager.searchPatient(patientId);

                        if (found != null) {
                            System.out.println("Patient Found: " + found.toString());
                        } else {
                            System.out.println("Patient not found, please try again");
                        }
                        break;

                    case "3":
                        String updateId = promptUser(sc, "Enter Patient ID to update: ");

                        Patient existingPatient = patientManager.searchPatient(updateId);

                        if (existingPatient != null) {

                            String updatedFName = existingPatient.getFirstName();
                            String updatedLName = existingPatient.getLastName();
                            int updatedAge = existingPatient.getAge();
                            String updatedGender = existingPatient.getGender();
                            String updatedCondition = existingPatient.getMedicalCondition();
                            String updatedCategory = existingPatient.getPatientCategory();

                            boolean isUpdating = true;

                            while (isUpdating) {
                                System.out.println("\n--- Update Patient: " + updatedFName + " " + updatedLName + " ---");
                                System.out.println("1. First Name       (Current: " + updatedFName + ")");
                                System.out.println("2. Last Name        (Current: " + updatedLName + ")");
                                System.out.println("3. Age              (Current: " + updatedAge + ")");
                                System.out.println("4. Gender           (Current: " + updatedGender + ")");
                                System.out.println("5. Condition        (Current: " + updatedCondition + ")");
                                System.out.println("6. Category         (Current: " + updatedCategory + ")");
                                System.out.println("7. Save Changes ONLY");
                                System.out.println("8. Save Changes and Exit");
                                System.out.print("Select a field to update: ");

                                String updateChoice = sc.nextLine();

                                switch (updateChoice) {
                                    case "1":
                                        updatedFName = promptUser(sc, "Enter New First Name: ");
                                        break;
                                    case "2":
                                        updatedLName = promptUser(sc, "Enter new Last Name: ");
                                        break;
                                    case "3":
                                        try {
                                            updatedAge = Integer.parseInt(promptUser(sc, "Enter New Age: "));
                                        } catch (NumberFormatException e) {
                                            System.out.println("Invalid input. Age not updated.");
                                        }
                                        break;
                                    case "4":
                                        updatedGender = promptUser(sc, "Enter New Gender: ");
                                        break;
                                    case "5":
                                        updatedCondition = promptUser(sc, "Enter New Condition: ");
                                        break;
                                    case "6":
                                        updatedCategory = promptUser(sc, "Enter New Category: ");
                                        break;
                                    case "7":
                                        Patient updatedPatientData = new Patient(updateId, updatedFName, updatedLName, updatedAge, updatedGender, updatedCondition, updatedCategory);
                                        patientManager.updatePatientDetails(updateId, updatedPatientData);

                                        System.out.println("Patient updated successfully!");
                                        break;
                                    case "8":
                                        updatedPatientData = new Patient(updateId, updatedFName, updatedLName, updatedAge, updatedGender, updatedCondition, updatedCategory);
                                        patientManager.updatePatientDetails(updateId, updatedPatientData);

                                        System.out.println("Patient updated successfully!");
                                        isUpdating = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                }
                            }
                        } else {
                            System.out.println("Patient with ID " + updateId + " not found.");
                        }
                        break;
                    case "4":
                        patientId = promptUser(sc, "Enter Patient ID to delete: ");

                        boolean deleted = patientManager.deletePatient(patientId);

                        if (deleted == true) {
                            System.out.println("deleting...");
                            System.out.println("Patient ID " + patientId + " successfully deleted.");
                        } else {
                            System.out.println("Patient not Found, please try again.");

                        }
                        break;

                    case "5":

                        System.out.println("\n--- Patient Data ---");
                        patientManager.displayAllPatients();
                        break;

                    case "6":
                        System.out.println("Exiting program...");
                        running = false;
                }
            } catch (ReturnToMenuExeption r) {
                System.out.println("\n[!} Operation Cancelled. Returning to Main Menu...");
            }
        }
        sc.close();
    }

    /*
     * Helper method for Scanner inputs to allow for menu returning, uses exeption handling
     * @param sc : Scanner
     * @param message : string
     * @return input : String
     */
    public static String promptUser(Scanner sc, String message) {
        System.out.println("\n" + message);
        String input = sc.nextLine();

        if (input.trim().equalsIgnoreCase("exit")) {
            throw new ReturnToMenuExeption();

        }

        return input;
    }
}
