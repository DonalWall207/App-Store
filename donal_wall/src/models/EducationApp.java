package models;

import utils.Utilities;

public class EducationApp extends App {
    //fields
    private int level = 0;
//constructor
    public EducationApp(Developer developer,String appName,double appSize,double appVersion,double appCost, int level) {
       super(developer, appName, appSize, appVersion, appCost);
       this.level = level ;
    }
//getter and setter
    public int getLevel() {
        if (Utilities.validRange(level,1,10)) {
            return level;
        }
        return 0;
    }

    public void setLevel(int level){
        if (Utilities.validRange(level,1,10)) {
            this.level = level;
        }
    }

//adding to recommended app conditions
    @Override
    public boolean isRecommendedApp() {
        if (super.getAppCost() > 0.99 && super.calculateRating() >= 3.5 && level >= 3) {
            return true;
        }
        return false;
    }

//adding to the app summary string
    @Override
    public String appSummary() {
        return super.appSummary() + "level " + getLevel();
    }
//adding to the toString
    @Override
    public String toString() {
        return "EducationApp{" +
                super.toString() +
                "Level: " + getLevel() +
                '}';
    }
}
