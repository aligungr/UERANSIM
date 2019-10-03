package com.runsim.backend.otn;

import com.runsim.backend.utils.Convertions;

public class OtnBase16 extends OtnElement {
    private final String base16;

    public OtnBase16(String base16) {
        if (!Convertions.isValidHexString(base16))
            throw new RuntimeException("invalid base16: " + base16);
        this.base16 = base16;
    }

    public String getString() {
        return base16;
    }

    public byte[] getData() {
        return Convertions.hexStringToByteArray(base16);
    }
}
