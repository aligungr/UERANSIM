/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_TDD_UL_DL_Pattern extends RRC_Sequence {

    public RRC_Integer dl_UL_TransmissionPeriodicity;
    public RRC_Integer nrofDownlinkSlots;
    public RRC_Integer nrofDownlinkSymbols;
    public RRC_Integer nrofUplinkSlots;
    public RRC_Integer nrofUplinkSymbols;
    public RRC_TDD_UL_DL_Pattern__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dl-UL-TransmissionPeriodicity","nrofDownlinkSlots","nrofDownlinkSymbols","nrofUplinkSlots","nrofUplinkSymbols","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dl_UL_TransmissionPeriodicity","nrofDownlinkSlots","nrofDownlinkSymbols","nrofUplinkSlots","nrofUplinkSymbols","ext1" };
    }

    @Override
    public String getAsnName() {
        return "TDD-UL-DL-Pattern";
    }

    @Override
    public String getXmlTagName() {
        return "TDD-UL-DL-Pattern";
    }

}
