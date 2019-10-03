package com.runsim.backend.otn;

import com.runsim.backend.utils.Conversions;

public class OtnBase16 extends OtnElement {
    private final String base16;

    public OtnBase16(String base16) {
        if (!Conversions.isValidHexString(base16))
            throw new RuntimeException("invalid base16: " + base16);
        this.base16 = base16;
    }

    public String getString() {
        return base16;
    }

    public byte[] getData() {
        return Conversions.hexStringToByteArray(base16);
    }
}
