/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;

public class RRC_EUTRA_RSTD_Info extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA carrierFreq;
    public RRC_Integer measPRS_Offset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","measPRS-Offset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","measPRS_Offset" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-RSTD-Info";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-RSTD-Info";
    }

}
