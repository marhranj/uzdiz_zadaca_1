package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Dan {

    private LocalTime pocetak;
    private LocalTime kraj;
    private String naziv;

    private List<Termin> termini = new ArrayList<>();

    public Dan(LocalTime pocetak, LocalTime kraj, String naziv) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.naziv = naziv;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public boolean dodajEmisiju(LocalTime pocetak, Emisija emisija) {
        boolean uspjesnoDodano = false;
        if (!zauzetTermin(pocetak, pocetak.plusMinutes(emisija.getTrajanje())) && unutarVremena(pocetak, emisija)) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
            uspjesnoDodano = true;
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak + " na dan " + naziv);
        }
        return uspjesnoDodano;
    }

    public boolean dodajEmisiju(Emisija emisija) {
        boolean uspjesnoDodano = false;
        LocalTime pocetak = pronadjiSlobodnoVrijeme(emisija);
        if (prijeKrajaEmitiranjaDana(pocetak.plusMinutes(emisija.getTrajanje()))) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
            uspjesnoDodano = true;
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak + " na dan " + naziv);
        }
        return uspjesnoDodano;
    }

    private LocalTime pronadjiSlobodnoVrijeme(Emisija emisija) {
        LocalTime pocetakEmisije = this.pocetak;
        LocalTime krajEmisije = pocetakEmisije.plusMinutes(emisija.getTrajanje());
        int i = 0;
        while (zauzetTermin(pocetakEmisije, krajEmisije) && prijeKrajaEmitiranjaDana(krajEmisije)) {
            pocetakEmisije = termini.get(i++).getKraj();
            krajEmisije = pocetakEmisije.plusMinutes(emisija.getTrajanje());
        }
        return pocetakEmisije;
    }

    private boolean prijeKrajaEmitiranjaDana(LocalTime vrijeme) {
        return vrijeme.isBefore(this.kraj) || vrijeme.equals(this.kraj);
    }

    private boolean zauzetTermin(LocalTime pocetak, LocalTime kraj) {
        Predicate<Termin> unutarVremena = termin ->
                ((pocetak.equals(termin.getPocetak()) || pocetak.isAfter(termin.getPocetak())) && pocetak.isBefore(termin.getKraj())) ||
                ((kraj.equals(termin.getKraj()) || kraj.isBefore(termin.getKraj())) && kraj.isAfter(termin.getPocetak())) ;
        return termini.stream()
                .anyMatch(unutarVremena);
    }

    private boolean unutarVremena(LocalTime pocetak, Emisija emisija) {
        return pocetak.isAfter(this.pocetak)
                && pocetak.plusMinutes(emisija.getTrajanje()).isBefore(this.kraj);
    }

}
