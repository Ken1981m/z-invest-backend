package ZInvest.domain.dto;

public enum MaanedMap {

    JANUAR(1, "Januar"),
    FEBRUAR(2, "Februar"),
    MARS(3, "Mars"),
    APRIL(4, "April"),
    MAI(5, "Mai"),
    JUNI(6, "Juni"),
    JULI(7, "Juli"),
    AUGUST(8, "August"),
    SEPTEMBER(9, "September"),
    OKTOBER(10, "Oktober"),
    NOVEMBER(11, "November"),
    DESEMBER(12, "Desember");

    private final int number;
    private final String string;

    MaanedMap(int number, String string) {
        this.number = number;
        this.string = string;
    }

    public int getNumber() {
        return number;
    }

    public String getString() {
        return string;
    }

    public static String hentMaaned(int number) {
        for (MaanedMap mnd : MaanedMap.values()) {
            if (mnd.number == number) {
                return mnd.string;
            }
        }
        return null;
    }

}
