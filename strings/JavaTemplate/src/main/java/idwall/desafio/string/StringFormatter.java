package idwall.desafio.string;

import idwall.desafio.exception.InvalidTextException;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    protected Integer limit;

    public StringFormatter(int limit) {
        this.limit = limit;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public abstract String format(String text) throws InvalidTextException;
}
