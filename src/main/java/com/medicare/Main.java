package main.java.com.medicare;

import java.util.Scanner;

import main.java.com.medicare.model.Inpatient;
import main.java.com.medicare.model.Patient;
import main.java.com.medicare.model.PatientCategory;
import main.java.com.medicare.service.BedManager;
import main.java.com.medicare.service.PatientManager;
import main.java.com.medicare.service.ReportManager;
import main.java.com.medicare.util.ReturnToMenuExeption;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PatientManager patientManager = new PatientManager();
        BedManager bedManager = new BedManager();
        ReportManager reportManager = new ReportManager(bedManager, patientManager);

        boolean running = true;

        while (running) {

            try {

                System.out.println("\n=== MEDICARE MAIN MENU ===");
                System.out.println("============================");
                System.out.println("Welcome to Medicare, type \"exit\" at any point to return to the main menu...");
                System.out.println("1. Patient Management");
                System.out.println("2. Bed Management");
                System.out.println("3. Report System");
                System.out.println("4. Exit System");
                String choice = promptUser(sc, "Select Menu Option: ");

                switch (choice) {

                    case "1":

                        handlePatientManagementMenu(sc, patientManager);
                        break;

                    case "2":

                        handleBedManagementMenu(sc, patientManager, bedManager);
                        break;

                    case "3":
                        handleReportSystemMenu(sc, reportManager);
                        break;

                    case "4":
                        System.out.println("Stopping Program...");
                        System.out.println("Exiting System...");
                        running = false;

                }

            } catch (ReturnToMenuExeption e) {
            }
        }

        sc.close();
    }

    private static void handleReportSystemMenu(Scanner sc, ReportManager reportManager) {

        boolean inReportMenu = true;

        while (inReportMenu) {

            System.out.println("\n=== REPORTING SYSTEM ===");
            System.out.println("==========================");
            System.out.println("1. Display all Patients Report");
            System.out.println("2. Display Occupied Bed Report");
            System.out.println("3. Display Available Bed Report");
            System.out.println("4. Display Summary Statistics");
            System.out.println("5. Return to Main Menu");

            String input = promptUser(sc, "Select Menu Option: ");

            switch (input) {

                case "1":
                    reportManager.displayAllPatientsReport();
                    System.out.println();
                    break;

                case "2":
                    reportManager.displayBedStatusReport(true);
                    System.out.println();
                    break;

                case "3":
                    reportManager.displayBedStatusReport(false);
                    System.out.println();
                    break;

                case "4":
                    reportManager.displaySummaryStatistics();
                    System.out.println();
                    break;

                case "5":
                    System.out.println("Returning to main menu...");
                    inReportMenu = false;
                    break;

                default:
                    System.out.println("[!] Invalid Selection, please try again.");
            }
        }
    }

    private static void handlePatientManagementMenu(Scanner sc, PatientManager patientManager) {

        boolean inPatientMenu = true;
        while (inPatientMenu) {

            System.out.println();
            System.out.println("\n=== MEDICARE PATIENT MANAGEMENT SYSTEM ===");
            System.out.println("============================================");
            System.out.println("1. Register Patient");
            System.out.println("2. Search for Patient");
            System.out.println("3. Update existing Patient details");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display all Patients");
            System.out.println("6. Return to Main Menu");

            String input = promptUser(sc, "Select Menu Option: ");

            //TODO: #1 handle input validation
            switch (input) {

                case "1":
                    System.out.println("\n----------------------------");
                    System.out.println("--- Patient Registration ---");
                    System.out.println("----------------------------");
                    String id = patientManager.generateNextPatientId();
                    System.out.println("Assigned Patient ID: " + id);
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
                    String condition = promptUser(sc, "Enter Patient Condition: ");

                    boolean validCategory = false;
                    Patient newPatient = null;
                    while (!validCategory) {
                        System.out.println("\n--- Patient Category ---");
                        System.out.println("1. Inpatient");
                        System.out.println("2. Emergency");
                        System.out.println("3. Outpatient");
                        String choice = promptUser(sc, "Select Patient Category (1-3): ");

                        switch (choice) {

                            case "1":
                                newPatient = new Inpatient(id, fName, lName, age, gender, condition, PatientCategory.INPATIENT, "Unassigned");
                                validCategory = true;
                                break;

                            case "2":
                                newPatient = new Patient(id, fName, lName, age, gender, condition, PatientCategory.EMERGENCY);
                                validCategory = true;
                                break;

                            case "3":
                                newPatient = new Patient(id, fName, lName, age, gender, condition, PatientCategory.OUTPATIENT);
                                validCategory = true;
                                break;
                            default:
                                System.out.println("[!] Invalid selection: Please enter 1, 2, or 3.");
                        }
                    }

                    patientManager.registerPatient(newPatient);

                    break;

                case "2":
                    System.out.println("\n------------------------");
                    System.out.println("--- Patient Finder ---");
                    System.out.println("------------------------");
                    System.out.println("[*] patient ID format (P001, P002, etc), not case sensitive");
                    System.out.println();

                    boolean isFound = false;

                    String patientId = promptUser(sc, "Enter Patient ID to search for: ");
                    while (!isFound) {
                        Patient found = patientManager.searchPatient(patientId);

                        if (found != null) {
                            System.out.println("Patient Found: " + found.toString());
                            isFound = true;
                        } else {
                            System.out.println("[!] Patient not found, please try again");
                            patientId = promptUser(sc, "Enter Patient ID to search for: ");
                        }
                    }

                    break;

                case "3":
                    System.out.println("\n-------------------------");
                    System.out.println("--- Patient Updater ---");
                    System.out.println("-------------------------");

                    String updateId = promptUser(sc, "Enter Patient ID to update: ");

                    Patient existingPatient = patientManager.searchPatient(updateId);

                    if (existingPatient != null) {

                        String updatedFName = existingPatient.getFirstName();
                        String updatedLName = existingPatient.getLastName();
                        int updatedAge = existingPatient.getAge();
                        String updatedGender = existingPatient.getGender();
                        String updatedCondition = existingPatient.getMedicalCondition();
                        PatientCategory updatedCategory = existingPatient.getPatientCategory();

                        boolean isUpdating = true;

                        while (isUpdating) {
                            System.out.println("\n--- Update Patient: " + updatedFName + " " + updatedLName + "{" + existingPatient.getPatientID() + "}" + " ---");
                            System.out.println("1. First Name       (Current: " + updatedFName + ")");
                            System.out.println("2. Last Name        (Current: " + updatedLName + ")");
                            System.out.println("3. Age              (Current: " + updatedAge + ")");
                            System.out.println("4. Gender           (Current: " + updatedGender + ")");
                            System.out.println("5. Condition        (Current: " + updatedCondition + ")");
                            System.out.println("6. Category         (Current: " + updatedCategory + ")");
                            System.out.println("7. Save Changes ONLY");
                            System.out.println("8. Save Changes and Exit");
                            String updateChoice = promptUser(sc, "Selection Option to Update: ");

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

                                    boolean validSelection = false;

                                    while (!validSelection) {
                                        System.out.println("\nSelect New Category:");
                                        System.out.println("1. Inpatient");
                                        System.out.println("2. Emergency");
                                        System.out.println("3. Outpatient");
                                        String catChoice = promptUser(sc, "Select choice (1-3): ");

                                        switch (catChoice) {

                                            case "1":
                                                updatedCategory = PatientCategory.INPATIENT;
                                                validSelection = true;
                                                break;

                                            case "2":
                                                updatedCategory = PatientCategory.EMERGENCY;
                                                validSelection = true;
                                                break;

                                            case "3":
                                                updatedCategory = PatientCategory.OUTPATIENT;
                                                validSelection = true;
                                                break;

                                            default:
                                                System.out.println("[!] Invalid Selection, please enter 1, 2, or 3.");

                                        }
                                    }

                                    break;
                                case "7":
                                    Patient updatedPatientData = new Patient(updateId, updatedFName, updatedLName, updatedAge, updatedGender, updatedCondition, updatedCategory);
                                    patientManager.updatePatientDetails(updateId, updatedPatientData);

                                    System.out.println("\n[*] Patient updated successfully!");
                                    break;
                                case "8":
                                    updatedPatientData = new Patient(updateId, updatedFName, updatedLName, updatedAge, updatedGender, updatedCondition, updatedCategory);
                                    patientManager.updatePatientDetails(updateId, updatedPatientData);

                                    System.out.println("\n[*] Patient updated successfully! Exiting...");
                                    isUpdating = false;
                                    break;
                                default:
                                    System.out.println("\n[!] Invalid selection. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Patient with ID " + updateId + " not found.");
                    }
                    break;
                case "4":
                    System.out.println("\n--------------------------");
                    System.out.println("--- Patient Deletion ---");
                    System.out.println("--------------------------");
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

                    System.out.println("\n--------------------------");
                    System.out.println("--- Patient Database ---");
                    System.out.println("--------------------------");
                    

                    if(patientManager.getPatientList().isEmpty()){
                        patientManager.displayAllPatients();
                    }else{
                        String dbChoice = promptUser(sc, "[?] Do you want to sort the Patient Database? (Y/N): ").toUpperCase();

                        switch (dbChoice) {

                        case "Y":
                            System.out.println("\n--------------------");
                            System.out.println("--- Sorting Menu ---");
                            System.out.println("--------------------");
                            System.out.println("1. Sort by Patient ID");
                            System.out.println("2. Sort by Patient Category");
                            System.out.println("3. Sort by Patient Age");
                            System.out.println("4. Sort by Patient Last Name");
                            System.out.println("5. Return to Menu");
                            String sortChoice = promptUser(sc, "Select Menu Option: ");
                            

                            switch (sortChoice) {

                                case "1":
                                    patientManager.sortByPatientId();
                                    patientManager.displayAllPatients();
                                    break;

                                case "2":
                                    patientManager.sortByCategory();
                                    patientManager.displayAllPatients();
                                    break;

                                case "3":
                                    patientManager.sortByAge();
                                    patientManager.displayAllPatients();
                                    break;

                                case "4":
                                    patientManager.sortByLastName();
                                    patientManager.displayAllPatients();
                                    break;

                                case "5":
                                    System.out.println("[*] Returning to menu...");
                                    break;

                                default:
                                    System.out.println("[!] Not a valid choice. Please try again.");
                                    break;
                            }
                            break;

                            case "N":
                                System.out.println("[*] NO Selected. Returning to Main Menu...");
                                break;

                            default:
                                System.out.println("[!] Not a valid choice. Please try again");
                                break;
                        }
                        
                    }
                            

                case "6":
                    System.out.println("Returning to Main Menu...");
                    inPatientMenu = false;
                    break;

                default:
                    System.out.println("[!] Invalid selection, please try again.");
            }
        }

    }

    /**
     * @param sc
     * @param patientManager
     * @param bedManager
     */
    private static void handleBedManagementMenu(Scanner sc, PatientManager patientManager, BedManager bedManager) {

        boolean inBedMenu = true;

        while (inBedMenu) {

            System.out.println("\n=== MEDICARE BED MANAGEMENT SYSTEM ===");
            System.out.println("========================================");
            System.out.println("1. Allocate available bed to Inpatient.");
            System.out.println("2. Release bed of discharged patient.");
            System.out.println("3. Display Ward Layout.");
            System.out.println("4. Display Available Beds.");
            System.out.println("5. Display Occupied Beds.");
            System.out.println("6. Return to Main Menu.");

            String choice = promptUser(sc, "Select Menu Option: ");

            switch (choice) {

                case "1":

                    System.out.println("\n------------------------");
                    System.out.println("--- Bed Allocation ---");
                    System.out.println("------------------------");
                    patientManager.listIntpatients();
                    String patientId = promptUser(sc, "Enter Patient ID for allocation: ");
                    Patient patient = patientManager.searchPatient(patientId);

                    if (patient != null) {
                        bedManager.allocateBeds(patient);
                    } else {
                        System.out.println("Error: Patient ID not found");
                    }
                    break;

                case "2":

                    System.out.println("\n---------------------");
                    System.out.println("--- Bed Release ---");
                    System.out.println("---------------------");
                    String bedNumber = promptUser(sc, "Enter Bed Number to release (e.g., B01): ");
                    bedManager.releaseBed(bedNumber);
                    break;

                case "3":
                    System.out.println("\n---------------------");
                    System.out.println("--- Ward Layout ---");
                    System.out.println("---------------------");
                    bedManager.displayCompleteLayout();
                    break;

                case "4":
                    System.out.println("\n------------------------");
                    System.out.println("--- Available Beds ---");
                    System.out.println("------------------------");
                    bedManager.displayAvailableBeds();
                    break;

                case "5":
                    System.out.println("\n-----------------------");
                    System.out.println("--- Occupied Beds ---");
                    System.out.println("-----------------------");
                    bedManager.displayOccupiedBeds();
                    break;

                case "6":
                    System.out.println("Returning to Main Menu...");
                    inBedMenu = false;
            }
        }
    }

    /*
     * Helper method for Scanner inputs to allow for menu returning, uses exeption
     * handling
     * 
     * @param sc : Scanner
     * 
     * @param message : string
     * 
     * @return input : String
     */
    public static String promptUser(Scanner sc, String message) {
        System.out.print("\n" + message);
        String input = sc.nextLine();

        if (input.trim().equalsIgnoreCase("exit")) {
            System.out.println("[!] action stopped [!]. Returning to main menu...");
            throw new ReturnToMenuExeption();

        }

        return input;
    }
}
