package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                case 2 -> runAppMenu();
                case 3 -> runReportMenu();
                case 4 -> searchAppsBySpecificCriteria();
                case 5 -> sortAppsByNameAscendingOrder();
                case 6 -> listAllRecommendedApps();
                case 7 -> randomApp();
                case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void sortAppsByNameAscendingOrder(){
        appStoreAPI.listAllApps();
        System.out.println(appStoreAPI.listAllApps());

    }

    private void listAllRecommendedApps(){
        if (appStoreAPI != null) {
            System.out.println(appStoreAPI.listAllRecommendedApps());
        }

    }

    private void randomApp(){
        if (appStoreAPI != null) {
            System.out.println(appStoreAPI.randomApp());
        }
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private int appMenu() {
        System.out.println("""
                 -------App Menu--------------
                |   1) Add app               |
                |   2) Update app            |
                |   3) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runAppMenu() {
        int option = appMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addApp();
                //case 2 -> updateApp();
                case 3 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appMenu();
        }
    }

    private int reportMenu() {
        System.out.println("""
                 -------Report Menu-----------
                |   1) App Overview          |
                |   2) Developer Overview    |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runReportMenu() {
        int option = reportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> appOverview();
                case 2 -> developerOverview();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = reportMenu();
        }
    }

    private void appOverview(){
        System.out.println("Here is the Overview of all Apps");
        System.out.println(appStoreAPI.listSummaryOfAllApps());
    }

    private void developerOverview(){
        System.out.println("Here is the Overview of all Developer");
        System.out.println(developerAPI.listDevelopers());
    }

    private void addApp(){


            boolean isAdded = false;

            int option = ScannerInput.validNextInt("""
                    ---------------------------
                    |   1) Add Game App       |
                    |   2) Add Prod App       |
                    |   3) Add Educate App    |
                    ---------------------------
                    ==>> """);

            switch (option) {
                case 1 -> {
                    Developer developer = developerAPI.getDeveloperByName(ScannerInput.validNextLine("Enter a Developer: "));


                    String appName = ScannerInput.validNextLine("Enter an app name");
                    double appSize = ScannerInput.validNextDouble("Enter app size");
                    double appVersion = ScannerInput.validNextDouble("Enter an app version");
                    double appCost = ScannerInput.validNextDouble("Enter an app cost");
                    boolean isMultiplayer = Boolean.parseBoolean(ScannerInput.validNextLine("Is it multiplayer [Y/n]"));
                    isAdded = appStoreAPI.addApp(new GameApp(developer,appName,appSize,appCost,appVersion,isMultiplayer));
                }
                case 2 -> {
                    Developer developer = developerAPI.getDeveloperByName(ScannerInput.validNextLine("Enter a Developer: "));

                    String appName = ScannerInput.validNextLine("Enter an app name");
                    double appSize = ScannerInput.validNextDouble("Enter app size");
                    double appVersion = ScannerInput.validNextDouble("Enter an app version");
                    double appCost = ScannerInput.validNextDouble("Enter an app cost");
                    isAdded = appStoreAPI.addApp(new ProductivityApp(developer,appName,appCost,appSize,appVersion));
                }
                case 3 -> {
                    Developer developer = developerAPI.getDeveloperByName(ScannerInput.validNextLine("Enter a Developer: "));

                    String appName = ScannerInput.validNextLine("Enter an app name");
                    double appSize = ScannerInput.validNextDouble("Enter app size");
                    double appVersion = ScannerInput.validNextDouble("Enter an app version");
                    double appCost = ScannerInput.validNextDouble("Enter an app cost");
                    int level = ScannerInput.validNextInt("Enter an education level");
                    isAdded = appStoreAPI.addApp(new EducationApp(developer,appName,appSize,appVersion,appCost,level));
                }
                default -> System.out.println("Invalid option entered: " + option);
            }

            if (isAdded){
                System.out.println("App added successfully");
            }
            else{
                System.out.println("No App Added");
            }
        }
/*
    private void updateApp() {
        if (appStoreAPI.numberOfApps() > 0) {

            Developer developer = developerAPI.getDeveloperByName(ScannerInput.validNextLine("Enter a Developer: "));
            int indexToUpdate = ScannerInput.validNextInt("Enter the index of the product to update ==> ");
            if (appStoreAPI.isValidIndex(indexToUpdate)) {
                String appName = ScannerInput.validNextLine("Please enter the app name: ");
                double appSize = ScannerInput.validNextDouble("Please enter app size (MB)");
                double appVersion = ScannerInput.validNextDouble("Please Enter its Version");
                double appCost = ScannerInput.validNextDouble("Please enter the app Cost");


               if (appStoreAPI.numberOfApps(indexToUpdate, new App(developer,appName,appSize,appVersion,appCost))) {
                    System.out.println("Update Successful");
                } else {
                    System.out.println("Update NOT Successful");
                }
            } else {
                System.out.println("There are no products for this index number");
            }
        }
    }
 */
    private void deleteApp() {
        if (appStoreAPI.numberOfApps() > 0){

            int indexToDelete = ScannerInput.validNextInt("Enter the index of the post to delete ==> ");

            App appToDelete = appStoreAPI.deleteAppByIndex(indexToDelete);
            if (appToDelete != null){
                System.out.println("Delete Successful! Deleted post: " + appToDelete.getAppName());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }


    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else
                System.out.println("Developer Website NOT Updated");
        } else
            System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }



    //--------------------------------------------------
    // TODO UNCOMMENT THIS CODE as you start working through this class
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps of that rating or above)""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {

             case 1 -> searchAppsByName();
             //case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
             case 3 -> searchAppsEqualOrAboveAStarRating();
             default -> System.out.println("Invalid option");
        }
    }

    private void searchAppsByName(){
        String appName = ScannerInput.validNextLine("Please enter a app name to search by:");
        System.out.println(appStoreAPI.getAppByName(appName));

    }

    private void searchAppsByDeveloper(){
        String devName = ScannerInput.validNextLine("Please enter a developer to search by:");
        System.out.println(developerAPI.getDeveloperByName(devName));
    }

    private void searchAppsEqualOrAboveAStarRating(){
        double rating = ScannerInput.validNextDouble("Please enter a star rating:");
        System.out.println(appStoreAPI.listAllAppsAboveOrEqualAGivenStarRating((int) rating));
    }

    //--------------------------------------------------
    // TODO UNCOMMENT THIS COMPLETED CODE as you start working through this class
    //--------------------------------------------------
    private void simulateRatings() {
        // simulate random ratings for all apps (to give data for recommended apps and reports etc).
        if (appStoreAPI.numberOfApps() > 0) {
           System.out.println("Simulating ratings...");
           appStoreAPI.simulateRatings();
          System.out.println(appStoreAPI.listSummaryOfAllApps());
        } else {
            System.out.println("No apps");
        }
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        try {
            System.out.println("Saving to file: " + appStoreAPI.fileName());
            appStoreAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
        try {
            System.out.println("Saving to file: " + developerAPI.fileName());
            developerAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadAllData() {
        try {
            System.out.println("Loading from file: " + appStoreAPI.fileName());
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        try {
            System.out.println("Loading from file: " + developerAPI.fileName());
            developerAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}
