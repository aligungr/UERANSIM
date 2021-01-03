/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.jcolor;

/*
 * This is the modified version of https://github.com/dialex/JColor.
 * Licensed by Diogo Nunes under MIT
 */

/**
 * Abstracts ANSI codes with intuitive names. It maps a description (e.g. RED_TEXT) with a code (e.g. 31).
 *
 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#Escape_sequences">Wikipedia, for a list of all codes available</a>
 * @see <a href="https://stackoverflow.com/questions/4842424/list-of-ansi-color-escape-sequences/33206814#33206814">StackOverflow, for a list of codes with examples</a>
 */
public abstract class AnsiColorAttribute {

    public static AnsiColorAttribute NONE() {
        return new SimpleAttribute("");
    }

    /*
    =========================
    API for simple attributes
    =========================
    */

    // Effects

    public static AnsiColorAttribute CLEAR() {
        return new SimpleAttribute("0");
    }

    public static AnsiColorAttribute BOLD() {
        return new SimpleAttribute("1");
    }

    /**
     * @return Alias of BOLD().
     */
    public static AnsiColorAttribute SATURATED() {
        return new SimpleAttribute("1");
    }

    public static AnsiColorAttribute DIM() {
        return new SimpleAttribute("2");
    }

    /**
     * @return Alias of DIM().
     */
    public static AnsiColorAttribute DESATURATED() {
        return new SimpleAttribute("2");
    }

    public static AnsiColorAttribute ITALIC() {
        return new SimpleAttribute("3");
    }

    public static AnsiColorAttribute UNDERLINE() {
        return new SimpleAttribute("4");
    }

    public static AnsiColorAttribute SLOW_BLINK() {
        return new SimpleAttribute("5");
    }

    public static AnsiColorAttribute RAPID_BLINK() {
        return new SimpleAttribute("6");
    }

    public static AnsiColorAttribute REVERSE() {
        return new SimpleAttribute("7");
    }

    public static AnsiColorAttribute HIDDEN() {
        return new SimpleAttribute("8");
    }

    public static AnsiColorAttribute STRIKETHROUGH() {
        return new SimpleAttribute("9");
    }

    public static AnsiColorAttribute BLACK_TEXT() {
        return new SimpleAttribute("30");
    }

    // Colors (foreground)

    public static AnsiColorAttribute RED_TEXT() {
        return new SimpleAttribute("31");
    }

    public static AnsiColorAttribute GREEN_TEXT() {
        return new SimpleAttribute("32");
    }

    public static AnsiColorAttribute YELLOW_TEXT() {
        return new SimpleAttribute("33");
    }

    public static AnsiColorAttribute BLUE_TEXT() {
        return new SimpleAttribute("34");
    }

    public static AnsiColorAttribute MAGENTA_TEXT() {
        return new SimpleAttribute("35");
    }

    public static AnsiColorAttribute CYAN_TEXT() {
        return new SimpleAttribute("36");
    }

    public static AnsiColorAttribute WHITE_TEXT() {
        return new SimpleAttribute("37");
    }

    public static AnsiColorAttribute BLACK_BACK() {
        return new SimpleAttribute("40");
    }

    // Colors (background)

    public static AnsiColorAttribute RED_BACK() {
        return new SimpleAttribute("41");
    }

    public static AnsiColorAttribute GREEN_BACK() {
        return new SimpleAttribute("42");
    }

    public static AnsiColorAttribute YELLOW_BACK() {
        return new SimpleAttribute("43");
    }

    public static AnsiColorAttribute BLUE_BACK() {
        return new SimpleAttribute("44");
    }

    public static AnsiColorAttribute MAGENTA_BACK() {
        return new SimpleAttribute("45");
    }

    public static AnsiColorAttribute CYAN_BACK() {
        return new SimpleAttribute("46");
    }

    public static AnsiColorAttribute WHITE_BACK() {
        return new SimpleAttribute("47");
    }

    public static AnsiColorAttribute BRIGHT_BLACK_TEXT() {
        return new SimpleAttribute("90");
    }

    // Bright colors (foreground)

    public static AnsiColorAttribute BRIGHT_RED_TEXT() {
        return new SimpleAttribute("91");
    }

    public static AnsiColorAttribute BRIGHT_GREEN_TEXT() {
        return new SimpleAttribute("92");
    }

    public static AnsiColorAttribute BRIGHT_YELLOW_TEXT() {
        return new SimpleAttribute("93");
    }

    public static AnsiColorAttribute BRIGHT_BLUE_TEXT() {
        return new SimpleAttribute("94");
    }

    public static AnsiColorAttribute BRIGHT_MAGENTA_TEXT() {
        return new SimpleAttribute("95");
    }

    public static AnsiColorAttribute BRIGHT_CYAN_TEXT() {
        return new SimpleAttribute("96");
    }

    public static AnsiColorAttribute BRIGHT_WHITE_TEXT() {
        return new SimpleAttribute("97");
    }

    public static AnsiColorAttribute BRIGHT_BLACK_BACK() {
        return new SimpleAttribute("100");
    }

    // Bright colors (background)

    public static AnsiColorAttribute BRIGHT_RED_BACK() {
        return new SimpleAttribute("101");
    }

    public static AnsiColorAttribute BRIGHT_GREEN_BACK() {
        return new SimpleAttribute("102");
    }

    public static AnsiColorAttribute BRIGHT_YELLOW_BACK() {
        return new SimpleAttribute("103");
    }

    public static AnsiColorAttribute BRIGHT_BLUE_BACK() {
        return new SimpleAttribute("104");
    }

    public static AnsiColorAttribute BRIGHT_MAGENTA_BACK() {
        return new SimpleAttribute("105");
    }

    public static AnsiColorAttribute BRIGHT_CYAN_BACK() {
        return new SimpleAttribute("106");
    }

    public static AnsiColorAttribute BRIGHT_WHITE_BACK() {
        return new SimpleAttribute("107");
    }

    /**
     * @param colorNumber A number (0-255) that represents an 8-bit color.
     * @return An Attribute that represents a foreground with an 8-bit color.
     */
    public static AnsiColorAttribute TEXT_COLOR(int colorNumber) {
        return new TextColorAttribute(colorNumber);
    }

    // Complex colors

    /**
     * @param r A number (0-255) that represents the red component.
     * @param g A number (0-255) that represents the green component.
     * @param b A number (0-255) that represents the blue component.
     * @return An Attribute that represents a foreground with a true color.
     */
    public static AnsiColorAttribute TEXT_COLOR(int r, int g, int b) {
        return new TextColorAttribute(r, g, b);
    }

    /**
     * @param colorNumber A number (0-255) that represents an 8-bit color.
     * @return An Attribute that represents a background with an 8-bit color.
     */
    public static AnsiColorAttribute BACK_COLOR(int colorNumber) {
        return new AnsiBackColorAttribute(colorNumber);
    }

    /**
     * @param r A number (0-255) that represents the red component.
     * @param g A number (0-255) that represents the green component.
     * @param b A number (0-255) that represents the blue component.
     * @return An Attribute that represents a background with a true color.
     */
    public static AnsiColorAttribute BACK_COLOR(int r, int g, int b) {
        return new AnsiBackColorAttribute(r, g, b);
    }

    /**
     * @return The Attribute's ansi escape code.
     */
    @Override
    public abstract String toString();

    /*
    ==========
    End of API
    ==========
    */
}

