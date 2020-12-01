/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_SpatialRelationInfo__referenceSignal;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_SRS_SpatialRelationInfo extends RRC_Sequence {

    public RRC_ServCellIndex servingCellId;
    public RRC_SRS_SpatialRelationInfo__referenceSignal referenceSignal;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "servingCellId","referenceSignal" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "servingCellId","referenceSignal" };
    }

    @Override
    public String getAsnName() {
        return "SRS-SpatialRelationInfo";
    }

    @Override
    public String getXmlTagName() {
        return "SRS-SpatialRelationInfo";
    }

}
