package marhranj_zadaca_1.helperi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpravljacDatotekama {

    private UpravljacArgumentimaKmdLin upravljacArgumentimaKmdLin;

    private String datotekaEmisije;
    private String datotekaOsobe;
    private String datotekaTvKuca;
    private String datotekaUloge;

    public UpravljacDatotekama() {
    }

    public UpravljacDatotekama(UpravljacArgumentimaKmdLin upravljacArgumentimaKmdLin) {
        this.upravljacArgumentimaKmdLin = upravljacArgumentimaKmdLin;
    }

    public boolean ucitajDatoteke() {
        try {
            datotekaEmisije = procitajDatoteku(upravljacArgumentimaKmdLin.getDatotekaEmisije());
            datotekaOsobe = procitajDatoteku(upravljacArgumentimaKmdLin.getDatotekaOsobe());
            datotekaTvKuca = procitajDatoteku(upravljacArgumentimaKmdLin.getDatotekaTvKuca());
            datotekaUloge = procitajDatoteku(upravljacArgumentimaKmdLin.getDatotekaUloge());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String procitajDatoteku(String nazivDatoteke) throws IOException {
        return new String(Files.readAllBytes(Paths.get(nazivDatoteke)));
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
