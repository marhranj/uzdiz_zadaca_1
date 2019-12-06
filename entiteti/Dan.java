package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Dan {

    private LocalTime pocetakEmitiranja;
    private LocalTime krajEmitiranja;
    private String naziv;

    private List<Termin> termini = new ArrayList<>();

    public Dan(LocalTime pocetakEmitiranja, LocalTime krajEmitiranja, String naziv) {
        this.pocetakEmitiranja = pocetakEmitiranja;
        this.krajEmitiranja = krajEmitiranja;
        this.naziv = naziv;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public boolean dodajEmisiju(LocalTime pocetak, Emisija emisija) {
        boolean uspjesnoDodano = false;
        LocalTime kraj = pocetak.plusMinutes(emisija.getTrajanje());
        if (!zauzetTermin(pocetak, kraj) && unutarVremenaEmitiranjaPrograma(pocetak, kraj)) {
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
        LocalTime kraj = pocetak.plusMinutes(emisija.getTrajanje());
        if (unutarVremenaEmitiranjaPrograma(pocetak, kraj)) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
            uspjesnoDodano = true;
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak + " na dan " + naziv);
        }
        return uspjesnoDodano;
    }

    private LocalTime pronadjiSlobodnoVrijeme(Emisija emisija) {
        LocalTime pocetakEmisije = this.pocetakEmitiranja;
        LocalTime krajEmisije = pocetakEmisije.plusMinutes(emisija.getTrajanje());
        int i = 0;
        while (zauzetTermin(pocetakEmisije, krajEmisije) && unutarVremenaEmitiranjaPrograma(pocetakEmisije, krajEmisije)) {
            pocetakEmisije = termini.get(i++).getKraj();
            krajEmisije = pocetakEmisije.plusMinutes(emisija.getTrajanje());
        }
        return pocetakEmisije;
    }

    private boolean zauzetTermin(LocalTime pocetak, LocalTime kraj) {
        Predicate<Termin> unutarVremena = termin ->
                ((pocetak.equals(termin.getPocetak()) || pocetak.isAfter(termin.getPocetak())) && pocetak.isBefore(termin.getKraj())) ||
                ((kraj.equals(termin.getKraj()) || kraj.isBefore(termin.getKraj())) && kraj.isAfter(termin.getPocetak())) ;
        return termini.stream()
                .anyMatch(unutarVremena);
    }

    private boolean unutarVremenaEmitiranjaPrograma(LocalTime pocetak, LocalTime kraj) {
        return (pocetak.isAfter(this.pocetakEmitiranja) || pocetak.equals(this.pocetakEmitiranja))
                && (kraj.isBefore(this.krajEmitiranja) || kraj.equals(this.krajEmitiranja));
    }

}
