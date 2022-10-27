package models;

public class GameApp extends App{
    //field
    private boolean isMultiplayer = false;
    //constructor
    public GameApp(Developer developer,String appName,double appSize,double appVersion,double appCost,boolean isMultiplayer){
        super(developer, appName, appSize, appVersion, appCost);
        this.isMultiplayer = isMultiplayer;
    }
    //getter and setter
    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }
//Adding on to isRecommended method
    @Override
    public boolean isRecommendedApp() {
        if (isMultiplayer && calculateRating()>=4.0){
            return true;
        }
        return false;
    }

//adding to app summary
    @Override
    public String appSummary() {
        return super.appSummary() + "Multiplayer= " + isMultiplayer();
    }
//adding to the toString
    @Override
    public String toString() {
        return "GameApp{" +
                super.toString()  +
                "Multiplayer= " + isMultiplayer() +
                '}';
    }
}
