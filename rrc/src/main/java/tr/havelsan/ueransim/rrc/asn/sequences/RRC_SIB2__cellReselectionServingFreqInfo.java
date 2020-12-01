/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellReselectionPriority;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReselectionThreshold;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReselectionThresholdQ;

public class RRC_SIB2__cellReselectionServingFreqInfo extends RRC_Sequence {

    public RRC_ReselectionThreshold s_NonIntraSearchP;
    public RRC_ReselectionThresholdQ s_NonIntraSearchQ;
    public RRC_ReselectionThreshold threshServingLowP;
    public RRC_ReselectionThresholdQ threshServingLowQ;
    public RRC_CellReselectionPriority cellReselectionPriority;
    public RRC_CellReselectionSubPriority cellReselectionSubPriority;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "s-NonIntraSearchP","s-NonIntraSearchQ","threshServingLowP","threshServingLowQ","cellReselectionPriority","cellReselectionSubPriority" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "s_NonIntraSearchP","s_NonIntraSearchQ","threshServingLowP","threshServingLowQ","cellReselectionPriority","cellReselectionSubPriority" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
