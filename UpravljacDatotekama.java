package marhranj_zadaca_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpravljacDatotekama {

    private UpravljacArgumentimaKmdLin upravljacArgumentimaKmdLin;

    private String datotekaEmisije;
    private String datotekaOsobe;
    private String datotekaTvKuca;
    private String datotekaUloge;

    public UpravljacDatotekama(UpravljacArgumentimaKmdLin upravljacArgumentimaKmdLin) {
        this.upravljacArgumentimaKmdLin = upravljacArgumentimaKmdLin;
    }

    public boolean ucitajDatoteke() {
        try {
            datotekaEmisije = new String(Files.readAllBytes(Paths.get(upravljacArgumentimaKmdLin.getDatotekaEmisije())));
            datotekaOsobe = new String(Files.readAllBytes(Paths.get(upravljacArgumentimaKmdLin.getDatotekaOsobe())));
            datotekaTvKuca = new String(Files.readAllBytes(Paths.get(upravljacArgumentimaKmdLin.getDatotekaTvKuca())));
            datotekaUloge = new String(Files.readAllBytes(Paths.get(upravljacArgumentimaKmdLin.getDatotekaUloge())));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String getDatotekaEmisije() {
        return datotekaEmisije;
    }

    public String getDatotekaOsobe() {
        return datotekaOsobe;
    }

    public String getDatotekaTvKuca() {
        return datotekaTvKuca;
    }

    public String getDatotekaUloge() {
        return datotekaUloge;
    }

}
