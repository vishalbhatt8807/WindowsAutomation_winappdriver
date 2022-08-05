import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Calc {

    public static WindowsDriver driver = null;
    @BeforeClass
    public void setUp() throws MalformedURLException {
        //for Inspect.exe if windows Keys is not present in your local folder than use below github and
        // use multiple tools https://github.com/blackrosezy/gui-inspect-tool
        DesiredCapabilities cap = new DesiredCapabilities();
        //to search of path of any app in window use cmd and write command 'dri /s /b  App name
        //UMS app has different set of id like check lin 19 here we passed id instead of path of calc
        //https://www.securitylearningacademy.com/mod/book/view.php?id=13829&chapterid=678  follow mentioned
        //steps in Manual setup to get UMS id (use id and Post fix add !app
        cap.setCapability("app","Microsoft.WindowsCalculator_8wekyb3d8bbwe!app");
        cap.setCapability("platformName","Windows");
        cap.setCapability("deviceName","WindowsPC");
       // cap.setCapability("appWorkingDir", "C:\\Windows\\System32\\calc.exe");


        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"),cap);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }

    @AfterMethod
    public void cleanUp() throws MalformedURLException {
        driver.quit();
        setUp();
    }

    @AfterSuite
    public void tearDown(){
        driver.close();
    }

    @Test
    public void checkHelpAboutNowTest(){
        driver.findElementByName("Calculator").isDisplayed();
        driver.findElementByName("Seven").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("Four").click();
        driver.findElementByName("Equals").click();
        String a =driver.findElementByAccessibilityId("CalculatorResults").getText();
        System.out.println(a);
    }


}
