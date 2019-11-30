package marhranj_zadaca_1;

import marhranj_zadaca_1.entiteti.Emisija;
import marhranj_zadaca_1.entiteti.Osoba;
import marhranj_zadaca_1.entiteti.Uloga;

import java.util.List;

public class MarhranjZadaca1 {

    private static List<Uloga> uloge;
    private static List<Osoba> osobe;
    private static List<Emisija> emisije;

    public static void main(String... args) {
        UpravljacArgumentimaKmdLin upravljacArgumentimaKmdLin = new UpravljacArgumentimaKmdLin(args);
        if (!upravljacArgumentimaKmdLin.popuniNaizveDatoteka()) {
            System.err.println("Niste popunili sve odgovarajuce parametre");
            System.exit(0);
        }
        UpravljacDatotekama upravljacDatotekama = new UpravljacDatotekama(upravljacArgumentimaKmdLin);
        if (!upravljacDatotekama.ucitajDatoteke()) {
            System.err.println("Neispravni nazivi datoteka");
            System.exit(0);
        }
        UcitavacKlasa ucitavacKlasa = new UcitavacKlasa(upravljacDatotekama);
        ucitajKlase(ucitavacKlasa);
        System.out.println("Bravo");
    }

    private static void ucitajKlase(UcitavacKlasa ucitavacKlasa) {
        uloge = ucitavacKlasa.ucitajUloge();
        osobe = ucitavacKlasa.ucitajOsobe();
        emisije = ucitavacKlasa.ucitajEmisije();
    }

    public static List<Uloga> getUloge() {
        return uloge;
    }

    public static List<Osoba> getOsobe() {
        return osobe;
    }

    public static List<Emisija> getEmisije() {
        return emisije;
    }

}
