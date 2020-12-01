/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MCC;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MNC;

public class RRC_PLMN_Identity extends RRC_Sequence {

    public RRC_MCC mcc;
    public RRC_MNC mnc;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mcc","mnc" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mcc","mnc" };
    }

    @Override
    public String getAsnName() {
        return "PLMN-Identity";
    }

    @Override
    public String getXmlTagName() {
        return "PLMN-Identity";
    }

}
