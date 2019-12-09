package marhranj_zadaca_1.helperi;

import marhranj_zadaca_1.entiteti.Dan;
import marhranj_zadaca_1.entiteti.Program;
import marhranj_zadaca_1.entiteti.TvKuca;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Izbornik {

    public void prikaziIzbornik() {
        Scanner scanner = new Scanner(System.in);
        List<Program> programi = TvKuca.dajInstancu().getProgrami();
        if (!programi.isEmpty()) {
            while (true) {
                try {
                    System.out.println("Izbornik (za prekid upisite 0): ");
                    Program program = dohvatiOdabraniProgram(scanner, programi);
                    Dan dan = dohvatiOdabraniDan(scanner, program);
                    prikaziOpcijeZaDan(scanner, dan);
                } catch (NumberFormatException e){
                    System.err.println("Morate unijeti broj");
                }
            }
        } else {
            System.err.println("Niti jedan program nije inicijaliziran");
        }
    }

    private Program dohvatiOdabraniProgram(Scanner scanner, List<Program> programi) {
        int odabir;
        do {
            System.out.println("Odaberite program: ");
            IntStream.range(0, programi.size())
                    .forEach(i -> System.out.println(i + 1 + ". " + programi.get(i).getNaziv()));
            odabir = Integer.parseInt(scanner.nextLine());
            if (odabir < 0 || odabir > programi.size()) {
                System.err.println("Niste unijeli ispravan broj");
            }
        } while (odabir < 0 || odabir > programi.size());

        if (odabir == 0) {
            System.exit(0);
        }

        return programi.get(odabir - 1);
    }

    private Dan dohvatiOdabraniDan(Scanner scanner, Program program) {
        int odabir;
        do {
            System.out.println("Odaberite dan za koji zelite ispis programa: ");
            IntStream.rangeClosed(1, 7)
                    .forEach(i -> System.out.println(i + ". " + program.getRaspored().dohvatiDanPremaIndexu(i).getNaziv()));
            odabir = Integer.parseInt(scanner.nextLine());
            if (odabir < 0 || odabir > 7) {
                System.err.println("Niste unijeli ispravan dan");
            }
        } while (odabir < 0 || odabir > 7);

        if (odabir == 0) {
            System.exit(0);
        }

        return program.getRaspored().dohvatiDanPremaIndexu(odabir);
    }

    private void prikaziOpcijeZaDan(Scanner scanner, Dan dan) {
        int odabir;
        do {
            System.out.println("Odaberite opciju koju zelite za dan: ");
            System.out.println("1. Prikaz rasporeda za dan: " + dan.getNaziv());
            System.out.println("2. Prikaz statistike za dan: " + dan.getNaziv());
            odabir = Integer.parseInt(scanner.nextLine());
             if (odabir < 0 || odabir > 2) {
                System.err.println("Niste unijeli ispravan odabir");
            }
        } while (odabir < 0 || odabir > 2);

        if (odabir == 0) {
            System.exit(0);
        } else if (odabir == 1) {
            System.out.println(dan.dohvatiTermineZaDan());
        } else {
            System.out.println(dan.dohvatiStatistikuZaDan());
        }
    }

}
