package marhranj_zadaca_1.entiteti;

import marhranj_zadaca_1.helperi.UpravljacDatotekama;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Program {

    private int id;
    private String naziv;
    private LocalTime pocetak;
    private LocalTime kraj;
    private String nazivDatotekeRasporeda;
    private Raspored raspored;

    public Program(String redDatotekePrograma) {
        IllegalArgumentException exception = new IllegalArgumentException("Neispravan zapis u datoteci programa: " + redDatotekePrograma);
        String[] atributi = redDatotekePrograma.split("\\s*;\\s*");
        if (atributi.length > 4) {
            try {
                popuniAtribute(atributi);
                raspored = new Raspored(pocetak, kraj, new UpravljacDatotekama().procitajDatoteku(nazivDatotekeRasporeda));
            } catch (IllegalArgumentException | DateTimeParseException | IOException e) {
                throw exception;
            }
        } else {
            throw exception;
        }
    }

    private void popuniAtribute(String[] atributi) {
        id = Integer.parseInt(atributi[0]);
        naziv = atributi[1];
        pocetak = LocalTime.parse(urediVrijeme(atributi[2]));
        kraj = atributi[3].isEmpty() ? LocalTime.of(0, 0) : LocalTime.parse(urediVrijeme(atributi[3]));
        nazivDatotekeRasporeda = atributi[4];
    }

    private String urediVrijeme(String vrijeme) {
        if (vrijeme.length() == 4) {
            return "0" + vrijeme;
        }
        return vrijeme;
    }

    public Raspored getRaspored() {
        return raspored;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getId() {
        return id;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public String getNazivDatotekeRasporeda() {
        return nazivDatotekeRasporeda;
    }

}
