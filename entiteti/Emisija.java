package marhranj_zadaca_1.entiteti;

import marhranj_zadaca_1.sucelja.Prototype;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Emisija implements Prototype {

    private int id;
    private String nazivEmisije;
    private long trajanje;
    private List<Osoba> osobe;

    public Emisija(Emisija emisija) {
        this.id = emisija.id;
        this.nazivEmisije = emisija.nazivEmisije;
        this.trajanje = emisija.trajanje;
        this.osobe = emisija.osobe;
    }

    public Emisija(String redDatotekeEmisije) throws IllegalArgumentException {
        IllegalArgumentException exception = new IllegalArgumentException("Neispravan zapis u datoteci emisija: " + redDatotekeEmisije);
        String[] atributi = redDatotekeEmisije.split("\\s*;\\s*");
        if (atributi.length > 2) {
            try {
                popuniAtribute(atributi);
            } catch (IllegalArgumentException e) {
                throw exception;
            }
        } else {
            throw exception;
        }
    }

    private void popuniAtribute(String[] atributi) {
        id = Integer.parseInt(atributi[0]);
        nazivEmisije = atributi[1];
        trajanje = Long.parseLong(atributi[2]);
        if (atributi.length > 3) {
            String[] osobeUloge = atributi[3].split("\\s*,\\s*");
            osobe = Stream.of(osobeUloge)
                    .map(osobaUloga -> osobaUloga.split("\\s*-\\s*"))
                    .map(this::dodjeliOsobiUlogu)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Prototype clone() {
        return new Emisija(this);
    }

    public void dodajOsobu(Osoba osoba) {
        osobe.add(osoba);
    }

    public int getId() {
        return id;
    }

    public String getNazivEmisije() {
        return nazivEmisije;
    }

    public long getTrajanje() {
        return trajanje;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    private Optional<Osoba> dodjeliOsobiUlogu(String[] osobaUloga) throws IllegalArgumentException {
        if (osobaUloga.length > 1) {
            Optional<Osoba> osoba = dohvatiOsobuPremaId(Integer.parseInt(osobaUloga[0]));
            Optional<Uloga> uloga = dohvatiUloguPremaId(Integer.parseInt(osobaUloga[1]));
            osoba.ifPresent(osoba1 -> uloga.ifPresent(osoba1::setUloga));
            return osoba;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Optional<Osoba> dohvatiOsobuPremaId(int idOsobe) {
        return TvKuca.dajInstancu().getOsobe()
                .stream()
                .filter(osoba -> osoba.getId() == idOsobe)
                .findFirst()
                .map(osoba -> (Osoba) osoba.clone());
    }

    private Optional<Uloga> dohvatiUloguPremaId(int idUloge) {
        return TvKuca.dajInstancu().getUloge()
                .stream()
                .filter(uloga -> uloga.getId() == idUloge)
                .findFirst();
    }

}
