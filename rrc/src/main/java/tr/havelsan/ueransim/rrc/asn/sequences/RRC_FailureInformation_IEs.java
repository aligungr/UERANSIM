/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FailureInformation_IEs extends RRC_Sequence {

    public RRC_FailureInfoRLC_Bearer failureInfoRLC_Bearer;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_FailureInformation_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "failureInfoRLC-Bearer","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "failureInfoRLC_Bearer","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "FailureInformation-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "FailureInformation-IEs";
    }

}
