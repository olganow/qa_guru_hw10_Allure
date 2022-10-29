package ru.ya.olganow;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StepTest {
    String repo = "olganow/qa_guru_hw1_gith";
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Step("Open the main page")
        public void openMainPage() {
        open("https://github.com");
    }

    @Step("Find a repo")
    public void findRepo(String repo) {
        $x("//input[contains(@type,'text')]").setValue(repo).submit();
    }


    @Step("Click on a repo link")
    public void clickOnRepo() {
        $x("//a[contains(@href,'olganow/qa')]").click();
    }

    @Step("Check issue")
    public void checkIssue() {
        $("#issues-tab").shouldBe(visible);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepTest steps = new StepTest();

        steps.openMainPage();
        steps.findRepo(repo);
        steps.clickOnRepo();
        steps.checkIssue();

    }


}
