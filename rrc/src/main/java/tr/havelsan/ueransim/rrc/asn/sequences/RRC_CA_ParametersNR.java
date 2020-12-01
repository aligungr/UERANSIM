/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CA_ParametersNR extends RRC_Sequence {

    public RRC_Integer dummy;
    public RRC_Integer parallelTxSRS_PUCCH_PUSCH;
    public RRC_Integer parallelTxPRACH_SRS_PUCCH_PUSCH;
    public RRC_Integer simultaneousRxTxInterBandCA;
    public RRC_Integer simultaneousRxTxSUL;
    public RRC_Integer diffNumerologyAcrossPUCCH_Group;
    public RRC_Integer diffNumerologyWithinPUCCH_GroupSmallerSCS;
    public RRC_Integer supportedNumberTAG;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dummy","parallelTxSRS-PUCCH-PUSCH","parallelTxPRACH-SRS-PUCCH-PUSCH","simultaneousRxTxInterBandCA","simultaneousRxTxSUL","diffNumerologyAcrossPUCCH-Group","diffNumerologyWithinPUCCH-GroupSmallerSCS","supportedNumberTAG" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dummy","parallelTxSRS_PUCCH_PUSCH","parallelTxPRACH_SRS_PUCCH_PUSCH","simultaneousRxTxInterBandCA","simultaneousRxTxSUL","diffNumerologyAcrossPUCCH_Group","diffNumerologyWithinPUCCH_GroupSmallerSCS","supportedNumberTAG" };
    }

    @Override
    public String getAsnName() {
        return "CA-ParametersNR";
    }

    @Override
    public String getXmlTagName() {
        return "CA-ParametersNR";
    }

}
