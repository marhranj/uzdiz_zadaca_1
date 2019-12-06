package marhranj_zadaca_1.helperi;

import marhranj_zadaca_1.entiteti.Emisija;
import marhranj_zadaca_1.entiteti.Osoba;
import marhranj_zadaca_1.entiteti.TvKuca;
import marhranj_zadaca_1.entiteti.Uloga;

import java.util.Optional;

public final class DohvacanjeEntiteta {

    private DohvacanjeEntiteta() {}

    public static Optional<Osoba> dohvatiOsobuPremaId(int idOsobe) {
        return TvKuca.dajInstancu().getOsobe()
                .stream()
                .filter(osoba -> osoba.getId() == idOsobe)
                .findFirst()
                .map(osoba -> (Osoba) osoba.clone());
    }

    public static Optional<Uloga> dohvatiUloguPremaId(int idUloge) {
        return TvKuca.dajInstancu().getUloge()
                .stream()
                .filter(uloga -> uloga.getId() == idUloge)
                .findFirst();
    }

    public static Optional<Emisija> dohvatiEmisijuPremaId(int idEmisije) {
        return TvKuca.dajInstancu().getEmisije()
                .stream()
                .filter(emisija -> emisija.getId() == idEmisije)
                .findFirst();
    }

}
