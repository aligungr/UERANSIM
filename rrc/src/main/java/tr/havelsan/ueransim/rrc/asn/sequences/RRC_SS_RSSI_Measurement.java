/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SS_RSSI_Measurement extends RRC_Sequence {

    public RRC_BitString measurementSlots;
    public RRC_Integer endSymbol;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measurementSlots","endSymbol" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measurementSlots","endSymbol" };
    }

    @Override
    public String getAsnName() {
        return "SS-RSSI-Measurement";
    }

    @Override
    public String getXmlTagName() {
        return "SS-RSSI-Measurement";
    }

}
