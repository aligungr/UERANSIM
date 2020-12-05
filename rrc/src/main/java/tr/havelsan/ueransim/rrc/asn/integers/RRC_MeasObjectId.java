package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(1..64)
public class RRC_MeasObjectId extends AsnInteger {
    public RRC_MeasObjectId() {
    }
    
    public RRC_MeasObjectId(long value) {
        super(value);
    }
}

