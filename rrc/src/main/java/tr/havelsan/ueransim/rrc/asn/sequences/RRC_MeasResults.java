/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_MeasResults__measResultNeighCells;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultServMOList;

public class RRC_MeasResults extends RRC_Sequence {

    public RRC_MeasId measId;
    public RRC_MeasResultServMOList measResultServingMOList;
    public RRC_MeasResults__measResultNeighCells measResultNeighCells;
    public RRC_MeasResults__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measId","measResultServingMOList","measResultNeighCells","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measId","measResultServingMOList","measResultNeighCells","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasResults";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResults";
    }

}
