package marhranj_zadaca_1;

import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UpravljanjeProgramima {

    private static String datotekaEmisije;
    private static String datotekaOsobe;
    private static String datotekaTvKuca;
    private static String datotekaUloge;

    public static void main(String... args) {
        if (!popuniNaizveDatotekaSaParametrima(pretvoriUMapuParametarVrijednost(args))) {
            System.err.println("Niste popunili sve odgovarajuce parametre");
            System.exit(0);
        }
        System.out.println("Bravo");
    }

    private static Map<String, String> pretvoriUMapuParametarVrijednost(String[] argumenti) {
        BinaryOperator<String> ignorirajDuplikate = (vrijednostJedan, vrijednostDva) -> vrijednostDva;
        return IntStream.range(0, argumenti.length / 2)
                .boxed()
                .collect(Collectors.toMap(dohvatiKeyMapper(argumenti), dohvatiValueMapper(argumenti), ignorirajDuplikate));
    }

    private static Function<Integer, String> dohvatiValueMapper(String[] argumenti) {
        return i -> argumenti[2 * i + 1];
    }

    private static Function<Integer, String> dohvatiKeyMapper(String[] argumenti) {
        return i -> {
            String argument = argumenti[2 * i];
            IntPredicate ukloniCrtuSaPrveDvijePozicije = character -> argument.indexOf(character) < 2 && (char) character == '-';
            return argument.chars()
                    .filter(ukloniCrtuSaPrveDvijePozicije.negate())
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString()
                    .toLowerCase();
        };
    }

    private static boolean popuniNaizveDatotekaSaParametrima(Map<String, String> parametri) {
        datotekaOsobe = parametri.get("o");
        datotekaEmisije = parametri.get("e");
        datotekaTvKuca = parametri.get("t");
        datotekaUloge = parametri.get("u");
        return Stream.of(datotekaOsobe, datotekaEmisije, datotekaTvKuca, datotekaUloge).allMatch(Objects::nonNull);
    }

}
