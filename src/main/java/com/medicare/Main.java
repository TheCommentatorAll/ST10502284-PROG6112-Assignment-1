package main.java.com.medicare;

import java.util.Scanner;

import main.java.com.medicare.service.BedManager;
import main.java.com.medicare.service.MenuManager;
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
        MenuManager menuManager = new MenuManager();

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

                // I call the static void prompt user method below ()
                String choice = promptUser(sc, "Select Menu Option: ");

                switch (choice) {

                    case "1":

                        menuManager.handlePatientManagementMenu(sc, patientManager);
                        break;

                    case "2":

                        menuManager.handleBedManagementMenu(sc, patientManager, bedManager);
                        break;

                    case "3":
                        menuManager.handleReportSystemMenu(sc, reportManager);
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

    /**
     * Helper method for Scanner inputs to allow for menu returning, uses exeption handling
     * @param sc
     * @param message
     * @return
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
