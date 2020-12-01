/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultListNR;

public class RRC_MeasResult2NR extends RRC_Sequence {

    public RRC_ARFCN_ValueNR ssbFrequency;
    public RRC_ARFCN_ValueNR refFreqCSI_RS;
    public RRC_MeasResultNR measResultServingCell;
    public RRC_MeasResultListNR measResultNeighCellListNR;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssbFrequency","refFreqCSI-RS","measResultServingCell","measResultNeighCellListNR" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssbFrequency","refFreqCSI_RS","measResultServingCell","measResultNeighCellListNR" };
    }

    @Override
    public String getAsnName() {
        return "MeasResult2NR";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResult2NR";
    }

}
