/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.utils.jcolor;

/*
 * This is the modified version of https://github.com/dialex/JColor.
 * Licensed by Diogo Nunes under MIT
 */

/**
 * Provides a fluent API to generate
 * <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI escape sequences</a>
 * by specifying {@link AnsiColorAttribute}s of your format.
 */
public class AnsiColor {

    private static final char ESC = 27; // Escape character used to start an ANSI code
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * Every Ansi escape code begins with this PREFIX.
     */
    public static final String PREFIX = ESC + "[";

    /**
     * Two options must be separated by this SEPARATOR.
     */
    public static final String SEPARATOR = ";";

    /**
     * Every Ansi escape code must end with this POSTFIX.
     */
    public static final String POSTFIX = "m";

    /**
     * Shorthand for the Ansi code that resets to the terminal's default format.
     */
    public static final String RESET = PREFIX + AnsiColorAttribute.CLEAR() + POSTFIX;

    /**
     * @param ansiColorAttributes ANSI attributes to format a text.
     * @return The ANSI code that describes all those attributes together.
     */
    public static String generateCode(AnsiColorAttribute... ansiColorAttributes) {
        StringBuilder builder = new StringBuilder();

        builder.append(PREFIX);
        for (Object option : ansiColorAttributes) {
            String code = option.toString();
            if (code.equals(""))
                continue;
            builder.append(code);
            builder.append(SEPARATOR);
        }
        builder.append(POSTFIX);

        // because code must not end with SEPARATOR
        return builder.toString().replace(SEPARATOR + POSTFIX, POSTFIX);
    }

    /**
     * @param attributes Object containing format attributes.
     * @return The ANSI code that describes all those attributes together.
     */
    public static String generateCode(AnsiColorFormat attributes) {
        return generateCode(attributes.toArray());
    }

    /**
     * @param text     String to format.
     * @param ansiCode Ansi code to format each message's lines
     * @return The formatted string, ready to be printed.
     */
    public static String colorize(String text, String ansiCode) {
        StringBuilder output = new StringBuilder();
        boolean endsWithLine = text.endsWith(NEWLINE);

        String[] lines = text.split(NEWLINE);
        /*
         * Every formatted line should:
         * 1) start with a code that sets the format
         * 2) end with a code that resets the format
         * This prevents "spilling" the format to other independent prints, which
         * is noticeable when the background is colored. This method ensures those
         * two rules, even when the original message contains newlines.
         */
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            output.append(ansiCode);
            output.append(line);
            output.append(RESET);
            if (i != lines.length -1)
                output.append(NEWLINE);
            if (endsWithLine)
                output.append(NEWLINE);
        }
        return output.toString();
    }

    /**
     * @param text       String to format.
     * @param ansiColorAttributes ANSI attributes to format a text.
     * @return The formatted string, ready to be printed.
     */
    public static String colorize(String text, AnsiColorAttribute... ansiColorAttributes) {
        String ansiCode = generateCode(ansiColorAttributes);
        return colorize(text, ansiCode);
    }

    /**
     * @param text       String to format.
     * @param attributes Object containing format attributes.
     * @return The formatted string, ready to be printed.
     */
    public static String colorize(String text, AnsiColorFormat attributes) {
        return colorize(text, attributes.toArray());
    }
}
