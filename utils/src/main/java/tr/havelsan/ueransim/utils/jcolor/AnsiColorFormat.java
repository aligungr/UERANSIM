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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Abstracts an Array of {@link AnsiColorAttribute}s.
 * Use it if you find this more readable than Attribute[].
 */
public class AnsiColorFormat {

    // Starts with capacity=2 because that's how many attributes are used on average
    private final ArrayList<AnsiColorAttribute> _AnsiColor_attributes = new ArrayList<>(2);

    /**
     * @param ansiColorAttributes All ANSI attributes to format a text.
     */
    public AnsiColorFormat(AnsiColorAttribute... ansiColorAttributes) {
        _AnsiColor_attributes.addAll(Arrays.asList(ansiColorAttributes));
    }

    /**
     * @param text String to format.
     * @return The formatted string, ready to be printed.
     */
    public String format(String text) {
        return AnsiColor.colorize(text, this.toArray());
    }

    protected AnsiColorAttribute[] toArray() {
        return _AnsiColor_attributes.toArray(new AnsiColorAttribute[0]);
    }
}
