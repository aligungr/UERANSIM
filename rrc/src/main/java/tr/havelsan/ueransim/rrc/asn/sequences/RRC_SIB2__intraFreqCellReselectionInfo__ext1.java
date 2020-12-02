/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SIB2__intraFreqCellReselectionInfo__ext1 extends RRC_Sequence {

    public RRC_SpeedStateScaleFactors t_ReselectionNR_SF;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "t-ReselectionNR-SF" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "t_ReselectionNR_SF" };
    }

}
