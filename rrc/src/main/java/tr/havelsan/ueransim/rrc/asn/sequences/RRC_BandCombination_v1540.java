/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombination_v1540__bandList_v1540;

public class RRC_BandCombination_v1540 extends RRC_Sequence {

    public RRC_BandCombination_v1540__bandList_v1540 bandList_v1540;
    public RRC_CA_ParametersNR_v1540 ca_ParametersNR_v1540;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bandList-v1540","ca-ParametersNR-v1540" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bandList_v1540","ca_ParametersNR_v1540" };
    }

    @Override
    public String getAsnName() {
        return "BandCombination-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "BandCombination-v1540";
    }

}
