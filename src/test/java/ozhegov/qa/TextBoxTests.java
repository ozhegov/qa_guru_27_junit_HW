package ozhegov.qa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxTests {

    @ParameterizedTest(name = "Сохраненные значения имени {0}, email {1}, постоянного адреса {2} и временного адреса {3} соответствуют заполненным в форме")
    @CsvSource(value = {
            "Maksim Ozhegov, test@test.com, 192007 9B Evergreen str., 192000 10B Nevergreen str",
            "Viktor Sergeev, testing@sof.com, 123 7C Cool st., 123 7C Cool st.",
            "Yo Min, yo@min.cn, China 123, China 123"
    })
    @Tag("Smoke")
    public void textBoxFormFullyFilled(String userName, String userEmail, String currentAddress, String permanentAddress){

        open("https://demoqa.com/text-box");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#name").shouldHave(text(userName));
        $("#email").shouldHave(text(userEmail));
        $("#currentAddress").shouldHave(value(currentAddress));
        $("#permanentAddress").shouldHave(value(permanentAddress));




    }

}
