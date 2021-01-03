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
