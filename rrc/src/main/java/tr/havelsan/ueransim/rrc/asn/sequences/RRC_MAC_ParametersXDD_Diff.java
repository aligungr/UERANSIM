/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MAC_ParametersXDD_Diff extends RRC_Sequence {

    public RRC_Integer skipUplinkTxDynamic;
    public RRC_Integer logicalChannelSR_DelayTimer;
    public RRC_Integer longDRX_Cycle;
    public RRC_Integer shortDRX_Cycle;
    public RRC_Integer multipleSR_Configurations;
    public RRC_Integer multipleConfiguredGrants;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "skipUplinkTxDynamic","logicalChannelSR-DelayTimer","longDRX-Cycle","shortDRX-Cycle","multipleSR-Configurations","multipleConfiguredGrants" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "skipUplinkTxDynamic","logicalChannelSR_DelayTimer","longDRX_Cycle","shortDRX_Cycle","multipleSR_Configurations","multipleConfiguredGrants" };
    }

    @Override
    public String getAsnName() {
        return "MAC-ParametersXDD-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "MAC-ParametersXDD-Diff";
    }

}
