/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;

public class RRC_DRB_CountInfo extends RRC_Sequence {

    public RRC_DRB_Identity drb_Identity;
    public RRC_Integer count_Uplink;
    public RRC_Integer count_Downlink;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "drb-Identity","count-Uplink","count-Downlink" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "drb_Identity","count_Uplink","count_Downlink" };
    }

    @Override
    public String getAsnName() {
        return "DRB-CountInfo";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-CountInfo";
    }

}
