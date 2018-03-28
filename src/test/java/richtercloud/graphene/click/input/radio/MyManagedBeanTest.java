package richtercloud.graphene.click.input.radio;

import com.google.common.io.Files;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author richter
 */
@RunWith(Arquillian.class)
public class MyManagedBeanTest {
    private static final String WEBAPP_SRC = "src/main/webapp";
    private final static Logger LOGGER = LoggerFactory.getLogger(MyManagedBeanTest.class);

    @Deployment(testable = false)
    public static Archive<?> createDeployment0() throws TransformerException, XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        WebArchive retValue = ShrinkWrap.create(WebArchive.class)
                .add(EmptyAsset.INSTANCE, "beans.xml")
                .addClasses(MyManagedBean.class)
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");
        Maven.configureResolver().workOffline().resolve("richtercloud:graphene-click-input-radio:war:1.0-SNAPSHOT").withoutTransitivity().asList(JavaArchive.class).forEach(dependency -> retValue.addAsLibrary(dependency));
        //add all webapp resources
        retValue.merge(ShrinkWrap.create(GenericArchive.class)
                .as(ExplodedImporter.class)
                .importDirectory(WEBAPP_SRC)
                .as(GenericArchive.class), "/", Filters.include(".*\\.(xhtml|css|js|png)$"));

        ByteArrayOutputStream archiveContentOutputStream = new ByteArrayOutputStream();
        retValue.writeTo(archiveContentOutputStream, Formatters.VERBOSE);
        LOGGER.info(archiveContentOutputStream.toString());
        return retValue;
    }

    @Drone
    private WebDriver browser;
    @ArquillianResource
    private URL deploymentUrl;
    @FindBy(id = "mainForm:mainSelectOneRadio:0")
    private WebElement mainSelectOneRadioOption0;
    @FindBy(css = "[for='mainForm:mainSelectOneRadioPrime:0']")
    private WebElement mainSelectOneRadioPrimeOption0;
    @FindBy(id = "mainForm:mainSelectOneButtonPrime:0")
    private WebElement mainSelectOneButtonPrimeOption0;
    private final File screenshotDir;
    private int screenshotCounter = 0;

    public MyManagedBeanTest() {
        this.screenshotDir = Files.createTempDir();
        LOGGER.info(String.format("screenshot directory is '%s'",
                screenshotDir.getAbsolutePath()));
    }

    /**
     * Test of getMyProperty method, of class MyManagedBean.
     */
    @Test
    public void testAll() throws IOException {
        browser.get(deploymentUrl.toExternalForm()+"index.xhtml");
        LOGGER.debug(browser.getPageSource());
        mainSelectOneRadioOption0.click();
        mainSelectOneRadioPrimeOption0.click();
        mainSelectOneButtonPrimeOption0.click();
        screenshot();
    }

    private void screenshot() throws IOException {
        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotDir, String.valueOf(screenshotCounter)));
        screenshotCounter++;
    }
}
