package marhranj_zadaca_1.entiteti;

import marhranj_zadaca_1.helperi.VremenaUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Dan {

    private LocalTime pocetakEmitiranja;
    private LocalTime krajEmitiranja;
    private String naziv;

    private List<Termin> termini = new ArrayList<>();

    public Dan(LocalTime pocetakEmitiranja, LocalTime krajEmitiranja, String naziv) {
        this.pocetakEmitiranja = pocetakEmitiranja;
        this.krajEmitiranja = krajEmitiranja;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public boolean dodajEmisiju(LocalTime pocetak, Emisija emisija) {
        boolean uspjesnoDodano = false;
        LocalTime kraj = pocetak.plusMinutes(emisija.getTrajanje());
        if (!zauzetTermin(pocetak, kraj) && unutarVremenaEmitiranjaPrograma(pocetak, kraj)) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
            uspjesnoDodano = true;
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak + " na dan " + naziv);
        }
        return uspjesnoDodano;
    }

    public boolean dodajEmisiju(Emisija emisija) {
        boolean uspjesnoDodano = false;
        LocalTime pocetak = pronadjiSlobodnoVrijeme(emisija);
        LocalTime kraj = pocetak.plusMinutes(emisija.getTrajanje());
        if (unutarVremenaEmitiranjaPrograma(pocetak, kraj)) {
            termini.add(new Termin(pocetak, pocetak.plusMinutes(emisija.getTrajanje()), emisija));
            Collections.sort(termini);
            uspjesnoDodano = true;
        } else {
            System.err.println("Nije moguce dodati emisiju: " + emisija.getNazivEmisije() + ", u " + pocetak + " na dan " + naziv);
        }
        return uspjesnoDodano;
    }

    public String dohvatiStatistikuZaDan() {
        double minuteEmitiranjaPrograma = Duration.between(pocetakEmitiranja, krajEmitiranja).toMinutes();
        double minuteEmitiranjaSignala = 1440L - minuteEmitiranjaPrograma;
        double minuteEmitiranjaEmisija = termini.stream()
                .mapToDouble(termin -> Duration.between(termin.getPocetak(), termin.getKraj()).toMinutes())
                .sum();
        double minuteSlobodnogVremena = minuteEmitiranjaPrograma - minuteEmitiranjaEmisija;
        String ispis = String.format("%-30s %-30s %-30s %n", "Emitiranje signala (%)", "Emitiranje emisija (%)", "Slobodno vrijeme (%)");
        ispis += String.format("%-30.2f %-30.2f %-30.2f %n", minuteEmitiranjaSignala/14.40D, minuteEmitiranjaEmisija/14.40D, minuteSlobodnogVremena/14.40D);
        return ispis;
    }

    @Override
    public String toString() {
        StringBuilder ispis = new StringBuilder(naziv + ":" + System.lineSeparator());
        for (Termin termin : termini) {
            ispis.append(String.format("%-40s %5s %10s %n", termin.getEmisija().getNazivEmisije(), termin.getPocetak().toString(), termin.getKraj().toString()));
        }
        return ispis.toString();
    }

    private LocalTime pronadjiSlobodnoVrijeme(Emisija emisija) {
        LocalTime pocetakEmisije = termini.size() > 1 ?  termini.get(0).getKraj() : this.pocetakEmitiranja;
        LocalTime krajEmisije = pocetakEmisije.plusMinutes(emisija.getTrajanje());
        int i = 0;
        while (zauzetTermin(pocetakEmisije, krajEmisije) && unutarVremenaEmitiranjaPrograma(pocetakEmisije, krajEmisije)) {
            pocetakEmisije = termini.get(i++).getKraj();
            krajEmisije = pocetakEmisije.plusMinutes(emisija.getTrajanje());
        }
        return pocetakEmisije;
    }

    private boolean zauzetTermin(LocalTime pocetak, LocalTime kraj) {
        Predicate<Termin> unutarVremena = termin ->
                (VremenaUtils.prijeIliUIstoVrijeme(termin.getPocetak(), pocetak) && pocetak.isBefore(termin.getKraj())) ||
                (VremenaUtils.poslijeIliUIstoVrijeme(termin.getKraj(), kraj) && kraj.isAfter(termin.getPocetak()));
        return termini.stream()
                .anyMatch(unutarVremena);
    }

    private boolean unutarVremenaEmitiranjaPrograma(LocalTime pocetak, LocalTime kraj) {
        return VremenaUtils.poslijeIliUIstoVrijeme(pocetak, this.pocetakEmitiranja)
                && VremenaUtils.prijeIliUIstoVrijeme(kraj, this.krajEmitiranja);
    }

}
