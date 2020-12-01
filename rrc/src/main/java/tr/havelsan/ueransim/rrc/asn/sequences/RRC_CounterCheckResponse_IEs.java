/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DRB_CountInfoList;

public class RRC_CounterCheckResponse_IEs extends RRC_Sequence {

    public RRC_DRB_CountInfoList drb_CountInfoList;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_CounterCheckResponse_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "drb-CountInfoList","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "drb_CountInfoList","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "CounterCheckResponse-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "CounterCheckResponse-IEs";
    }

}
