package org.selenium.pom.base;

import com.github.javafaker.DateAndTime;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOriginal;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseTest {

    private ThreadLocal<DriverManagerAbstract>  driverManager =  new ThreadLocal<>();
    private ThreadLocal<WebDriver>  driver =  new ThreadLocal<>(); // we are not using public as to avoid unecessaory use so , due to this driver will be used in BaseTest & its subclasses to get encapsulation principle.

    // this set method of Threadlocal var will set driver value to local scope which is unique to each thread
    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    // same like above set it will get specific local unique value related to each thread
    protected WebDriver getDriver(){
        return this.driver.get();
    }

    public void setDriverManager(DriverManagerAbstract driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager(){
        return this.driverManager.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String localBrowser){

        String browser = System.getProperty("browser",localBrowser);
       // if(browser == null) browser="CHROME";

        //setDriver(new DriverManagerOriginal().initializeDriver(browser));
        // using Factory Design pattern using Interface , we create required browser object below
       // setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        // using Factory Design pattern using Abstract class , we create required browser object below
      //   setDriver(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)).getDriver());
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("CURRENT THREAD START IS => "+Thread.currentThread().getId()+ " , DRIVER is => "+getDriver());

    }

    @AfterMethod
    @Parameters("browser")
    public synchronized void quitDriver(@Optional String localBrowser, ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD END IS => "+Thread.currentThread().getId()+ " , DRIVER is => "+getDriver());
        if(result.getStatus() == ITestResult.FAILURE){

            File destFile = new File("screenshots" + File.separator + localBrowser + File.separator +
                                result.getTestClass().getRealClass().getSimpleName() + "_"+
                    result.getMethod().getMethodName() +"_"+
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) +
                    ".png");
           // takeScreenShot(destFile);
            takeScreenshotusingAShot(destFile);

        }
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){

        List<Cookie> seleniumCookies = new CookieUtils().convertRsetAssuredCookiestoSeleniumCookies(cookies);

        // as selenium dont have any method to add mautiple cookies in 1 go hence need to use below code.
        for(Cookie cookie:seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenShot(File destFile) throws IOException {

        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriverManager().getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,destFile);

    }

    private  void takeScreenshotusingAShot(File destfile){

        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());

        try{
            ImageIO.write(screenshot.getImage(),"PNG",destfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
