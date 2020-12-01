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

public class RRC_FeatureSetDownlinkId extends RRC_Integer {

    public RRC_FeatureSetDownlinkId(long value) {
        super(value);
    }

    public RRC_FeatureSetDownlinkId(Octet value) {
        super(value);
    }

    public RRC_FeatureSetDownlinkId(Octet2 value) {
        super(value);
    }

    public RRC_FeatureSetDownlinkId(Octet3 value) {
        super(value);
    }

    public RRC_FeatureSetDownlinkId(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "FeatureSetDownlinkId";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetDownlinkId";
    }

}
