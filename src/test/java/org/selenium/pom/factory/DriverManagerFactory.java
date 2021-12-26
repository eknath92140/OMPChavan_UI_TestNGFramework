package org.selenium.pom.factory;

        import org.selenium.pom.constants.DriverType;

        import static org.selenium.pom.constants.DriverType.CHROME;
        import static org.selenium.pom.constants.DriverType.FIREFOX;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType){

        switch (driverType){

            case CHROME : return new ChromeDriverManager();
            case FIREFOX : return new FirefoxDriverManager();
            default: throw new IllegalStateException("Unexpcted value : "+driverType);
        }
    }
}
