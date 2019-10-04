package com.runsim.backend.otn;

import com.runsim.backend.Utils;

public class OtnBase16 extends OtnElement {
    private final String base16;

    public OtnBase16(String base16) {
        if (!Utils.isValidHexString(base16))
            throw new RuntimeException("invalid base16: " + base16);
        this.base16 = base16;
    }

    public String getString() {
        return base16;
    }

    public byte[] getData() {
        return Utils.hexStringToByteArray(base16);
    }

    @Override
    public String toJson() {
        return "{\"type\":\"base16\",\"value\":\"" + base16 + "\"}";
    }
}
