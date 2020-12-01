/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_VictimSystemType extends RRC_Sequence {

    public RRC_Integer gps;
    public RRC_Integer glonass;
    public RRC_Integer bds;
    public RRC_Integer galileo;
    public RRC_Integer wlan;
    public RRC_Integer bluetooth;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "gps","glonass","bds","galileo","wlan","bluetooth" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "gps","glonass","bds","galileo","wlan","bluetooth" };
    }

    @Override
    public String getAsnName() {
        return "VictimSystemType";
    }

    @Override
    public String getXmlTagName() {
        return "VictimSystemType";
    }

}
