/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DownlinkPreemption__int_ConfigurationPerServingCell;

public class RRC_DownlinkPreemption extends RRC_Sequence {

    public RRC_RNTI_Value int_RNTI;
    public RRC_Integer timeFrequencySet;
    public RRC_Integer dci_PayloadSize;
    public RRC_DownlinkPreemption__int_ConfigurationPerServingCell int_ConfigurationPerServingCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "int-RNTI","timeFrequencySet","dci-PayloadSize","int-ConfigurationPerServingCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "int_RNTI","timeFrequencySet","dci_PayloadSize","int_ConfigurationPerServingCell" };
    }

    @Override
    public String getAsnName() {
        return "DownlinkPreemption";
    }

    @Override
    public String getXmlTagName() {
        return "DownlinkPreemption";
    }

}
