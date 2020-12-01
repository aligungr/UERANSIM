/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_VarMeasConfig__s_MeasureConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasIdToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasObjectToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ReportConfigToAddModList;

public class RRC_VarMeasConfig extends RRC_Sequence {

    public RRC_MeasIdToAddModList measIdList;
    public RRC_MeasObjectToAddModList measObjectList;
    public RRC_ReportConfigToAddModList reportConfigList;
    public RRC_QuantityConfig quantityConfig;
    public RRC_VarMeasConfig__s_MeasureConfig s_MeasureConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measIdList","measObjectList","reportConfigList","quantityConfig","s-MeasureConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measIdList","measObjectList","reportConfigList","quantityConfig","s_MeasureConfig" };
    }

    @Override
    public String getAsnName() {
        return "VarMeasConfig";
    }

    @Override
    public String getXmlTagName() {
        return "VarMeasConfig";
    }

}
