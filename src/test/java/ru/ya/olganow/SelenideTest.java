package ru.ya.olganow;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    public void testIssueSearch(){

        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#81")).should(Condition.exist);

    }

    @Test
    void issueSearchForMyRep() {

        String repo = "olganow/qa_guru_hw1_gith";
        open("https://github.com/");
        $x("//input[contains(@type,'text')]").setValue(repo).submit();
        $x("//a[contains(@href,'olganow/qa')]").click();

        $("#issues-tab").shouldBe(visible);
    }
}
