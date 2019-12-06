package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Raspored {

    private static final String OSOBA_ULOGA_REGEX = "^([0-9]*-([0-9]+)(,[0-9]*-[0-9]+)*)$";
    private static final String VRIJEME_REGEX = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
    private static final String DAN_IZVODENJA_REGEX = "^([0-9]*(,[0-9]+)+)|([0-9]*-[0-9]+)|([0-9]*)$";
    private static final String ID_REGEX = "^[0-9]*$";

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
        String[] emisijeBezZadanogVremena = dohvatiRedoveZapisaPremaBrojuAtributa(redoviZapisa, 2);
        redoviZapisa = removeSubArray(redoviZapisa, emisijeBezZadanogVremena);
        String[] emisijeBezZadanogDana = dohvatiRedoveZapisaPremaBrojuAtributa(redoviZapisa, 1);
        if (redoviZapisa.length > 0) {
            System.err.println("Greske u sljedecim zapisima programa: " + Arrays.toString(redoviZapisa));
        }

        popuniRasporedEmisijamaSaZadanimPocetkom(emisijeSaZadanimPocetkom);
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

    private void popuniRasporedEmisijamaSaZadanimPocetkom(String[] emisijeSaZadanimPocetkom) {
        Stream.of(emisijeSaZadanimPocetkom)
                .forEach(emisijaSaZadanimPocetkom -> {
                    String[] atributi = emisijaSaZadanimPocetkom.split("\\s*;\\s*");
                    int idEmisije = Integer.parseInt(atributi[0]);
                    String dani = atributi[1];
                    LocalTime pocetakEmisije = LocalTime.parse(urediVrijeme(atributi[2]));
                    String[] osobeUloge = atributi.length > 3 ? atributi[3].split("\\s*,\\s*") : new String[] {};
                    Optional<Emisija> emisija = TvKuca.dajInstancu().getEmisije()
                            .stream()
                            .filter(emisija1 -> emisija1.getId() == idEmisije)
                            .findFirst()
                            .map(emisija1 -> (Emisija) emisija1.clone());
                    emisija.ifPresent(emisija1 -> {
                        if (unutarVremena(pocetakEmisije, emisija1)) {
                            emisija1.dodajOsobeUloge(osobeUloge);
                            if (nizDanova(dani)) {
                                int prviDan = Character.getNumericValue(dani.charAt(0));
                                int zadnjiDan = Character.getNumericValue(dani.charAt(2));
                                for (int i = prviDan; i <= zadnjiDan; i++) {
                                    Dan dan = dohvatiDanPremaIndexu(i);
                                    dan.dodajEmisiju(pocetakEmisije, emisija.get());
                                }
                            }
                        }
                    });
                });
    }

    private String urediVrijeme(String vrijeme) {
        if (vrijeme.length() == 4) {
            return "0" + vrijeme;
        }
        return vrijeme;
    }

    private boolean unutarVremena(LocalTime pocetak, Emisija emisija) {
        return pocetak.isAfter(this.pocetak)
                && pocetak.plusMinutes(emisija.getTrajanje()).isBefore(this.kraj);
    }

    private boolean nizDanova(String dan) {
        return dan.matches("^[0-9]*-[0-9]+$");
    }

    private boolean nabrajanjeDanova(String dan) {
        return dan.matches("[0-9]*(,[0-9]+)+");
    }

    private Dan dohvatiDanPremaIndexu(int index) {
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

    private Predicate<String> filtrirajZapisePremaBrojuAtributa(int brojPotrebnihAtributa) {
        return redZapisa -> {
            String[] atributi = redZapisa.split("\\s*;\\s*") ;
            int brojAtributa = zavrsavaZapisSaOsobomUlogom(redZapisa)
                    ? brojPotrebnihAtributa + 1
                    : brojPotrebnihAtributa ;
            return atributi.length == brojAtributa;
        };
    }

    private boolean zavrsavaZapisSaOsobomUlogom(String redZapisa) {
        String[] atributi = redZapisa.split("\\s*;\\s*");
        return !redZapisa.endsWith(";")
                && atributi[atributi.length - 1].matches(OSOBA_ULOGA_REGEX);
    }

    private Predicate<String> filtrirajZapisePremaIspravnostiAtributa(int brojPotrebnihAtributa) {
        return redZapisa -> {
            String[] atributi = redZapisa.split("\\s*;\\s*");

            boolean ispravno = true;
            int brojAtributa = brojPotrebnihAtributa;

            if (brojAtributa-- == 3) {
                ispravno = atributi[2].matches(VRIJEME_REGEX);
            }
            if (brojAtributa-- == 2) {
                ispravno = ispravno && atributi[1].matches(DAN_IZVODENJA_REGEX);
            }
            if (brojAtributa == 1) {
                ispravno = ispravno && atributi[0].matches(ID_REGEX);
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
