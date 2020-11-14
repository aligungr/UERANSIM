/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.jcolor;

/*
 * This is the modified version of https://github.com/dialex/JColor.
 * Licensed by Diogo Nunes under MIT
 */

class SimpleAttribute extends AnsiColorAttribute {

    private final String _code;

    /**
     * Constructor. Maps an attribute to an Ansi code.
     *
     * @param code Ansi code that represents the attribute.
     */
    SimpleAttribute(String code) {
        _code = code;
    }

    @Override
    public String toString() {
        return _code;
    }

}
