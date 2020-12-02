/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasurementTimingConfiguration_IEs;

public class RRC_MeasurementTimingConfiguration__criticalExtensions__c1 extends RRC_Choice {

    public RRC_MeasurementTimingConfiguration_IEs measTimingConf;
    public RRC_Null spare3;
    public RRC_Null spare2;
    public RRC_Null spare1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measTimingConf","spare3","spare2","spare1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measTimingConf","spare3","spare2","spare1" };
    }

}
