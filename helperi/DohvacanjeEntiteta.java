package marhranj_zadaca_1.helperi;

import marhranj_zadaca_1.entiteti.Emisija;
import marhranj_zadaca_1.entiteti.Osoba;
import marhranj_zadaca_1.entiteti.TvKuca;
import marhranj_zadaca_1.entiteti.Uloga;

import java.util.Optional;

public final class DohvacanjeEntiteta {

    private DohvacanjeEntiteta() {}

    public static Optional<Osoba> dohvatiOsobuPremaId(int idOsobe) {
        Optional<Osoba> osobaId = TvKuca.dajInstancu().getOsobe()
                .stream()
                .filter(osoba -> osoba.getId() == idOsobe)
                .map(osoba -> (Osoba) osoba.clone())
                .findFirst();
        if (!osobaId.isPresent()) {
            System.err.println("Nije pronađena osoba sa id " + osobaId);
        }
        return osobaId;
    }

    public static Optional<Uloga> dohvatiUloguPremaId(int idUloge) {
        Optional<Uloga> ulogaId = TvKuca.dajInstancu().getUloge()
                .stream()
                .filter(uloga -> uloga.getId() == idUloge)
                .findFirst();
        if (!ulogaId.isPresent()) {
            System.err.println("Nije pronađena uloga sa id " + ulogaId);
        }
        return ulogaId;
    }

    public static Optional<Emisija> dohvatiEmisijuPremaId(int idEmisije) {
        Optional<Emisija> emisijaSaId = TvKuca.dajInstancu().getEmisije()
                .stream()
                .filter(emisija -> emisija.getId() == idEmisije)
                .map(emisija -> (Emisija) emisija.clone())
                .findFirst();
        if (!emisijaSaId.isPresent()) {
            System.err.println("Nije pronađena emisija sa id " + idEmisije);
        }
        return emisijaSaId;
    }

}
