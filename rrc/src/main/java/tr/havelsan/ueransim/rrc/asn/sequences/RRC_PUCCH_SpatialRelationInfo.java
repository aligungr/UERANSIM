/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PUCCH_SpatialRelationInfo__referenceSignal;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUCCH_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_PathlossReferenceRS_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_SpatialRelationInfoId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PUCCH_SpatialRelationInfo extends RRC_Sequence {

    public RRC_PUCCH_SpatialRelationInfoId pucch_SpatialRelationInfoId;
    public RRC_ServCellIndex servingCellId;
    public RRC_PUCCH_SpatialRelationInfo__referenceSignal referenceSignal;
    public RRC_PUCCH_PathlossReferenceRS_Id pucch_PathlossReferenceRS_Id;
    public RRC_P0_PUCCH_Id p0_PUCCH_Id;
    public RRC_Integer closedLoopIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pucch-SpatialRelationInfoId","servingCellId","referenceSignal","pucch-PathlossReferenceRS-Id","p0-PUCCH-Id","closedLoopIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pucch_SpatialRelationInfoId","servingCellId","referenceSignal","pucch_PathlossReferenceRS_Id","p0_PUCCH_Id","closedLoopIndex" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-SpatialRelationInfo";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-SpatialRelationInfo";
    }

}
