package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.ISerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI implements ISerializer {

    private List<App> apps = new ArrayList<>();


    //Basic Crud Methods
    public boolean addApp(App app) {
        return apps.add(app);
    }
//if index is valid remove the app
    public App deleteAppByIndex(int index) {
        if (isValidIndex(index)) {
            return apps.remove(index);
        }
        return null;
    }
//if index is valid return the app
    public App getAppByIndex(int index) {
        if (isValidIndex(index)) {
            return apps.get(index);
        }
        return null;
    }
//if is name is valid return it
    public App getAppByName(String name){
        if (isValidAppName(name)){
            return apps.get(Integer.parseInt(name));
        }
        return null;
    }
    //Basic Crud Methods

    //Reporting Methods
    public String listAllApps() {
        if (apps.isEmpty()) {
            return "No apps";
        } else {
            String listAllApps = "";
            for (int i = 0; i < apps.size(); i++) {
                listAllApps += i + ": " + apps.get(i) + "\n";
            }
            return listAllApps;
        }
    }
//list appSummary for all apps in the array list of apps
    public String listSummaryOfAllApps(){
        String listSummaryOfAllApps = "";
        for (int i = 0; i < apps.size(); i++){
            if (apps.get(i).isRecommendedApp()){
                listSummaryOfAllApps += i + ": " + apps.get(i) + "\n";
            }
        }
        return "No apps";
    }
//lists all apps the instance of an app being a GameApp
    public String listAllGameApps(){
        String str = "";

        for (App app : apps) {
            if (app instanceof GameApp) {
                str += apps.indexOf(app) + ": " + app.appSummary() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Game Apps";
        } else {
            return str;
        }
    }

//lists all apps the instance of an app being a Product
// app
    public String listAllProductivityApps(){
        String str = "";

        for (App app : apps) {
            if (app instanceof ProductivityApp) {
                str += apps.indexOf(app) + ": " + app.appSummary() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Productivity Apps";
        } else {
            return str;
        }
    }
//lists all apps the instance of an app being education app
    public String listAllEducationApp(){
        String str = "";

        for (App app : apps) {
            if (app instanceof EducationApp) {
                str += apps.indexOf(app) + ": " + app.appSummary() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Education Apps";
        } else {
            return str;
        }
    }
//lists all aps that are recommended app
    public String listAllRecommendedApps() {
        String string = "";
        for (App app : apps) {
            string += app.getAppName() + "\n";

            if (string.isEmpty()) {
                return "No recommended apps";
            }
        }
            return string;
    }//lists all apps by name

    public String listAllAppsByName(String string){
        for (App app : apps) {
            string += app.getAppName() + "\n";

            if (string.isEmpty()) {
                return "No Apps for name " + app.getAppName();
            }
        }
        return string;
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating){
        String str = "";
        for (App app : apps) {
            str += rating + app.getAppName();
            }
            if (str.isEmpty()) {
                return "No Apps with rating of " + rating + " or above";
            }
        return str;
    }



    public String listAllAppsByChosenDeveloper( Developer developer) {
        String str = "";
        for (App app : apps) {
            if (apps instanceof Developer) {
                str += app.getAppName() + app.getDeveloper() + "\n";


                if (str.isEmpty()) {
                    return "No Apps with developer " + app.getDeveloper();
                }
            }
        }
        return str;
    }

    public int numberOfApps(){
        return apps.size();
    }

    public int numberOfAppsByChosenDeveloper(Developer developer){
        return 0;
    }

    public App randomApp() {
        for (int i = 0; i < apps.size(); i++) {
            // generating the index using Math.random()
            int index = (int) (Math.random() * apps.size());

        }
        return null;
    }

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)

    //---------------------
    // Method to simulate ratings (using the RatingUtility).
    // This will be called from the Driver (see skeleton code)
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED method as you start working through this class
    //---------------------

    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }
    //Reporting Methods

//Validation Methods

    //method to check if index given is valid
    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }
//method to check if app name given is valid
    public  boolean isValidAppName(String name){
        for (App app : apps){
            if (isValidAppName(app.getAppName()))
            return true;
        }
        return false;
    }
//Validation Methods

//Sorting Methods

    public void sortAppsByNameAscending()
    {
        for (int i = apps.size() -1; i >= 0; i--)
        {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApps(apps, i, highestIndex);
        }
    }

    private void swapApps(List<App> apps, int i, int j){
        App smaller = apps.get(i);
        App bigger =  apps.get(j);

        apps.set(i,bigger);
        apps.set(j,smaller);
    }

    // Persistence methods

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    public String fileName(){
        return "apps.xml";
    }

}