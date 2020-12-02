/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersDL;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_MIMO_LayersUL;

public class RRC_OverheatingAssistance__reducedMaxMIMO_LayersFR2 extends RRC_Sequence {

    public RRC_MIMO_LayersDL reducedMIMO_LayersFR2_DL;
    public RRC_MIMO_LayersUL reducedMIMO_LayersFR2_UL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reducedMIMO-LayersFR2-DL","reducedMIMO-LayersFR2-UL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reducedMIMO_LayersFR2_DL","reducedMIMO_LayersFR2_UL" };
    }

}
