package marhranj_zadaca_1.helperi;

import java.time.LocalTime;

public final class VremenaUtils {

    private VremenaUtils() {}

    public static String postaviFormatVremena(String vrijeme) {
        if (vrijeme.length() == 4) {
            return "0" + vrijeme;
        }
        return vrijeme;
    }

    public static boolean prijeIliUIstoVrijeme(LocalTime vrijemePrije, LocalTime vrijemeZaUsporedbu) {
        return vrijemeZaUsporedbu.equals(vrijemePrije) || vrijemePrije.isBefore(vrijemeZaUsporedbu);
    }

    public static boolean poslijeIliUIstoVrijeme(LocalTime vrijemePoslije, LocalTime vrijemeZaUsporedbu) {
        return vrijemeZaUsporedbu.equals(vrijemePoslije) || vrijemePoslije.isAfter(vrijemeZaUsporedbu);
    }

}
