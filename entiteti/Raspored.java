package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;

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

}
