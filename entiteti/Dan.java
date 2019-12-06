package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dan {

    private LocalTime pocetak;
    private LocalTime kraj;

    private List<Termin> termini = new ArrayList<>();

    public Dan(LocalTime pocetak, LocalTime kraj) {
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void dodajEmisiju(LocalTime pocetak, Emisija emisija) {
        if (!zauzetTermin(pocetak) && unutarVremena(pocetak, emisija)) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak);
        }
    }

    public void dodajEmisiju(Emisija emisija) {
        //pronadji slobodan termin
        termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
        Collections.sort(termini);
    }

    private boolean zauzetTermin(LocalTime pocetak) {
        return termini.stream()
                .anyMatch(termin -> (pocetak.equals(termin.getPocetak()) || pocetak.isAfter(termin.getPocetak()) && pocetak.isBefore(termin.getKraj())));
    }

    private boolean unutarVremena(LocalTime pocetak, Emisija emisija) {
        return pocetak.isAfter(this.pocetak)
                && pocetak.plusMinutes(emisija.getTrajanje()).isBefore(this.kraj);
    }

}
