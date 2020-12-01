/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCReconfiguration_v1540_IEs extends RRC_Sequence {

    public RRC_OtherConfig_v1540 otherConfig_v1540;
    public RRC_RRCReconfiguration_v1560_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "otherConfig-v1540","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "otherConfig_v1540","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReconfiguration-v1540-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReconfiguration-v1540-IEs";
    }

}
