/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCResume_IEs extends RRC_Sequence {

    public RRC_RadioBearerConfig radioBearerConfig;
    public RRC_OctetString masterCellGroup;
    public RRC_MeasConfig measConfig;
    public RRC_Integer fullConfig;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_RRCResume_v1560_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "radioBearerConfig","masterCellGroup","measConfig","fullConfig","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "radioBearerConfig","masterCellGroup","measConfig","fullConfig","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCResume-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCResume-IEs";
    }

}
