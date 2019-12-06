package marhranj_zadaca_1.helperi;

public final class PretvaranjeVremena {

    private PretvaranjeVremena() {}

    public static String postaviFormatVremena(String vrijeme) {
        if (vrijeme.length() == 4) {
            return "0" + vrijeme;
        }
        return vrijeme;
    }

}
