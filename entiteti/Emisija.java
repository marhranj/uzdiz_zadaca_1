package marhranj_zadaca_1.entiteti;

import marhranj_zadaca_1.helperi.DohvacanjePremaId;
import marhranj_zadaca_1.sucelja.Prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Emisija implements Prototype {

    private int id;
    private String nazivEmisije;
    private long trajanje;
    private List<Osoba> osobe = new ArrayList<>();

    public Emisija(Emisija emisija) {
        if (emisija != null) {
            this.id = emisija.id;
            this.nazivEmisije = emisija.nazivEmisije;
            this.trajanje = emisija.trajanje;
            this.osobe = new ArrayList<>(emisija.osobe);
        }
    }

    public Emisija(String redDatotekeEmisije) throws IllegalArgumentException {
        String[] atributi = redDatotekeEmisije.split("\\s*;\\s*");
        if (atributi.length > 2) {
            try {
                popuniAtribute(atributi);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Neispravan zapis u datoteci emisija: " + redDatotekeEmisije, e);
            }
        } else {
            throw new IllegalArgumentException("Neispravan zapis u datoteci emisija, nedovoljno atributa: " + redDatotekeEmisije);
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

    private void popuniAtribute(String[] atributi) {
        id = Integer.parseInt(atributi[0]);
        nazivEmisije = atributi[1];
        trajanje = Long.parseLong(atributi[2]);
        if (atributi.length > 3) {
            String[] osobeUloge = atributi[3].split("\\s*,\\s*");
            dodajUlogeOsobama(osobeUloge);
        }
    }

    public void dodajUlogeOsobama(String[] osobeUloge) {
        List<Osoba> osobeSaNovimUlogama = Stream.of(osobeUloge)
                .map(osobaUloga -> osobaUloga.split("\\s*-\\s*"))
                .map(this::dodjeliOsobiUlogu)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        osobe.addAll(osobeSaNovimUlogama);
    }

    private Optional<Osoba> dodjeliOsobiUlogu(String[] osobaUloga) throws IllegalArgumentException {
        if (osobaUloga.length > 1) {
            Optional<Osoba> osoba = DohvacanjePremaId.dohvatiOsobuPremaId(Integer.parseInt(osobaUloga[0]));
            Optional<Uloga> uloga = DohvacanjePremaId.dohvatiUloguPremaId(Integer.parseInt(osobaUloga[1]));
            osoba.ifPresent(osoba1 -> uloga.ifPresent(osoba1::setUloga));
            return osoba;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
