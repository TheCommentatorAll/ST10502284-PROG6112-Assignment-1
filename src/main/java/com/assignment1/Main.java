package com.assignment1;

import java.util.Scanner;

import com.assignment1.model.Patient;
import com.assignment1.services.PatientManager;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PatientManager patientManager = new PatientManager();
        boolean running = true;

        while (running) {

            System.out.println("\n--- MEDICARE PATIENT MANAGEMENT SYSTEM ---");
            System.out.println("--------------------------------------------");
            System.out.println("1. Register Patient");
            System.out.println("2. Search for Patient");
            System.out.println("3. Update existing Patient details");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display all Patients");
            System.out.println("6. Exit");

            System.out.print("\n\t Select Menu Option: ");
            String input = sc.nextLine();

            //TODO: handle input validation
            switch (input) {

                case "1":
                    System.out.println("\n--- Patient Registry ---");
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter First Name: ");
                    String fName = sc.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lName = sc.nextLine();

                    // age input validation
                    boolean isValidAge = false;
                    int age = 0;
                    while (!isValidAge) {

                        System.out.print("Enter Age: ");
                        try {
                            age = sc.nextInt();

                            if (age > 0 && age < 150) {
                                isValidAge = true;
                            } else {
                                System.out.println("Please enter a realistic age greater than 0.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a numerical value for age.");
                        }

                    }

                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter Medical Condition: ");
                    String condition = sc.nextLine();
                    System.out.print("Enter Category (Inpatient/Outpatient/Emergency): ");
                    String category = sc.nextLine();

                    Patient newPatient = new Patient(id, fName, lName, age, gender, condition, category);
                    patientManager.registerPatient(newPatient);
                    break;

                case "2":
                    System.out.print("Enter Patient ID to search: ");
                    String patientId = sc.nextLine();
                    Patient found = patientManager.searchPatient(patientId);

                    if (found != null) {
                        System.out.println("Patient Found: " + found.toString());
                    } else {
                        System.out.println("Patient not found, please try again");
                    }
                    break;

                case "3":
                    System.out.print("\nEnter Patient ID to update: ");
                    String updateId = sc.nextLine();

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
                                    System.out.print("Enter New First Name: ");
                                    updatedFName = sc.nextLine();
                                    break;
                                case "2":
                                    System.out.print("Enter New Last Name: ");
                                    updatedLName = sc.nextLine();
                                    break;
                                case "3":
                                    // Using a simplified version of your validation here!
                                    System.out.print("Enter New Age: ");
                                    try {
                                        updatedAge = Integer.parseInt(sc.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Age not updated.");
                                    }
                                    break;
                                case "4":
                                    System.out.print("Enter New Gender: ");
                                    updatedGender = sc.nextLine();
                                    break;
                                case "5":
                                    System.out.print("Enter New Medical Condition: ");
                                    updatedCondition = sc.nextLine();
                                    break;
                                case "6":
                                    System.out.print("Enter New Category: ");
                                    updatedCategory = sc.nextLine();
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
                    System.out.print("\nEnter Patient ID to delete: ");
                    patientId = sc.nextLine();

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
            }
        }
        sc.close();
    }
}
