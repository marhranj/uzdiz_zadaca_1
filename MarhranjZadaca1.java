package marhranj_zadaca_1;

import marhranj_zadaca_1.entiteti.Program;
import marhranj_zadaca_1.entiteti.TvKuca;
import marhranj_zadaca_1.helperi.UcitacKlasa;
import marhranj_zadaca_1.helperi.UpravljacArgumentimaKmdLin;
import marhranj_zadaca_1.helperi.UpravljacDatotekama;

import java.util.List;
import java.util.Scanner;

public class MarhranjZadaca1 {

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
        UcitacKlasa ucitacKlasa = new UcitacKlasa(upravljacDatotekama);
        postaviZnacajkeTvKuce(ucitacKlasa);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Odaberite program: ");
        List<Program> programi = TvKuca.dajInstancu().getProgrami();
        for (int i = 0; i < programi.size(); i++) {
            System.out.println(i + 1 + ". " + programi.get(i).getNaziv());
        }
        int odabir = 0;
        try {
            odabir = Integer.parseInt(scanner.nextLine());
            if (odabir < 0 || odabir > programi.size()) {
                System.err.println("Niste unijeli ispravan broj");
                System.exit(0);
            }
            Program program = programi.get(odabir - 1);
            System.out.println("Odaberite dan za koji zelite ispis programa: ");
            for (int i = 1; i <= 7; i++) {
                System.out.println(i + ". " + program.getRaspored().dohvatiDanPremaIndexu(i).getNaziv());
            }
            odabir = Integer.parseInt(scanner.nextLine());
            if (odabir < 0 || odabir > 7) {
                System.err.println("Niste unijeli ispravan dan");
                System.exit(0);
            }
            System.out.println("Odaberite dan za koji zelite ispis programa: ");
            System.out.println(program.getRaspored().dohvatiDanPremaIndexu(odabir));
            System.out.println(program.getRaspored().dohvatiDanPremaIndexu(odabir).dohvatiStatistikuZaDan());
        } catch (NumberFormatException e){
            System.err.println("Morate unijeti broj");
            System.exit(0);
        }

    }

    private static void postaviZnacajkeTvKuce(UcitacKlasa ucitacKlasa) {
        TvKuca tvKuca = TvKuca.dajInstancu();
        tvKuca.setUloge(ucitacKlasa.ucitajUloge());
        tvKuca.setOsobe(ucitacKlasa.ucitajOsobe());
        tvKuca.setEmisije(ucitacKlasa.ucitajEmisije());
        tvKuca.setProgrami(ucitacKlasa.ucitajPrograme());
    }

}
