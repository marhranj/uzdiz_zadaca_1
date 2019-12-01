package marhranj_zadaca_1.entiteti;

public class Uloga {

    private int id;
    private String nazivUloge;

    public Uloga(String redDatotekeUloga) throws IllegalArgumentException {
        String[] atributi = redDatotekeUloga.split("\\s*;\\s*");
        if (atributi.length > 1) {
            try {
                id = Integer.parseInt(atributi[0]);
                nazivUloge = atributi[1];
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Neispravan zapis u datoteci uloga: " + redDatotekeUloga, e);
            }
        } else {
            throw new IllegalArgumentException("Neispravan zapis u datoteci uloga: " + redDatotekeUloga);
        }
    }

    public int getId() {
        return id;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

}
