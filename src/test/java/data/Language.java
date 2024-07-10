package data;

public enum Language {

    EN("A Yandex project"),
    RU("Проект компании Яндекс"),
    HY("Yandex ընկերության նախագիծ"),
    UZ("Yandex kompaniyasi loyihasi"),
    KK("Яндекс компаниясының жобасы"),
    UK("Проєкт компанії Яндекс"),
    AZ("Yandex şirkətinin layihəsi");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
