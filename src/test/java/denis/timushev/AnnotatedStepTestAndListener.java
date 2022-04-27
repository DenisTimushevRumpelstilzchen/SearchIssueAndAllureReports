package denis.timushev;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("Helsinki")
@Severity(SeverityLevel.BLOCKER)
@Feature("Задачи")
@Story("Просмотр задач")
@DisplayName("Тест Issue")
public class AnnotatedStepTestAndListener {

    private static final String REPOSITORY = "City-of-Helsinki/hel-icons";
    private static final int ISSUE_NUMBER = 1;

    @Test
    public void searchIssie() {
        Allure.parameter("Страна", "Финляндия");
        Allure.parameter("Город", "Хельсинки");
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
