/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.*;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BWP_UplinkDedicated extends RRC_Sequence {

    public RRC_SetupRelease_PUCCH_Config pucch_Config;
    public RRC_SetupRelease_PUSCH_Config pusch_Config;
    public RRC_SetupRelease_ConfiguredGrantConfig configuredGrantConfig;
    public RRC_SetupRelease_SRS_Config srs_Config;
    public RRC_SetupRelease_BeamFailureRecoveryConfig beamFailureRecoveryConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pucch-Config","pusch-Config","configuredGrantConfig","srs-Config","beamFailureRecoveryConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pucch_Config","pusch_Config","configuredGrantConfig","srs_Config","beamFailureRecoveryConfig" };
    }

    @Override
    public String getAsnName() {
        return "BWP-UplinkDedicated";
    }

    @Override
    public String getXmlTagName() {
        return "BWP-UplinkDedicated";
    }

}
