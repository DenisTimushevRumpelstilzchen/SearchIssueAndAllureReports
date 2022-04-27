package denis.timushev;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@Owner("Helsinki")
@Severity(SeverityLevel.BLOCKER)
@Feature("Задачи")
@Story("Просмотр задач")
@DisplayName("Тест Issue")
public class SelenideTestAndListener {

    @Test
    public void searchIssie() {
        Allure.parameter("Регион", "Финляндия");
        Allure.parameter("Город", "Хельсинки");
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("City-of-Helsinki/hel-icons");
        $(".header-search-input").submit();
        $(linkText("City-of-Helsinki/hel-icons")).click();
        $(partialLinkText("Issues")).click();
        $(withText("#1")).click();
    }
}
