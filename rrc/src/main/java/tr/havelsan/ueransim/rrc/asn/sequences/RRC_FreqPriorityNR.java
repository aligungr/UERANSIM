/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellReselectionPriority;

public class RRC_FreqPriorityNR extends RRC_Sequence {

    public RRC_ARFCN_ValueNR carrierFreq;
    public RRC_CellReselectionPriority cellReselectionPriority;
    public RRC_CellReselectionSubPriority cellReselectionSubPriority;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","cellReselectionPriority","cellReselectionSubPriority" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","cellReselectionPriority","cellReselectionSubPriority" };
    }

    @Override
    public String getAsnName() {
        return "FreqPriorityNR";
    }

    @Override
    public String getXmlTagName() {
        return "FreqPriorityNR";
    }

}
