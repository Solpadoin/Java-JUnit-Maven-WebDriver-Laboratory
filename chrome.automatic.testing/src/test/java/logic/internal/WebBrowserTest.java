package logic.internal;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {

    @Test
    public void firstTest()
    {
        System.out.println("Testing First test method");
    }

    public WebElement getWebDriverElement(WebDriver driver, String elementName)
    {
        WebElement element = driver.findElement(By.name(elementName));
        try{
            if(element.isEnabled()){
                return element;
            }
        }

        catch(NoSuchElementException nsee){
            System.out.println(nsee.toString());
        }

        return null;
    }

    @Test
    void main() {
        // declaration and instantiation of objects/variables
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://www.google.com/";
        String expectedTitle = "Google";
        String actualTitle;

        // launch and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        actualTitle = driver.getTitle();

        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Title equality Test [[Passed]]! Output: Actual[ " + actualTitle + " ], Expected[ " + expectedTitle + " ]");
        } else {
            System.out.println("Title equality Test [[Failed]]! Output: Actual[ " + actualTitle + " ], Expected[ " + expectedTitle + " ]");
        }

        // testing widgets

        System.out.println("Now Testing < " + baseUrl + " >");
        String tagName;

        tagName = driver.findElement(By.className("RNNXgb")).getTagName(); // find Search Bar DIV by ClassName
        System.out.println(tagName);

        tagName = driver.findElement(By.id("viewport")).getTagName(); // find viewport element by ID
        System.out.println(tagName);

        System.out.println("DONE!");

        //
        baseUrl = "https://uk.wikipedia.org/wiki/%D0%93%D0%BE%D0%BB%D0%BE%D0%B2%D0%BD%D0%B0_%D1%81%D1%82%D0%BE%D1%80%D1%96%D0%BD%D0%BA%D0%B0";
        driver.get(baseUrl);

        System.out.println("Now Testing < " + baseUrl + " >");

        // Input message //

        //WebElement search = driver.findElement(By.name("search")); // write text to the input
        WebElement search = getWebDriverElement(driver, "search"); // test custom method -- functional
        if (search.isEnabled())
            search.sendKeys("Київ");
        else
            System.out.println("[ERROR] Can't find search element!");

        WebElement goSubmit = driver.findElement(By.name("go")); // simulate click on 'search' button
        if (goSubmit.isEnabled())
            goSubmit.click();
        else
            System.out.println("[ERROR] Can't find submit element!");

        tagName = driver.findElement(By.tagName("td")).getTagName();
        System.out.println(tagName);

        tagName = driver.findElement(By.tagName("a")).getTagName();
        System.out.println(tagName);

        tagName = driver.findElement(By.linkText("Населення")).getTagName();
        System.out.println(tagName);

        tagName = driver.findElement(By.partialLinkText("Епідемія коронавірусу")).getTagName();
        if (tagName.length() > 0)
            System.out.println(tagName);

        List<WebElement> elements = driver.findElements(By.className("thumbinner")); //.getTagName();

        int counter = 0;
        for (WebElement element : elements) {
            counter++;
            System.out.println("Element thumb tright founded! [" + counter + "]");
        }

        System.out.println("Element thumb tright founded! Total Count: [" + counter + "]");

        if (counter > 20)
            System.out.println("Thumb tright test passed!");
        else
            System.out.println("Thumb tright test failed!");

        System.out.println("DONE!");

        // close
        driver.close();
    }
}