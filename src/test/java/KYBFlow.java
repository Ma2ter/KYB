import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class KYBFlow {

    private WebDriver driver;
    private WebDriverWait wait;
    private By nextLocator = By.xpath("//*[@class='row center']//*[@class='submit']");

    public KYBFlow(String url) {
        initDriver(url);
    }

    public static void main(String[] args) {
        String url = "http://localhost:63342/rnd/rnd.main/MyPage.html?_ijt=e74p2f9it7ff6l72vsrcm31hpa";

        CompanyInfo companyInfo = CompanyInfo.defaultInstance();
        List<UboInfo> uboInfoList = Arrays.asList(
                UboInfo.defaultInstance(), UboInfo.defaultInstance()
        );
        List<CompanyInfo> companyList = Arrays.asList(
                CompanyInfo.defaultInstance(), CompanyInfo.defaultInstance()
        );

        //НАЧАЛО ФЛОУ
        KYBFlow flow = new KYBFlow(url);
        //First form - COMPANY
        flow.addCompanyInfo(companyInfo,
                Arrays.asList(
                        new File("/users/ma2ter/Downloads/5057855081_5-12GIBYXM4_20190916.pdf")
                ));
        //Индексы необходимы, поскольку локаторы на страницу меняются в зависимости от порядкового
        //номера ВСЕХ предыдущих компаний, UBO и т.д.
        int index = 0;
        //Second form - UBO
        if (!companyInfo.getSkipUbo()) {
            index = flow.addUboInfo(index, uboInfoList);
        }
        //Third form - shareholders, board members and directors of the company
        //It can be either individual or company, so at first we add list of individuals,
        //then - list of companies
        index = flow.addIndividuals(index, uboInfoList);
        index = flow.addCompanies(index, companyList);
        flow.submitAndFinish();
    }

    private void initDriver(String url) {
        driver = new ChromeDriver();
        driver.manage().timeouts()
                .implicitlyWait(3, TimeUnit.SECONDS)
                .pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get(url);
        //10 - timeOut in seconds
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
    }

    public void addCompanyInfo(CompanyInfo info, List<File> documents) {
        driver.findElement(By.name("companyName")).sendKeys(info.getCompanyName());
        driver.findElement(By.name("registrationNumber")).sendKeys(info.getRegistrationNumber());
        driver.findElement(By.cssSelector(".country")).sendKeys(info.getCountry());
        driver.findElement(By.name("type")).sendKeys(info.getTypeOfEntity());
        driver.findElement(By.name("incorporatedOn")).sendKeys(info.getIncorporatedOn());
        driver.findElement(By.name("controlScheme")).sendKeys(info.getControlScheme());
        if (info.getSkipUbo()) {
            driver.findElement(By.name("ownerType ")).click();
        }
        documents.forEach(file -> {
            By uploadDocumentLocator = By.cssSelector(".empty > .drag-drop > input");
            wait.until(presenceOfElementLocated(uploadDocumentLocator));
            driver.findElement(uploadDocumentLocator).sendKeys(file.getAbsolutePath());
            //.empty > .drag-drop > .error
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".circle")));
            if (!driver.findElements(By.cssSelector(".empty > .drag-drop > .error")).isEmpty()) {
                throw new RuntimeException("Error while uploading file " + file.getAbsolutePath() + " - Invalid file format or duplicate");
            }

        });
        driver.findElement(By.cssSelector(".submit:last-of-type")).click();
    }

    public int addUboInfo(int startIndex, List<UboInfo> infoList) {
        IntStream.range(0, infoList.size()).forEach(idx -> {
            UboInfo info = infoList.get(idx);
            int index = idx + startIndex;
            //if not first UBO then click on 'Add a participant'
            if (idx > 0) {
                driver.findElement(By.cssSelector(".payment-method-add")).click();
            }
            addPosition(index, info);
        });
        driver.findElement(nextLocator).click();
        return startIndex + infoList.size();
    }

    public int addIndividuals(int startIndex, List<UboInfo> infoList) {
        IntStream.range(0, infoList.size()).forEach(idx -> {
            UboInfo info = infoList.get(idx);
            int index = idx + startIndex;
            //if not first UBO then click on 'Add a participant'
            if (idx > 0) {
                driver.findElement(By.cssSelector(".payment-method-add")).click();
            }
            driver.findElement(By.xpath("(//*[@value='individual']/ancestor::label)[last()]")).click();
            addPosition(index, info);
        });

        return startIndex + infoList.size();
    }

    public int addCompanies(int startIndex, List<CompanyInfo> infoList) {

        IntStream.range(0, infoList.size()).forEach(idx -> {
            CompanyInfo info = infoList.get(idx);
            int index = idx + startIndex;
            //if not first UBO then click on 'Add a participant'
            if (!driver.findElements(By.cssSelector(".payment-method-add")).isEmpty()) {
                driver.findElement(By.cssSelector(".payment-method-add")).click();
            }
            driver.findElement(By.xpath("(//*[@value='company']/ancestor::label)[last()]")).click();
            driver.findElement(By.name(index + "companyName")).sendKeys(info.getCompanyName());
            driver.findElement(By.name(index + "registrationNumber")).sendKeys(info.getRegistrationNumber());
            driver.findElement(By.xpath("//*[@class='country'][last()]")).sendKeys(info.getCountry());

            driver.findElement(By.xpath("(//*[@class='payment-method']//*[@class='submit'])[last()]")).click();
        });

        return startIndex + infoList.size();
    }

    public void addPosition(int index, UboInfo info) {
        if (info.getShareholder()) {
            driver.findElement(By.xpath("(//*[@value='shareholder']/ancestor::label)[last()]")).click();
        }
        if (info.getDirector()) {
            driver.findElement(By.cssSelector("(//*[@value='director']/ancestor::label)[last()]")).click();
        }
        driver.findElement(By.name(index + "firstName")).sendKeys(info.getFirstname());
        driver.findElement(By.name(index + "lastName")).sendKeys(info.getLastname());
        driver.findElement(By.name(index + "middleName")).sendKeys(info.getMiddlename());
        driver.findElement(By.name(index + "dob")).sendKeys(info.getDob());
        driver.findElement(By.name(index + "phone")).sendKeys(info.getPhone());
        driver.findElement(By.name(index + "email")).sendKeys(info.getEmail());

        driver.findElement(By.xpath("(//*[@class='payment-method']//*[@class='submit'])[last()]")).click();
    }

    public void submitAndFinish() {
        driver.findElement(nextLocator).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Please check and review all the provided information and make sure that all the data is valid and belongs to you.']")));
        driver.findElement(nextLocator).click();
        driver.quit();
    }
}
