/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RRCReconfiguration_IEs extends RRC_Sequence {

    public RRC_RadioBearerConfig radioBearerConfig;
    public RRC_OctetString secondaryCellGroup;
    public RRC_MeasConfig measConfig;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_RRCReconfiguration_v1530_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "radioBearerConfig","secondaryCellGroup","measConfig","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "radioBearerConfig","secondaryCellGroup","measConfig","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReconfiguration-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReconfiguration-IEs";
    }

}
