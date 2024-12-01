package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShahidSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the Shahid homepage")
    public void i_am_on_the_Shahid_homepage() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://shahid.mbc.net/en");
        wait = new WebDriverWait(driver, 10);
    }

    @When("I enter {string} into the search bar")
    public void i_enter_into_the_search_bar(String query) throws InterruptedException {
        WebElement searchBar = driver.findElement(By.cssSelector("svg.block"));
        searchBar.click();
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#searchInput")));
        inputField.sendKeys(query);
        Thread.sleep(1000);

    }

    @When("I click the search button")
    public void i_click_the_search_button() throws InterruptedException {
        WebElement searchButton = driver.findElement(By.cssSelector("#searchInput"));
        searchButton.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

    }

    @Then("I should see a list of search results related to {string}")
    public void i_should_see_a_list_of_search_results_related_to(String query) throws InterruptedException {
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.text-\\[16px\\]")));
        Assert.assertTrue("Search results not found!", results.getText().contains(query));
        Thread.sleep(1000);
    }

    @When("I select the first video from the results")
    public void i_select_the_first_video_from_the_results() {
        WebElement firstVideo = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.carousel-item:nth-child(3) > a:nth-child(1) > div:nth-child(1) > img:nth-child(1)")));
        firstVideo.click();

    }

    @Then("the video player should open and start buffering")
    public void the_video_player_should_open_and_start_buffering() throws InterruptedException {
        WebElement videoPlayer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#chromelessPlayerContainer")));
        Assert.assertTrue("Video player not loaded", videoPlayer.isDisplayed());
        Thread.sleep(1000);

    }

    @Then("the video should be playable")
    public void the_video_should_be_playable() throws InterruptedException {
        WebElement videoPlayer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#chromelessPlayerContainer")));
        Assert.assertTrue("Video is not playable", videoPlayer.isEnabled());
        driver.quit();
        Thread.sleep(1000);

    }
}
