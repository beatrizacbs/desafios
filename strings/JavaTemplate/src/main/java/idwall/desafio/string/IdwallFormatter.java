package idwall.desafio.string;

import idwall.desafio.exception.InvalidTextException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    private boolean shouldJustify;

    public IdwallFormatter(boolean shouldJustify, int limit) {
        super(limit);
        this.shouldJustify = shouldJustify;
    }

    /**
     * Should format as described in the challenge
     *
     * @param text the text to be formatted
     * @return the text formatted according to the interface's attributes
     */
    @Override
    public String format(String text) throws InvalidTextException {

        if (text == null || text.isEmpty()) {
            throw new InvalidTextException("O texto não pode ser nulo ou vazio");
        }

        String[] words = text.replace("\n", " \n ").split(" ");

        String line = "";
        List<String> formattedLines = new ArrayList<>();

        for (int index = 0; index <= words.length - 1; index++) {
            String word = words[index];
            int lineCount = line.length() + word.length() + 1;
            if (word.equals("\n")) {
                formattedLines.add(line);
                formattedLines.add(word);
                line = "";
            } else if (lineCount > this.limit) {
                formattedLines.add(String.format("%s\n", line));
                line = String.format("%s", word);
            } else if (line.isEmpty()) {
                line = word;
            } else {
                line = String.format("%s %s", line, word);
                if (index == words.length - 1) {
                    formattedLines.add(line);
                }
            }
        }

        if (this.shouldJustify) {
            return this.justifyLines(formattedLines);
        }

        return String.join("", formattedLines);

    }

    /**
     * Method responsible to receive a list of string and justify
     * each one returning the text justified (following the order in the list)
     *
     * @param lines list of lines that need to be justified
     * @return the complete text concatenating the strings with spaces
     */
    private String justifyLines(List<String> lines) {
        List<String> result = new ArrayList<>();
        lines.forEach(line -> {

            int lineLength = line.replace("\n", "").length();
            int lineTrim = line.replaceAll("[\\s]", "").length();
            int spaceCount = lineLength - lineTrim;
            int remainingChars = this.limit - lineLength;

            if (remainingChars == 0 || spaceCount == 0) {
                result.add(line);
                return;
            }

            int mod = remainingChars % spaceCount;
            String[] words = line.split(" ");
            for (int i = 0; i < mod && i < words.length; i++) {
                int index = i + (i % mod);
                if (index < words.length - 1) {
                    words[index] = String.format("%s ", words[index]);
                } else {
                    words[index - 1] = String.format("%s ", words[index - 1]);
                }
            }
            line = String.join(" ", words);

            int quo = remainingChars / spaceCount;
            while (quo > 0) {
                line = line.replaceAll(" +", "$0 ");
                quo--;
            }

            result.add(line);
        });

        return String.join("", result);
    }
}
