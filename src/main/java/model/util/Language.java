package model.util;

public enum Language {
    ENG("en"),
    RUS("ru");
    private String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
