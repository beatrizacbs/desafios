package idwall.desafio;

import idwall.desafio.exception.InvalidTextException;
import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the IdwallFormatter class
 * who implements the format method
 */
public class IdwallFormatterTest {

    private static final Integer DEFAULT_LIMIT = 40;
    private static final Boolean DEFAULT_JUSTIFY = true;

    /**
     * Implements test suite that tests the part 1 of the
     * challenge giving a success result if the output string from the test matches
     * the given the right output string
     */
    @Test
    public void formatUnjustifiedSuccessTest() {
        String inputString = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
        String outputString = "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty, darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters.\n" +
                "\n" +
                "And God said, \"Let there be light,\" and\n" +
                "there was light. God saw that the light\n" +
                "was good, and he separated the light\n" +
                "from the darkness. God called the light\n" +
                "\"day,\" and the darkness he called\n" +
                "\"night.\" And there was evening, and\n" +
                "there was morning - the first day.";

        StringFormatter formatter = new IdwallFormatter(false, DEFAULT_LIMIT);

        try {
            String formatted = formatter.format(inputString);
            assertEquals(outputString, formatted);
        } catch (InvalidTextException e) {
            fail("Teste falhou por causa de uma InvalidTextException");
        }

    }

    /**
     * Tests the break line when formatting a string with empty
     * break line. The test should succeed when the strings are not equal
     */
    @Test
    public void formatUnjustifiedFailLineBreakTest() {
        String inputString = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
        String outputString = "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty, darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters.\n" +
                "And God said, \"Let there be light,\" and\n" +
                "there was light. God saw that the light\n" +
                "was good, and he separated the light\n" +
                "from the darkness. God called the light\n" +
                "\"day,\" and the darkness he called\n" +
                "\"night.\" And there was evening, and\n" +
                "there was morning - the first day.";

        StringFormatter formatter = new IdwallFormatter(false, DEFAULT_LIMIT);

        try {
            String formatted = formatter.format(inputString);
            assertNotEquals(outputString, formatted);
        } catch (InvalidTextException e) {
            fail("Teste falhou por causa de uma InvalidTextException");
        }
    }

    /**
     * This test verifies if the InvalidTextException is thrown
     * to a unjustified string being null
     */
    @Test
    public void formatUnjustifiedNullFailInvalidTextException() {
        StringFormatter formatter = new IdwallFormatter(false, DEFAULT_LIMIT);

        assertThrows(InvalidTextException.class, () -> {
            formatter.format(null);
        });
    }

    /**
     * This test verifies if the InvalidTextException is thrown
     * to a unjustified string being empty
     */
    @Test
    public void formatUnjustifiedEmptyFailInvalidTextException() {
        StringFormatter formatter = new IdwallFormatter(false, DEFAULT_LIMIT);

        assertThrows(InvalidTextException.class, () -> {
            formatter.format("");
        });
    }

    @Test
    public void formatJustifiedSuccessTest() {
        String inputString = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n\nAnd God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
        String outputString = "In the beginning God created the heavens\n" +
                "and   the  earth.   Now  the  earth  was\n" +
                "formless  and empty,  darkness was  over\n" +
                "the  surface of the deep, and the Spirit\n" +
                "of  God was  hovering over  the  waters.\n" +
                "\n" +
                "And  God said, \"Let there be light,\" and\n" +
                "there  was light. God saw that the light\n" +
                "was  good, and  he separated  the  light\n" +
                "from  the darkness. God called the light\n" +
                "\"day,\"   and  the   darkness  he  called\n" +
                "\"night.\"  And  there  was  evening,  and\n" +
                "there  was  morning  -  the  first  day.";

        StringFormatter formatter = new IdwallFormatter(DEFAULT_JUSTIFY, DEFAULT_LIMIT);

        try {
            String formatted = formatter.format(inputString);
            assertEquals(outputString, formatted);
        } catch (InvalidTextException e) {
            fail("Teste falhou por causa de uma InvalidTextException");
        }

    }
}
