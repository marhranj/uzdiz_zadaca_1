package marhranj_zadaca_1;

public class UpravljanjeProgramima {

    public static void main(String... args) {
        ProvjeraParametara provjeraParametara = new ProvjeraParametara();
        if (!provjeraParametara.popuniNaizveDatotekaSaParametrima(args)) {
            System.err.println("Niste popunili sve odgovarajuce parametre");
            System.exit(0);
        }
        System.out.println("Bravo");
    }

}
