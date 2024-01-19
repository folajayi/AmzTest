package SetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.*;

public class base {


        public static WebDriver driver;
        protected WebDriver getDriver() {

            if (driver == null) {
                driver = new ChromeDriver();
            }
            return driver;
        }

}