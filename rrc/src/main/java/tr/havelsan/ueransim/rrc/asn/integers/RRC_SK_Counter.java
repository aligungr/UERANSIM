package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..65535)
public class RRC_SK_Counter extends AsnInteger {
    public RRC_SK_Counter() {
    }
    
    public RRC_SK_Counter(long value) {
        super(value);
    }
}

