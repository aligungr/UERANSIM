/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PollByte;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PollPDU;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SN_FieldLengthAM;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_T_PollRetransmit;

public class RRC_UL_AM_RLC extends RRC_Sequence {

    public RRC_SN_FieldLengthAM sn_FieldLength;
    public RRC_T_PollRetransmit t_PollRetransmit;
    public RRC_PollPDU pollPDU;
    public RRC_PollByte pollByte;
    public RRC_Integer maxRetxThreshold;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sn-FieldLength","t-PollRetransmit","pollPDU","pollByte","maxRetxThreshold" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sn_FieldLength","t_PollRetransmit","pollPDU","pollByte","maxRetxThreshold" };
    }

    @Override
    public String getAsnName() {
        return "UL-AM-RLC";
    }

    @Override
    public String getXmlTagName() {
        return "UL-AM-RLC";
    }

}
