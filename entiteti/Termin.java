package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;

public class Termin implements Comparable<Termin> {

    private LocalTime pocetak;
    private LocalTime kraj;
    private Emisija emisija;

    public Termin(LocalTime pocetak, LocalTime kraj, Emisija emisija) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.emisija = emisija;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public Emisija getEmisija() {
        return emisija;
    }

    @Override
    public int compareTo(Termin termin) {
        return this.pocetak.compareTo(termin.getPocetak());
    }

}
