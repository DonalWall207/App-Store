package models;

import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public abstract class App {
//fields
    private String appName = "No app name";
    private double appSize = 0;
    private double appVersion = 1.0;
    private double appCost = 0.0;
    private Developer developer;
    private List<Rating> ratings = new ArrayList<>();

    //constructors
    public App(Developer developer,String appName,double appSize,double appVersion,double appCost) {
        this.appName = appName;
        this.appSize = appSize;
        this.appVersion = appVersion;
        this.appCost = appCost;
        this.developer = developer;
    }
//getters and setters
    public String getAppName() {
        return appName;
    }

    public double getAppSize() {
        if (Utilities.validRange(appSize,1,1000)) {
            return appSize;
        }
        return 0;
    }

    public double getAppVersion() {
        if (appVersion >= 1.0) {
            return appVersion;
        }
        return 1.0;
    }

    public double getAppCost() {
        if (Utilities.greaterThanOrEqualTo(appCost,0)) {
            return appCost;
        }
        return 0;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setAppSize(double appSize) {
        if (Utilities.validRange(appSize,1,1000)){
            this.appSize = appSize;
        }
    }

    public void setAppVersion(double appVersion) {
        if (appVersion >= 1.0) {
            this.appVersion = appVersion;
        }
    }

    public void setAppCost(double appCost) {
        if (Utilities.greaterThanOrEqualTo(appCost,0)) {
            this.appCost = appCost;
        }
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    //This method collects all the previous variables and returns it in a readable string format
    @Override
    public String toString() {
        return "App{" +
                 getAppName() +
                "(Version " + getAppVersion() +
                getDeveloper() +
                getAppSize() + "MB" +
                "Cost: " + getAppCost() +
                "Ratings (" + calculateRating() +
                listRatings() +
                '}';
    }

//abstract method is stated here, but is expanded upon in the other subclasses
    public abstract boolean isRecommendedApp();

    //the method is similar to the toString in the fact that is gathers other getters for the fields and returns them as a
    //summerised String
    public String appSummary(){
        return getAppName() +"(V" + getAppVersion() + ")" + getDeveloper().toString() + "€" + getAppCost()
                +"Rating: "+ calculateRating() ;

    }
//this method returns a new rating to the ratings Array List
    public boolean addRating(Rating rating){
        return ratings.add(rating);

    }
//this method returns all the ratings in the ratings array list in a readable format
    public String listRatings(){
        if (ratings.isEmpty()) {
                return "No ratings added yet";
            } else {
                String listRatings = "";
                for (int i = 0; i < ratings.size(); i++) {
                    listRatings += i + ": " + ratings.get(i) + "\n";
                }
                return listRatings;
            }

    }

    public double calculateRating(){
        int i=0;

        double sum = 0;
        if (ratings.isEmpty()){
            return 0;
        }
        while (i<ratings.size()){
            sum += ratings.get(i).getNumberOfStars();
            i++;
        }
        //the method calculates the mean by dividing the number of stars in the ratings array list and dividing it by the
        //ratings array list
        double average = (sum / ratings.size());
        return average;

    }



}
