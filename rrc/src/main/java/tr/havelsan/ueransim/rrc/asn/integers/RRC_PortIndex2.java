/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.integers;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class RRC_PortIndex2 extends RRC_Integer {

    public RRC_PortIndex2(long value) {
        super(value);
    }

    public RRC_PortIndex2(Octet value) {
        super(value);
    }

    public RRC_PortIndex2(Octet2 value) {
        super(value);
    }

    public RRC_PortIndex2(Octet3 value) {
        super(value);
    }

    public RRC_PortIndex2(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PortIndex2";
    }

    @Override
    public String getXmlTagName() {
        return "PortIndex2";
    }

}
