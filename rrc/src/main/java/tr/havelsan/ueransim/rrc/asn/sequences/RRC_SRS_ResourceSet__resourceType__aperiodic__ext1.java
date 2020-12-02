/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_ResourceSet__resourceType__aperiodic__ext1__aperiodicSRS_ResourceTriggerList_v1530;

public class RRC_SRS_ResourceSet__resourceType__aperiodic__ext1 extends RRC_Sequence {

    public RRC_SRS_ResourceSet__resourceType__aperiodic__ext1__aperiodicSRS_ResourceTriggerList_v1530 aperiodicSRS_ResourceTriggerList_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "aperiodicSRS-ResourceTriggerList-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "aperiodicSRS_ResourceTriggerList_v1530" };
    }

}
