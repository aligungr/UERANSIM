/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandNR__channelBWs_UL_v1530__fr1;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BandNR__channelBWs_UL_v1530__fr2;

public class RRC_BandNR__channelBWs_UL_v1530 extends RRC_Choice {

    public RRC_BandNR__channelBWs_UL_v1530__fr1 fr1;
    public RRC_BandNR__channelBWs_UL_v1530__fr2 fr2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "fr1","fr2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "fr1","fr2" };
    }

}
