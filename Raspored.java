package marhranj_zadaca_1;

public class Raspored {

    private Dan ponedjeljak;
    private Dan utorak;
    private Dan srijeda;
    private Dan cetvrtak;
    private Dan petak;
    private Dan subota;
    private Dan nedjelja;

    public Raspored() {
        ponedjeljak = new Dan();
        utorak = new Dan();
        srijeda = new Dan();
        cetvrtak = new Dan();
        petak = new Dan();
        subota = new Dan();
        subota = new Dan();
        nedjelja = new Dan();
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
}
