import com.jprotractor.NgWebDriver
import org.apache.commons.lang3.RandomStringUtils
import org.junit.After
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class First {

    val driver = ChromeDriver().apply {
        manage().timeouts()
            .pageLoadTimeout(15, TimeUnit.SECONDS)
            .implicitlyWait(3, TimeUnit.SECONDS)
    }

    val ngDriver = NgWebDriver(driver).apply {
        get("http://localhost:63342/rnd/rnd.main/MyPage.html")
//        findElement(By.cssSelector("input[type='text']")).sendKeys("n-t.io_vladimir_tarusov_test")
//        findElement(By.cssSelector("input[type='password']")).sendKeys("KycKybntio")
//        findElement(By.cssSelector("button")).click()
    }

    fun WebDriver.element(locator: By, actions: WebElement.() -> Unit) {
        this.findElement(locator).run(actions)
    }

    fun <T> By.perform(actions: WebElement.() -> T): T {
        return ngDriver.findElement(this).run(actions)
    }

    @Test
    fun first() {
        print("Hello")
        val id = RandomStringUtils.randomAlphabetic(15)
        with(ngDriver) {
            //            findElement(By.cssSelector(".group:nth-child(5) input")).sendKeys(id)
//            findElement(By.cssSelector("div:nth-child(2) > label > input:nth-child(2)")).click()
//            findElement(By.cssSelector(".sidebar > .section > div:nth-child(3) > label > input")).click()
//            findElement(By.cssSelector(".doc:nth-child(2) input")).click()
//            findElement(By.cssSelector(".doc:nth-child(4) input")).click()
//            findElement(By.cssSelector(".doc:nth-child(8) input")).click()
//            findElement(By.cssSelector(".button_apply:nth-child(2)")).click()

            switchTo().frame(0)
            By.name("").perform { sendKeys("SLOPOPENDRA2") }
            findElement(By.name("")).sendKeys("1234567890")
            findElement(By.className("")).sendKeys("Russia")
            findElement(By.cssSelector(".empty > .drag-drop > input")).sendKeys("/users/ma2ter/Downloads/5057855081_5-12GIBYXM4_20190916.pdf")
            findElement(By.cssSelector(".empty > .drag-drop > input")).sendKeys("/users/ma2ter/Downloads/5057855081_5-12GIBYXM4_20190916.pdf")
            findElement(By.cssSelector(".empty > .drag-drop > input")).sendKeys("/users/ma2ter/Downloads/5057855081_5-12GIBYXM4_20190916.pdf")

        }

    }

    @After
    fun tearDown() {
        ngDriver.quit()
    }
}