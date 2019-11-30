package marhranj_zadaca_1;

public class Uloga {

    private int id;
    private String nazivUloge;

    public Uloga(String redDatotekeUloga) throws IllegalArgumentException {
        IllegalArgumentException exception = new IllegalArgumentException("Neispravan zapis u datoteci uloga: " + redDatotekeUloga);
        String[] atributi = redDatotekeUloga.split("\\s*;\\s*");
        if (atributi.length > 1) {
            try {
                id = Integer.parseInt(atributi[0]);
                nazivUloge = atributi[1];
            } catch (NumberFormatException e) {
                throw exception;
            }
        } else {
            throw exception;
        }
    }

    public int getId() {
        return id;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

}
