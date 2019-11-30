package marhranj_zadaca_1.entiteti;

import marhranj_zadaca_1.Prototype;

public class Osoba implements Prototype {

    private int id;
    private String imePrezime;
    private Uloga uloga;

    public Osoba(Osoba osoba) {
        if (osoba != null) {
            this.id = osoba.id;
            this.imePrezime = osoba.imePrezime;
            this.uloga = osoba.uloga;
        }
    }

    public Osoba(String redDatotekeUloga) throws IllegalArgumentException {
        IllegalArgumentException exception = new IllegalArgumentException("Neispravan zapis u datoteci osoba: " + redDatotekeUloga);
        String[] atributi = redDatotekeUloga.split("\\s*;\\s*");
        if (atributi.length > 1) {
            try {
                id = Integer.parseInt(atributi[0]);
                imePrezime = atributi[1];
            } catch (NumberFormatException e) {
                throw exception;
            }
        } else {
            throw exception;
        }
    }

    public int getId() {
        return id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    @Override
    public Prototype clone() {
        return new Osoba(this);
    }

}
