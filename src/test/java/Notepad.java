import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Notepad {

    public static WindowsDriver driver = null;
    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app","C:\\Windows\\System32\\notepad.exe");
        cap.setCapability("platformName","Windows");
        cap.setCapability("deviceName","WindowsPC");

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
        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        driver.findElementByName("OK").click();
    }
}
