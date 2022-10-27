package models;

public class ProductivityApp extends App{
//constructor
    public ProductivityApp(Developer developer,String appName,double appSize,double appVersion,double appCost){
        super(developer, appName, appSize, appVersion, appCost);
    }
//adding to recommended app
    @Override
    public boolean isRecommendedApp() {
        if ((super.getAppCost() >= 1.99) && (super.calculateRating() > 3.0)){
            return true;
        }
        return false;
    }
//adding to the toString
    @Override
    public String toString() {
        return "ProductivityApp{" +
        super.toString() +
                '}'
                ;
    }
}
