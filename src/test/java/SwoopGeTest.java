import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwoopGeTest {

    public static WebDriver driver;
//    private WebDriverWait wait;

    @BeforeTest
    @Parameters("browser")
    public  static void setDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }

    //    @AfterTest
//    public void quitDriver() {
//        driver.quit();
//        driver = null;
//    }
    @Test
    public void testSwoop() {
        driver.get("https://swoop.ge");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement acceptButton = driver.findElement(By.cssSelector(".acceptCookie"));
        if (acceptButton.isDisplayed()) {
            acceptButton.click();
        }
        WebElement kinButton = driver.findElement(By.linkText("კინო"));
        kinButton.click();

        List<WebElement> movies = driver.findElements(By.cssSelector("div.container.cinema_container"));
        WebElement firstMovie = movies.get(0);
        if (movies.size() > 0) {
            Actions actions = new Actions(driver);
            actions.moveToElement(firstMovie).perform();

        } else {
            System.out.println("No movies found!");
        }



        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement buyButton = driver.findElement(By.xpath("(//div[@class='movie-main-img'])[1]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", buyButton);
        js.executeScript("window.scrollTo(document.body.scrollWidth, 0)");
        WebElement caveaEastPoint = driver.findElement(By.linkText("კავეა ისთ ფოინთი"));
        Actions actions = new Actions(driver);
        actions.moveToElement(caveaEastPoint).perform();
        caveaEastPoint.click();

        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");




        WebElement parentDiv = driver.findElement(By.id("384933"));
        List<WebElement> liElements = parentDiv.findElements(By.tagName("li"));
        WebElement lastLiElement = liElements.get(liElements.size() - 1);
        String lastLiText = lastLiElement.getText();
        System.out.println(lastLiText);
        lastLiElement.click();

        WebElement pDiv = driver.findElement(By.id("384933"));
        List<WebElement> childDivs = pDiv.findElements(By.tagName("div"));
        WebElement lastChildDiv = childDivs.get(childDivs.size() - 1);
        String lastChildDivDataSched = lastChildDiv.getAttribute("data-sched");
        System.out.println(lastChildDivDataSched);
        lastChildDiv.click();




//        WebElement content1 = null;
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#eventForm > div > div.left-content")));
//            content1 = driver.findElement(By.cssSelector("#eventForm > div > div.left-content"));
//        } catch (Exception e) {
//            System.out.println("Pop-up window did not appear");
//        }



        List<WebElement> freeSeats = driver.findElements(By.cssSelector(".seat.free"));
        Pattern pattern2 = Pattern.compile("\\d+"); // pattern for matching numbers
        for (WebElement freeSeat : freeSeats) {
            String seatNumber = freeSeat.getText();
            Matcher matcher = pattern2.matcher(seatNumber);
            if (matcher.find()) {
                System.out.println("Free seat number is: " + matcher.group());
                freeSeat.click(); // click on the first free seat number
                break;
            }
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-tabs.ui-widget.ui-widget-content.ui-corner-all")));


        WebElement registrationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'რეგისტრაცია')]")));


        registrationButton.click();
        WebElement link = driver.findElement(By.cssSelector("li.juridial a.profile-tabs__link"));
        link.click();


        WebElement legalForm = driver.findElement(By.id("lLegalForm"));
        Select select = new Select(legalForm);
        select.selectByVisibleText("სააქციო საზოგადოება");


        WebElement lName = driver.findElement(By.id("lName"));
        lName.sendKeys("Company L");

        WebElement lTaxCode = driver.findElement(By.id("lTaxCode"));
        lTaxCode.sendKeys("012220098");
        WebElement dateInput = driver.findElement(By.id("registred"));

        dateInput.clear();
        dateInput.sendKeys("2022-05-15");

        WebElement lAddress = driver.findElement(By.id("lAddress"));
        lAddress.sendKeys("Address 5");

        WebElement lCity = driver.findElement(By.id("lCity"));
        lCity.sendKeys("თბილისი");

        WebElement lPostalCode = driver.findElement(By.id("lPostalCode"));
        lPostalCode.sendKeys("0101");

        WebElement lBank = driver.findElement(By.id("lBank"));
        lBank.sendKeys("TBC");

        WebElement lBankAccount = driver.findElement(By.id("lBankAccount"));
        lBankAccount.sendKeys("GE01TB01022000000000");

        WebElement lContactPersonEmail = driver.findElement(By.id("lContactPersonEmail"));
        lContactPersonEmail.sendKeys("lContactPersonEmail@gmail.com");

        WebElement lContactPersonPassword1 = driver.findElement(By.id("lContactPersonPassword"));
        lContactPersonPassword1.sendKeys("Password123");

        WebElement lContactPersonPassword2 = driver.findElement(By.id("lContactPersonConfirmPassword"));
        lContactPersonPassword2.sendKeys("Password123");


        WebElement lContactPersonName = driver.findElement(By.id("lContactPersonName"));
        lContactPersonName.sendKeys("სახელი გვარი");

        WebElement genderSelect = driver.findElement(By.id("lContactPersonGender"));
        Select form = new Select(genderSelect);
        form.selectByVisibleText("კაცი");

        WebElement datebrth = driver.findElement(By.id("birthday"));
        datebrth.clear();
        datebrth.sendKeys("1990-05-15");

        WebElement personalId = driver.findElement(By.id("lContactPersonPersonalID"));
        personalId.sendKeys("12312312354");
        WebElement phone = driver.findElement(By.id("lContactPersonPhone"));
        phone.sendKeys("995995999");

        WebElement agreedTerms = driver.findElement(By.id("IsLegalAgreedTerms"));
        agreedTerms.click();




        executor.executeScript("arguments[0].click();", personalId);
        js.executeScript("window.scrollTo(document.body.scrollWidth, 0)");

        JavascriptExecutor scrollup = (JavascriptExecutor) driver;
        scrollup.executeScript("window.scrollBy(0,-500)", "");

    }



}