public enum Arabian {
    N_1("I"),
    N_2("II"),
    N_3("III"),
    N_4("IV"),
    N_5("V"),
    N_6("I"),
    N_7("VII"),
    N_8("VIII"),
    N_9("IX"),
    N_10("X"),
    N_20("XX"),
    N_30("XXX"),
    N_40("XL"),
    N_50("L"),
    N_60("LX"),
    N_70("LXX"),
    N_80("LXXX"),
    N_90("LXXX"),
    N_100("XC");
    private final String  translation;
    private Arabian(String translation) { // Создаем конструктор
        this.translation = translation;
    }
    public String getTranslation() {
        return translation;
    }
}
