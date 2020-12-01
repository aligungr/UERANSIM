/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasurementTimingConfiguration_v1550_IEs extends RRC_Sequence {

    public RRC_Boolean campOnFirstSSB;
    public RRC_Boolean psCellOnlyOnFirstSSB;
    public RRC_MeasurementTimingConfiguration_v1550_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "campOnFirstSSB","psCellOnlyOnFirstSSB","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "campOnFirstSSB","psCellOnlyOnFirstSSB","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "MeasurementTimingConfiguration-v1550-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "MeasurementTimingConfiguration-v1550-IEs";
    }

}
