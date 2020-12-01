/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_TDD_UL_DL_ConfigDedicated__slotSpecificConfigurationsToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_TDD_UL_DL_ConfigDedicated__slotSpecificConfigurationsToreleaseList;

public class RRC_TDD_UL_DL_ConfigDedicated extends RRC_Sequence {

    public RRC_TDD_UL_DL_ConfigDedicated__slotSpecificConfigurationsToAddModList slotSpecificConfigurationsToAddModList;
    public RRC_TDD_UL_DL_ConfigDedicated__slotSpecificConfigurationsToreleaseList slotSpecificConfigurationsToreleaseList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "slotSpecificConfigurationsToAddModList","slotSpecificConfigurationsToreleaseList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "slotSpecificConfigurationsToAddModList","slotSpecificConfigurationsToreleaseList" };
    }

    @Override
    public String getAsnName() {
        return "TDD-UL-DL-ConfigDedicated";
    }

    @Override
    public String getXmlTagName() {
        return "TDD-UL-DL-ConfigDedicated";
    }

}
