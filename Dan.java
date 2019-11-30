package marhranj_zadaca_1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dan {

    private List<Termin> termini = new ArrayList<>();

    public List<Termin> getTermini() {
        return termini;
    }

    private boolean dodajEmisiju(String pocetak, Emisija emisija) {
        LocalTime pocetakLocalTime = LocalTime.parse(pocetak);
        termini.add(new Termin(pocetakLocalTime, pocetakLocalTime.plusMinutes(emisija.getTrajanje()), emisija));
        return true;
    }

}
