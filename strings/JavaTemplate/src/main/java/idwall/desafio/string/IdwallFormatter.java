package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;

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
    public String format(String text) {
        String [] words = text.replace("\n", " \n ").split(" ");

        String line = "";
        List<String> formattedLines = new ArrayList<>();

        for (int index = 0; index <= words.length - 1 ; index++) {
            String word = words[index];
            int lineCount = line.length() + word.length() + 1;
            if(word.equals("\n")) {
                formattedLines.add(line);
                formattedLines.add(word);
                line = "";
            } else if(lineCount > this.limit) {
                formattedLines.add(String.format("%s\n", line));
                line = String.format("%s", word);
            }else if(line.isEmpty()){
                line = word;
            }else {
                line = String.format("%s %s", line, word);
                if(index == words.length - 1) {
                    formattedLines.add(line);
                }
            }
        }

        if(this.shouldJustify) {
            return this.justifyLines(formattedLines);
        }

        StringBuilder finalText = new StringBuilder();

        formattedLines.forEach(finalText::append);
        return finalText.toString();

    }

    private String justifyLines(List<String> lines) {
        return null;
    }
}
