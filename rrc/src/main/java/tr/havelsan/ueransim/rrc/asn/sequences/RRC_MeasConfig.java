/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasConfig__s_MeasureConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_MeasConfig extends RRC_Sequence {

    public RRC_MeasObjectToRemoveList measObjectToRemoveList;
    public RRC_MeasObjectToAddModList measObjectToAddModList;
    public RRC_ReportConfigToRemoveList reportConfigToRemoveList;
    public RRC_ReportConfigToAddModList reportConfigToAddModList;
    public RRC_MeasIdToRemoveList measIdToRemoveList;
    public RRC_MeasIdToAddModList measIdToAddModList;
    public RRC_MeasConfig__s_MeasureConfig s_MeasureConfig;
    public RRC_QuantityConfig quantityConfig;
    public RRC_MeasGapConfig measGapConfig;
    public RRC_MeasGapSharingConfig measGapSharingConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measObjectToRemoveList","measObjectToAddModList","reportConfigToRemoveList","reportConfigToAddModList","measIdToRemoveList","measIdToAddModList","s-MeasureConfig","quantityConfig","measGapConfig","measGapSharingConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measObjectToRemoveList","measObjectToAddModList","reportConfigToRemoveList","reportConfigToAddModList","measIdToRemoveList","measIdToAddModList","s_MeasureConfig","quantityConfig","measGapConfig","measGapSharingConfig" };
    }

    @Override
    public String getAsnName() {
        return "MeasConfig";
    }

    @Override
    public String getXmlTagName() {
        return "MeasConfig";
    }

}
