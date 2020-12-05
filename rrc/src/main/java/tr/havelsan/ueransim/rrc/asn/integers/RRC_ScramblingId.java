package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.asn.core.AsnInteger;

// VALUE(0..1023)
public class RRC_ScramblingId extends AsnInteger {
    public RRC_ScramblingId() {
    }
    
    public RRC_ScramblingId(long value) {
        super(value);
    }
}

