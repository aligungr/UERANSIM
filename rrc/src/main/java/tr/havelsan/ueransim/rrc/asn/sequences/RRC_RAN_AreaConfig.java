/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_TrackingAreaCode;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RAN_AreaConfig__ran_AreaCodeList;

public class RRC_RAN_AreaConfig extends RRC_Sequence {

    public RRC_TrackingAreaCode trackingAreaCode;
    public RRC_RAN_AreaConfig__ran_AreaCodeList ran_AreaCodeList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "trackingAreaCode","ran-AreaCodeList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "trackingAreaCode","ran_AreaCodeList" };
    }

    @Override
    public String getAsnName() {
        return "RAN-AreaConfig";
    }

    @Override
    public String getXmlTagName() {
        return "RAN-AreaConfig";
    }

}
