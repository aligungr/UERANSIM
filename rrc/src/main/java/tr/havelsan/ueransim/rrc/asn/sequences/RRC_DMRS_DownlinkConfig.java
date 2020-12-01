/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PTRS_DownlinkConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DMRS_DownlinkConfig extends RRC_Sequence {

    public RRC_Integer dmrs_Type;
    public RRC_Integer dmrs_AdditionalPosition;
    public RRC_Integer maxLength;
    public RRC_Integer scramblingID0;
    public RRC_Integer scramblingID1;
    public RRC_SetupRelease_PTRS_DownlinkConfig phaseTrackingRS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dmrs-Type","dmrs-AdditionalPosition","maxLength","scramblingID0","scramblingID1","phaseTrackingRS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dmrs_Type","dmrs_AdditionalPosition","maxLength","scramblingID0","scramblingID1","phaseTrackingRS" };
    }

    @Override
    public String getAsnName() {
        return "DMRS-DownlinkConfig";
    }

    @Override
    public String getXmlTagName() {
        return "DMRS-DownlinkConfig";
    }

}
