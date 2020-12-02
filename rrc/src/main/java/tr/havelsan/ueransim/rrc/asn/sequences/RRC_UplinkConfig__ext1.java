/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkConfig__ext1__uplinkChannelBW_PerSCS_List;

public class RRC_UplinkConfig__ext1 extends RRC_Sequence {

    public RRC_Boolean powerBoostPi2BPSK;
    public RRC_UplinkConfig__ext1__uplinkChannelBW_PerSCS_List uplinkChannelBW_PerSCS_List;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "powerBoostPi2BPSK","uplinkChannelBW-PerSCS-List" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "powerBoostPi2BPSK","uplinkChannelBW_PerSCS_List" };
    }

}