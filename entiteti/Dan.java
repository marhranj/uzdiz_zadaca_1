package marhranj_zadaca_1.entiteti;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dan {

    private List<Termin> termini = new ArrayList<>();

    public List<Termin> getTermini() {
        return termini;
    }

    public void dodajEmisiju(LocalTime pocetak, Emisija emisija) {
        if (!zauzetTermin(pocetak)) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak);
        }
    }

    private boolean zauzetTermin(LocalTime pocetak) {
        return termini.stream()
                .anyMatch(termin -> (pocetak.equals(termin.getPocetak()) || pocetak.isAfter(termin.getPocetak()) && pocetak.isBefore(termin.getKraj())));
    }

}
