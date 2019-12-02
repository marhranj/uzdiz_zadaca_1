package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Raspored {

    private LocalTime pocetak;
    private LocalTime kraj;

    private Dan ponedjeljak = new Dan();
    private Dan utorak = new Dan();
    private Dan srijeda = new Dan();
    private Dan cetvrtak = new Dan();
    private Dan petak = new Dan();
    private Dan subota = new Dan();
    private Dan nedjelja = new Dan();

    public Raspored(LocalTime pocetak, LocalTime kraj, String sadrzajDatotekeRasporeda) {
        this.pocetak = pocetak;
        this.kraj = kraj;

        String[] redoviZapisa = sadrzajDatotekeRasporeda.split("\\r?\\n");
        redoviZapisa = Arrays.copyOfRange(redoviZapisa, 1, redoviZapisa.length);
        String[] emisijeSaZadanimPocetkom = dohvatiRedoveZapisaPremaBrojuAtributa(redoviZapisa, 3);
        redoviZapisa = removeSubArray(redoviZapisa, emisijeSaZadanimPocetkom);
        String[] emisijebezZadanogVremena = dohvatiRedoveZapisaPremaBrojuAtributa(redoviZapisa, 2);
        redoviZapisa = removeSubArray(redoviZapisa, emisijebezZadanogVremena);
        String[] emisijebezZadanogDana = dohvatiRedoveZapisaPremaBrojuAtributa(redoviZapisa, 1);
    }

    public Dan getPonedjeljak() {
        return ponedjeljak;
    }

    public Dan getUtorak() {
        return utorak;
    }

    public Dan getSrijeda() {
        return srijeda;
    }

    public Dan getCetvrtak() {
        return cetvrtak;
    }

    public Dan getPetak() {
        return petak;
    }

    public Dan getSubota() {
        return subota;
    }

    public Dan getNedjelja() {
        return nedjelja;
    }

    private Dan dohvatiDanPremaIndexu(int index) {
        index = index % 7;
        switch (index) {
            case 1: return ponedjeljak;
            case 2: return utorak;
            case 3: return srijeda;
            case 4: return cetvrtak;
            case 5: return petak;
            case 6: return subota;
            default: return nedjelja;
        }
    }

    private String[] dohvatiRedoveZapisaPremaBrojuAtributa(String[] redoviZapisa, int brojAtributa) {
        return Stream.of(redoviZapisa)
                .filter(filtrirajZapisePremaBrojuAtributa(brojAtributa))
                .filter(filtrirajZapisePremaIspravnostiAtributa(brojAtributa))
                .toArray(String[]::new);
    }

    private Predicate<String> filtrirajZapisePremaBrojuAtributa(int brojAtributa) {
        return redZapisa -> {
            String[] atributi = redZapisa.split("\\s*;\\s*");
            int duzinaSijecanjaNiza = atributi.length > brojAtributa - 1 ? brojAtributa : atributi.length;
            atributi = Arrays.copyOfRange(atributi, 0, duzinaSijecanjaNiza);
            return atributi.length >= brojAtributa;
        };
    }

    private Predicate<String> filtrirajZapisePremaIspravnostiAtributa(int brojAtributa) {
        return redZapisa -> {
            String[] atributi = redZapisa.split("\\s*;\\s*");

            boolean ispravno = true;
            int temp = brojAtributa - 1;
            if (temp-- == 2) {
                ispravno = atributi[2].matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
            }
            if (temp-- == 1) {
                ispravno = atributi[1].matches("\\b([0-9]|[0-9]-[0-9]|[0-9]*(,[0-9]))\\b");
            }
            if (temp == 0) {
                ispravno = atributi[0].matches("^[0-9]*$");
            }
            return ispravno;
        };
    }

    private String[] removeSubArray(String[] array, String[] subArray) {
        return Stream.of(array)
                .filter(elem -> !Arrays.asList(subArray).contains(elem))
                .toArray(String[]::new);
    }

}
