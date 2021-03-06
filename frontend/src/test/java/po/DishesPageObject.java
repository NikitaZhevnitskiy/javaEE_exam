package po;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * exam
 * NIK on 08/06/2017
 */
public class DishesPageObject extends PageObject {


    public DishesPageObject(WebDriver driver) {
        super(driver);
        assertEquals("Dishes", driver.getTitle());
    }

    @Override
    public boolean isOnPage() {
        return driver.getTitle().equals("Dishes");
    }

    public DishesPageObject createDish(String nameId){
        setText("createDishForm:dishName",nameId);
        setText("createDishForm:createText","description");


        driver.findElement(By.id("createDishForm:createButton")).click();
        waitForPageToLoad();
        return this;
    }

    public HomePageObject toHome(){
        driver.findElement(By.id("homeLink")).click();
        waitForPageToLoad();
        return new HomePageObject(driver);
    }
}
