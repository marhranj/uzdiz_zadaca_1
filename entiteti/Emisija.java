package marhranj_zadaca_1.entiteti;

import marhranj_zadaca_1.MarhranjZadaca1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Emisija {

    private int id;
    private String nazivEmisije;
    private int trajanje;
    private List<Osoba> osobe;

    public Emisija(String redDatotekeEmisije) throws IllegalArgumentException {
        IllegalArgumentException exception = new IllegalArgumentException("Neispravan zapis u datoteci emisija: " + redDatotekeEmisije);
        String[] atributi = redDatotekeEmisije.split("\\s*;\\s*");
        if (atributi.length > 2) {
            try {
                id = Integer.parseInt(atributi[0]);
                nazivEmisije = atributi[1];
                trajanje = Integer.parseInt(atributi[2]);
                if (atributi.length > 3) {
                    String[] osobeUloge = atributi[3].split("\\s*,\\s*");
                    osobe = Stream.of(osobeUloge)
                            .map(osobaUloga -> osobaUloga.split("\\s*-\\s*"))
                            .map(this::dodjeliOsobiUlogu)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toList());
                }
            } catch (IllegalArgumentException e) {
                throw exception;
            }
        } else {
            throw exception;
        }
    }

    public int getId() {
        return id;
    }

    public String getNazivEmisije() {
        return nazivEmisije;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    private Optional<Osoba> dodjeliOsobiUlogu(String[] osobaUloga) throws IllegalArgumentException {
        if (osobaUloga.length > 1) {
            int osobaId = Integer.parseInt(osobaUloga[0]);
            int ulogaId = Integer.parseInt(osobaUloga[1]);
            Optional<Osoba> osoba = MarhranjZadaca1.getOsobe()
                    .stream()
                    .filter(osoba1 -> osoba1.getId() == osobaId)
                    .findFirst()
                    .map(osoba1 -> (Osoba) osoba1.clone());
            Optional<Uloga> uloga = MarhranjZadaca1.getUloge()
                    .stream()
                    .filter(uloga1 -> uloga1.getId() == ulogaId)
                    .findFirst();
            osoba.ifPresent(osoba1 -> uloga.ifPresent(osoba1::setUloga));
            return osoba;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
