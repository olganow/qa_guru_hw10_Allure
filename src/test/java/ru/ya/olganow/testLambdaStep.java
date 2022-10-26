package ru.ya.olganow;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class testLambdaStep {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void issueSearchForMyRep() {

        String repo = "olganow/qa_guru_hw1_gith";
        step("Open the main page", () -> {
                    open("https://github.com/");
                });
        step("Find a repo", () -> {
                    $x("//input[contains(@type,'text')]").setValue(repo).submit();
                });
        step("Click on a repo link", () -> {
                    $x("//a[contains(@href,'olganow/qa')]").click();
                });
        step("Check issue", () -> {
            $("#issues-tab").shouldBe(visible);
        });
    }
}
