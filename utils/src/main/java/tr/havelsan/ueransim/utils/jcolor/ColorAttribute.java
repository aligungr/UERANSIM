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
