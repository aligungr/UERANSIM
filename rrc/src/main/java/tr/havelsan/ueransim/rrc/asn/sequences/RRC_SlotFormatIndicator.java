/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SlotFormatIndicator__slotFormatCombToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SlotFormatIndicator__slotFormatCombToReleaseList;

public class RRC_SlotFormatIndicator extends RRC_Sequence {

    public RRC_RNTI_Value sfi_RNTI;
    public RRC_Integer dci_PayloadSize;
    public RRC_SlotFormatIndicator__slotFormatCombToAddModList slotFormatCombToAddModList;
    public RRC_SlotFormatIndicator__slotFormatCombToReleaseList slotFormatCombToReleaseList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sfi-RNTI","dci-PayloadSize","slotFormatCombToAddModList","slotFormatCombToReleaseList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sfi_RNTI","dci_PayloadSize","slotFormatCombToAddModList","slotFormatCombToReleaseList" };
    }

    @Override
    public String getAsnName() {
        return "SlotFormatIndicator";
    }

    @Override
    public String getXmlTagName() {
        return "SlotFormatIndicator";
    }

}
