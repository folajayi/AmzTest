package StepDefinition;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;


//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/Features",
        glue = "",
        plugin = { "pretty", "json:src/main/resources/report.html",
        },
        monochrome = true
)

public class moTestRunner extends AbstractTestNGCucumberTests {


}
