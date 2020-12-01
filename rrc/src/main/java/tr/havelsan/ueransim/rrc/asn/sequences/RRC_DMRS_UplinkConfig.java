/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PTRS_UplinkConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DMRS_UplinkConfig extends RRC_Sequence {

    public RRC_Integer dmrs_Type;
    public RRC_Integer dmrs_AdditionalPosition;
    public RRC_SetupRelease_PTRS_UplinkConfig phaseTrackingRS;
    public RRC_Integer maxLength;
    public RRC_DMRS_UplinkConfig__transformPrecodingDisabled transformPrecodingDisabled;
    public RRC_DMRS_UplinkConfig__transformPrecodingEnabled transformPrecodingEnabled;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dmrs-Type","dmrs-AdditionalPosition","phaseTrackingRS","maxLength","transformPrecodingDisabled","transformPrecodingEnabled" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dmrs_Type","dmrs_AdditionalPosition","phaseTrackingRS","maxLength","transformPrecodingDisabled","transformPrecodingEnabled" };
    }

    @Override
    public String getAsnName() {
        return "DMRS-UplinkConfig";
    }

    @Override
    public String getXmlTagName() {
        return "DMRS-UplinkConfig";
    }

}
