import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void testValidData() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Новосибирск");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue("28.03.2023").pressEscape();
        $("[data-test-id=name] input").setValue("Пупкин-Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79159058938");
        $x("//span[contains(text(),'условиями')]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $x("//div[contains(text(), 'Успешно!')]").shouldBe(visible, Duration.ofSeconds(11));
    }

    @Test
    void testValidDataWithMenus() {

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Но");
        $x("//span[contains(text(), 'Новосибирск')]").click();
        $("[data-test-id=date] input").click();
        $x("//td[contains(text(), '30')]").click();
        $("[data-test-id=name] input").setValue("Пупкин-Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79159058938");
        $x("//span[contains(text(),'условиями')]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $x("//div[contains(text(), 'Успешно!')]").shouldBe(visible, Duration.ofSeconds(11));
    }
}

