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

import static java.lang.String.valueOf;

abstract class ColorAttribute extends AnsiColorAttribute {

    protected final String[] _color;

    /**
     * Constructor (8-bit color).
     *
     * @param colorNumber A number (0-255) that represents an 8-bit color.
     */
    ColorAttribute(int colorNumber) {
        if (0 <= colorNumber && colorNumber <= 255) {
            _color = new String[]{valueOf(colorNumber)};
        } else
            throw new IllegalArgumentException("Color must be a number inside range [0-255]. Received: " + colorNumber);
    }

    /**
     * Constructor (true-color).
     *
     * @param r A number (0-255) that represents the red component.
     * @param g A number (0-255) that represents the green component.
     * @param b A number (0-255) that represents the blue component.
     */
    ColorAttribute(int r, int g, int b) {
        if ((0 <= r && r <= 255) && (0 <= g && g <= 255) && (0 <= b && b <= 255)) {
            _color = new String[]{valueOf(r), valueOf(g), valueOf(b)};
        } else
            throw new IllegalArgumentException(
                    String.format("Color components must be a number inside range [0-255]. Received: %d, %d, %d", r, g, b));
    }

    protected boolean isTrueColor() {
        return (_color.length == 3 );
    }

    protected abstract String getColorAnsiPrefix();

    protected String getColorAnsiCode() {
        if (isTrueColor())
            return _color[0] + AnsiColor.SEPARATOR + _color[1] + AnsiColor.SEPARATOR + _color[2];
        else
            return _color[0];
    }

    @Override
    public String toString() {
        return getColorAnsiPrefix() + getColorAnsiCode();
    }

}
