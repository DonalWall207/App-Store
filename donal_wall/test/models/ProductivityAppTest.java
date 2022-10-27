package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductivityAppTest {

    private ProductivityApp pdAppBelowBoundary, pdAppOnBoundary, pdAppAboveBoundary, pdAppInvalidData;
    private Developer developerLego = new Developer("Lego", "www.lego.com");
    private Developer developerSphero = new Developer("Sphero", "www.sphero.com");

    @BeforeEach
    void setUp() {
        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0), level(1-10).
        pdAppBelowBoundary = new ProductivityApp(developerLego, "WeDo", 1, 1.0, 0);
        pdAppOnBoundary = new ProductivityApp(developerLego, "Spike", 1000, 2.0, 1.99);
        pdAppAboveBoundary = new ProductivityApp(developerLego, "EV3", 1001, 3.5, 2.99);
        pdAppInvalidData = new ProductivityApp(developerLego, "", -1, 0, -1.00);
    }

    @AfterEach
    void tearDown() {
        pdAppBelowBoundary = pdAppOnBoundary = pdAppAboveBoundary = pdAppInvalidData = null;
        developerLego = developerSphero = null;
    }

    @Nested
    class ObjectStateMethods {

        @Test
        void appSummaryReturnsCorrectString() {
            ProductivityApp pdApp = setupProductivityAppWithRating(3, 3);
            String stringContents = pdApp.appSummary();

            assertTrue(stringContents.contains(pdApp.getAppName() + "(V" + pdApp.getAppVersion()));
            assertTrue(stringContents.contains(pdApp.getDeveloper().toString()));
            assertTrue(stringContents.contains("€" + pdApp.getAppCost()));
            assertTrue(stringContents.contains("Rating: " + pdApp.calculateRating()));
        }

        @Test
        void toStringReturnsCorrectString() {
            ProductivityApp pdApp = setupProductivityAppWithRating(3, 3);
            String stringContents = pdApp.toString();

            assertTrue(stringContents.contains(pdApp.getAppName()));
            assertTrue(stringContents.contains("(Version " + pdApp.getAppVersion()));
            assertTrue(stringContents.contains(pdApp.getDeveloper().toString()));
            assertTrue(stringContents.contains(pdApp.getAppSize() + "MB"));
            assertTrue(stringContents.contains("Cost: " + pdApp.getAppCost()));
            assertTrue(stringContents.contains("Ratings (" + pdApp.calculateRating()));

            //contains list of ratings too
            assertTrue(stringContents.contains("John Doe"));
            assertTrue(stringContents.contains("Very Good"));
            assertTrue(stringContents.contains("Jane Doe"));
            assertTrue(stringContents.contains("Excellent"));
        }



}
    @Nested
    class RecommendedApp {

        @Test
        void appIsRecommendedWhenAllOfTheConditionsAreTrue() {
            ProductivityApp pdApp = setupProductivityAppWithRating(4,4);
            pdApp.setAppCost(2.09);

            assertTrue(pdApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenRatingIsLessThan3() {
            //setting all conditions to true with ratings of 3 and 3 (i.e. 3.0)
            ProductivityApp pdApp = setupProductivityAppWithRating(2, 2);
            //verifying recommended app returns false (rating not high enough
            assertFalse(pdApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenAppCostIs199c() {
            //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
            ProductivityApp pdApp = setupProductivityAppWithRating(3, 3);

            //now setting appCost to 1.99 so app should not be recommended now
            pdApp.setAppCost(1.09);
            assertFalse(pdApp.isRecommendedApp());
        }


    }
    ProductivityApp setupProductivityAppWithRating(int rating1, int rating2) {
        //setting all conditions to true
        ProductivityApp pdApp = new ProductivityApp(developerLego, "WeDo", 1, 1.0, 1.00);
        pdApp.addRating(new Rating(rating1, "John Doe", "Very Good"));
        pdApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));

        //verifying all conditions are true for a recommended product app]
        assertEquals(2, pdApp.getRatings().size());  //two ratings are added
        assertEquals(1.0, pdApp.getAppCost(), 0.01);
        assertEquals(((rating1 + rating2) / 2.0), pdApp.calculateRating(), 0.01);

        return pdApp;

    }
}


