/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ULInformationTransferMRDC_IEs extends RRC_Sequence {

    public RRC_OctetString ul_DCCH_MessageNR;
    public RRC_OctetString ul_DCCH_MessageEUTRA;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_ULInformationTransferMRDC_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ul-DCCH-MessageNR","ul-DCCH-MessageEUTRA","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ul_DCCH_MessageNR","ul_DCCH_MessageEUTRA","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "ULInformationTransferMRDC-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "ULInformationTransferMRDC-IEs";
    }

}
