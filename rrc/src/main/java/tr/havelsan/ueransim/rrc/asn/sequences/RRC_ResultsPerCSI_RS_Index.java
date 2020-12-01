/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_RS_Index;

public class RRC_ResultsPerCSI_RS_Index extends RRC_Sequence {

    public RRC_CSI_RS_Index csi_RS_Index;
    public RRC_MeasQuantityResults csi_RS_Results;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-RS-Index","csi-RS-Results" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_RS_Index","csi_RS_Results" };
    }

    @Override
    public String getAsnName() {
        return "ResultsPerCSI-RS-Index";
    }

    @Override
    public String getXmlTagName() {
        return "ResultsPerCSI-RS-Index";
    }

}
