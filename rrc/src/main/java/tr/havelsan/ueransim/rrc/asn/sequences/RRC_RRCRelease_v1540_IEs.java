/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RejectWaitTime;

public class RRC_RRCRelease_v1540_IEs extends RRC_Sequence {

    public RRC_RejectWaitTime waitTime;
    public RRC_RRCRelease_v1540_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "waitTime","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "waitTime","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCRelease-v1540-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCRelease-v1540-IEs";
    }

}
