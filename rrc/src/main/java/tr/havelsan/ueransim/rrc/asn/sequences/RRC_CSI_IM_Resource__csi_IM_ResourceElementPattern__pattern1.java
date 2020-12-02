/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern__pattern1 extends RRC_Sequence {

    public RRC_Integer subcarrierLocation_p1;
    public RRC_Integer symbolLocation_p1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "subcarrierLocation-p1","symbolLocation-p1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "subcarrierLocation_p1","symbolLocation_p1" };
    }

}
