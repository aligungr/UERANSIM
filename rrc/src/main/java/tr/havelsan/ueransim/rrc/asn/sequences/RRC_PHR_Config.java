/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PHR_Config extends RRC_Sequence {

    public RRC_Integer phr_PeriodicTimer;
    public RRC_Integer phr_ProhibitTimer;
    public RRC_Integer phr_Tx_PowerFactorChange;
    public RRC_Boolean multiplePHR;
    public RRC_Boolean dummy;
    public RRC_Boolean phr_Type2OtherCell;
    public RRC_Integer phr_ModeOtherCG;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "phr-PeriodicTimer","phr-ProhibitTimer","phr-Tx-PowerFactorChange","multiplePHR","dummy","phr-Type2OtherCell","phr-ModeOtherCG" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "phr_PeriodicTimer","phr_ProhibitTimer","phr_Tx_PowerFactorChange","multiplePHR","dummy","phr_Type2OtherCell","phr_ModeOtherCG" };
    }

    @Override
    public String getAsnName() {
        return "PHR-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PHR-Config";
    }

}
