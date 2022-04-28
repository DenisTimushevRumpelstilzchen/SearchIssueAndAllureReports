package denis.timushev;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@Owner("Helsinki")
@Severity(SeverityLevel.BLOCKER)
@Feature("Задачи")
@Story("Просмотр задач")
@DisplayName("Тест Issue")
public class LambdaStepTestAndListener {

    private static final String REPOSITORY = "City-of-Helsinki/hel-icons";
    private static final int ISSUE_NUMBER = 1;

    @Test
    public void searchIssie() {
        Allure.parameter("Страна", "Финляндия");
        Allure.parameter("Город", "Хельсинки");
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем страницу", () -> {
            open("https://github.com");
        });
        step("Ищем " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Клик по Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверка номера Issue " + ISSUE_NUMBER, () -> {
            $(withText("#1")).click();
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
