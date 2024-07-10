package ozhegov.qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WildberriesSearchTests extends TestBase {


    @ParameterizedTest(name="Введенное название бренда {0} в поиске соответствует названию в фильтре по бренду")
    @ValueSource(strings = {
            "Lava Superfood",
            "SYOSS"
    })
    public void brandInSearchEqualsToFilterByBrand(String brandName){

        open("https://www.wildberries.ru");
        $("#searchInput").click();
        $("#searchInput").setValue(brandName).pressEnter();
        $$(".dropdown-filter__btn").find(text("Бренд")).click();
        $(".checkbox-with-text").shouldHave(text(brandName));




}

}
