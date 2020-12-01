/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CA_ParametersNR_v1540 extends RRC_Sequence {

    public RRC_Integer simultaneousSRS_AssocCSI_RS_AllCC;
    public RRC_CA_ParametersNR_v1540__csi_RS_IM_ReceptionForFeedbackPerBandComb csi_RS_IM_ReceptionForFeedbackPerBandComb;
    public RRC_Integer simultaneousCSI_ReportsAllCC;
    public RRC_Integer dualPA_Architecture;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "simultaneousSRS-AssocCSI-RS-AllCC","csi-RS-IM-ReceptionForFeedbackPerBandComb","simultaneousCSI-ReportsAllCC","dualPA-Architecture" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "simultaneousSRS_AssocCSI_RS_AllCC","csi_RS_IM_ReceptionForFeedbackPerBandComb","simultaneousCSI_ReportsAllCC","dualPA_Architecture" };
    }

    @Override
    public String getAsnName() {
        return "CA-ParametersNR-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "CA-ParametersNR-v1540";
    }

}
