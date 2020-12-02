/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB2__cellReselectionInfoCommon__speedStateReselectionPars extends RRC_Sequence {

    public RRC_MobilityStateParameters mobilityStateParameters;
    public RRC_SIB2__cellReselectionInfoCommon__speedStateReselectionPars__q_HystSF q_HystSF;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mobilityStateParameters","q-HystSF" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mobilityStateParameters","q_HystSF" };
    }

}
