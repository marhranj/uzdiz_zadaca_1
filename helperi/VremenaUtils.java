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

    public static boolean vremenskiPeriodUnutarDrugogVremenskogPerioda(LocalTime pocetakVremenskogPerioda1, LocalTime krajVremenskogPerioda1,
                                                                       LocalTime pocetakVremenskogPerioda2, LocalTime krajVremenskogPerioda2) {
        return pocetakUnutarVremenskogPerioda(pocetakVremenskogPerioda1, pocetakVremenskogPerioda2, krajVremenskogPerioda2) ||
                krajUnutarVremenskogPerioda(krajVremenskogPerioda1, pocetakVremenskogPerioda2, krajVremenskogPerioda2);
    }

    private static boolean pocetakUnutarVremenskogPerioda(LocalTime pocetak, LocalTime pocetakVremenskogPerioda, LocalTime krajVremenskogPerioda) {
        return poslijeIliUIstoVrijeme(pocetak, pocetakVremenskogPerioda)
                && pocetak.isBefore(krajVremenskogPerioda);
    }

    private static boolean krajUnutarVremenskogPerioda(LocalTime kraj, LocalTime pocetakVremenskogPerioda, LocalTime krajVremenskogPerioda) {
        return prijeIliUIstoVrijeme(kraj, krajVremenskogPerioda)
                && kraj.isAfter(pocetakVremenskogPerioda);
    }

}
