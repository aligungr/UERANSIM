/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB2__cellReselectionInfoCommon__speedStateReselectionPars__q_HystSF extends RRC_Sequence {

    public RRC_Integer sf_Medium;
    public RRC_Integer sf_High;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sf-Medium","sf-High" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sf_Medium","sf_High" };
    }

}
