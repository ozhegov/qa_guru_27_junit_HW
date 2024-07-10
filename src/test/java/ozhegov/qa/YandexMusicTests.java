package ozhegov.qa;

import data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexMusicTests extends TestBase{

    @BeforeEach
    public void setUp(){

        open("https://music.yandex.ru/home");
        executeJavaScript("$('.bar').remove()");

        WebElement promo = $(".pay-promo-popup");
        WebElement closeButton = $(".pay-promo-close-btn");

        if (promo.isDisplayed()) {
            closeButton.click();
        }

    }

static Stream<Arguments> yandexMusicMenuShouldDisplayCorrectMenuItems(){

    return Stream.of(

            Arguments.of(
                    Language.EN,
                    List.of("Copyright Holders","Ads","Terms","Recommendation rules","Help","Follow Us")
            ),
            Arguments.of(
                    Language.RU,
                    List.of("Правообладателям","Реклама","Пользовательское соглашение","Правила рекомендаций (РФ)","Справка","Подписаться")
            ),
            Arguments.of(
                    Language.HY,
                    List.of("Իրավատերերին","Գովազդ","Օգտվողի համաձայնագիր","Առաջարկությունների կանոններ","Տեղեկություններ","Երաժշտություն սոցցանցերում")
            ),
            Arguments.of(
                    Language.UZ,
                    List.of("Mualliflik huquqi egasi","Reklama","Foydalanuvchi kelishuvi","Tavsiyalar qoidalari","Yordam","Obuna bo‘lish")
            ),
            Arguments.of(
                    Language.KK,
                    List.of("Құқық иелеріне","Жарнама","Пайдаланушы келісімі","Ұсынымдар ережелері","Анықтама","Жазылу")
            ),
            Arguments.of(
                    Language.UK,
                    List.of("Правовласникам","Реклама","Угода користувача","Правила рекомендацій","Довідка","Підписатися")
            ),
            Arguments.of(
                    Language.AZ,
                    List.of("Hüquq sahibləri","Reklam","İstifadəçi razılığı","Tövsiyə qaydaları","Kömək","Sosial şəbəkələrdə musiqi")
            )
    );


}

    @MethodSource
    @ParameterizedTest(name = "Название элементов меню в футере соответствует выбранному языку")
    public void yandexMusicMenuShouldDisplayCorrectMenuItems(Language language, List<String> expectedItems){

    $(".d-lang-switcher").click();
    $$(".d-select__list li").find(text(language.name())).click();
    $(".footer__left").$$(".footer__static-text").shouldHave(texts(expectedItems));


    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Название компании в футере соответствует выбранному языку")
    void yandexMusicFooterMenuDisplayCorrectLanguage(Language language) {
        $(".d-lang-switcher").click();
        $$(".d-select__list li").find(text(language.name())).click();
        $(".footer__right").$("a[href='//yandex.ru/all']").shouldHave(text(language.description));
    }


}
