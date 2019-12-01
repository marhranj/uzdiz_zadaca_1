package marhranj_zadaca_1.entiteti;

import java.util.List;

public class TvKuca {

    private static TvKuca instanca;

    private List<Uloga> uloge;
    private List<Osoba> osobe;
    private List<Emisija> emisije;
    private List<Program> programi;

    static {
        instanca = new TvKuca();
    }

    private TvKuca() {}

    public static TvKuca dajInstancu() {
        return instanca;
    }

    public List<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    public void setOsobe(List<Osoba> osobe) {
        this.osobe = osobe;
    }

    public List<Emisija> getEmisije() {
        return emisije;
    }

    public void setEmisije(List<Emisija> emisije) {
        this.emisije = emisije;
    }

    public List<Program> getProgrami() {
        return programi;
    }

    public void setProgrami(List<Program> programi) {
        this.programi = programi;
    }

}
