/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_ResourceSet__pathlossReferenceRS;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_ResourceSet__resourceType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Alpha;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SRS_ResourceSet__srs_ResourceIdList;

public class RRC_SRS_ResourceSet extends RRC_Sequence {

    public RRC_SRS_ResourceSetId srs_ResourceSetId;
    public RRC_SRS_ResourceSet__srs_ResourceIdList srs_ResourceIdList;
    public RRC_SRS_ResourceSet__resourceType resourceType;
    public RRC_Integer usage;
    public RRC_Alpha alpha;
    public RRC_Integer p0;
    public RRC_SRS_ResourceSet__pathlossReferenceRS pathlossReferenceRS;
    public RRC_Integer srs_PowerControlAdjustmentStates;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-ResourceSetId","srs-ResourceIdList","resourceType","usage","alpha","p0","pathlossReferenceRS","srs-PowerControlAdjustmentStates" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_ResourceSetId","srs_ResourceIdList","resourceType","usage","alpha","p0","pathlossReferenceRS","srs_PowerControlAdjustmentStates" };
    }

    @Override
    public String getAsnName() {
        return "SRS-ResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-ResourceSet";
    }

}
