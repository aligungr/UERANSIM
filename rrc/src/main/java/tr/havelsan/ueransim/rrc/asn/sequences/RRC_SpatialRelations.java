/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SpatialRelations extends RRC_Sequence {

    public RRC_Integer maxNumberConfiguredSpatialRelations;
    public RRC_Integer maxNumberActiveSpatialRelations;
    public RRC_Integer additionalActiveSpatialRelationPUCCH;
    public RRC_Integer maxNumberDL_RS_QCL_TypeD;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberConfiguredSpatialRelations","maxNumberActiveSpatialRelations","additionalActiveSpatialRelationPUCCH","maxNumberDL-RS-QCL-TypeD" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberConfiguredSpatialRelations","maxNumberActiveSpatialRelations","additionalActiveSpatialRelationPUCCH","maxNumberDL_RS_QCL_TypeD" };
    }

    @Override
    public String getAsnName() {
        return "SpatialRelations";
    }

    @Override
    public String getXmlTagName() {
        return "SpatialRelations";
    }

}
