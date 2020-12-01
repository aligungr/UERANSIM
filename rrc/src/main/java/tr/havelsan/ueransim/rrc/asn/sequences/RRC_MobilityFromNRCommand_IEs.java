/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MobilityFromNRCommand_IEs extends RRC_Sequence {

    public RRC_Integer targetRAT_Type;
    public RRC_OctetString targetRAT_MessageContainer;
    public RRC_OctetString nas_SecurityParamFromNR;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_MobilityFromNRCommand_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "targetRAT-Type","targetRAT-MessageContainer","nas-SecurityParamFromNR","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "targetRAT_Type","targetRAT_MessageContainer","nas_SecurityParamFromNR","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "MobilityFromNRCommand-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "MobilityFromNRCommand-IEs";
    }

}
