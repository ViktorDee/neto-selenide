import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void testValidData() {
        String planningDate = generateDate(10);
        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue("Новосибирск");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planningDate).pressEscape();
        $("[data-test-id=name] input").setValue("Пупкин-Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79159058938");
        $x("//span[contains(text(),'условиями')]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

   /* @Test
    void testValidDataWithMenus() {

        //Алгоритм сценария должен предусматривать выбор даты из формы календаря,
        // возможность выпадения даты запланированной встречи на следующий месяц.
        // Решение об изменении текущего месяца в форме календаря должно приниматься автоматически.
        // Выбор даты должен выполняться из формы календаря нажатием на её элементы,
        // а не путём ввода строки в поле ввода формы.

        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue("Но");
        $x("//span[contains(text(), 'Новосибирск')]").click();
        $("[data-test-id=date] input").click();
        String count = $x("//td[@class='calendar__day calendar__day_state_current']").getText();

        $x("//td[contains(text(), '30')]").click();
        $("[data-test-id=name] input").setValue("Пупкин-Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79159058938");
        $x("//span[contains(text(),'условиями')]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $x("//div[contains(text(), 'Успешно!')]").shouldBe(visible, Duration.ofSeconds(11));
    } */
}

