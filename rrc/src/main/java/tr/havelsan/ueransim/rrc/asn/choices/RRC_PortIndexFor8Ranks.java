/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PortIndexFor8Ranks__portIndex2;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PortIndexFor8Ranks__portIndex4;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PortIndexFor8Ranks__portIndex8;

public class RRC_PortIndexFor8Ranks extends RRC_Choice {

    public RRC_PortIndexFor8Ranks__portIndex8 portIndex8;
    public RRC_PortIndexFor8Ranks__portIndex4 portIndex4;
    public RRC_PortIndexFor8Ranks__portIndex2 portIndex2;
    public RRC_Null portIndex1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "portIndex8","portIndex4","portIndex2","portIndex1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "portIndex8","portIndex4","portIndex2","portIndex1" };
    }

    @Override
    public String getAsnName() {
        return "PortIndexFor8Ranks";
    }

    @Override
    public String getXmlTagName() {
        return "PortIndexFor8Ranks";
    }

}
