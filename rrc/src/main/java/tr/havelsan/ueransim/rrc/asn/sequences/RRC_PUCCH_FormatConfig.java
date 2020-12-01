/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PUCCH_MaxCodeRate;

public class RRC_PUCCH_FormatConfig extends RRC_Sequence {

    public RRC_Integer interslotFrequencyHopping;
    public RRC_Integer additionalDMRS;
    public RRC_PUCCH_MaxCodeRate maxCodeRate;
    public RRC_Integer nrofSlots;
    public RRC_Integer pi2BPSK;
    public RRC_Integer simultaneousHARQ_ACK_CSI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "interslotFrequencyHopping","additionalDMRS","maxCodeRate","nrofSlots","pi2BPSK","simultaneousHARQ-ACK-CSI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "interslotFrequencyHopping","additionalDMRS","maxCodeRate","nrofSlots","pi2BPSK","simultaneousHARQ_ACK_CSI" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-FormatConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-FormatConfig";
    }

}
