package marhranj_zadaca_1;

import marhranj_zadaca_1.entiteti.Emisija;
import marhranj_zadaca_1.entiteti.Osoba;
import marhranj_zadaca_1.entiteti.Uloga;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UcitavacKlasa {

    private UpravljacDatotekama upravljacDatotekama;

    public UcitavacKlasa(UpravljacDatotekama upravljacDatotekama) {
        this.upravljacDatotekama = upravljacDatotekama;
    }

    public List<Osoba> ucitajOsobe() {
        return ucitajSadrzajDatotekeUKlasu(upravljacDatotekama.getDatotekaOsobe(), Osoba.class);
    }

    public List<Uloga> ucitajUloge() {
        return ucitajSadrzajDatotekeUKlasu(upravljacDatotekama.getDatotekaUloge(), Uloga.class);
    }

    public List<Emisija> ucitajEmisije() {
        return ucitajSadrzajDatotekeUKlasu(upravljacDatotekama.getDatotekaEmisije(), Emisija.class);
    }

    private <T> List<T> ucitajSadrzajDatotekeUKlasu(String sadrzajDatoteke, Class<T> klasa) {
        String[] redoviZapisa = sadrzajDatoteke.split("\\r?\\n");
        redoviZapisa = Arrays.copyOfRange(redoviZapisa, 1, redoviZapisa.length);
        return Stream.of(redoviZapisa)
                .map(zapisDatoteke -> {
                    try {
                        return klasa.getDeclaredConstructor(String.class).newInstance(zapisDatoteke);
                    } catch (Exception e) {
                        System.err.println(e.getCause().getLocalizedMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
