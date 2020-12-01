/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NextHopChainingCount;

public class RRC_RRCReestablishment_IEs extends RRC_Sequence {

    public RRC_NextHopChainingCount nextHopChainingCount;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_RRCReestablishment_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nextHopChainingCount","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nextHopChainingCount","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReestablishment-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReestablishment-IEs";
    }

}
