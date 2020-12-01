/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultListEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultListNR;

public class RRC_MeasResults__measResultNeighCells extends RRC_Choice {

    public RRC_MeasResultListNR measResultListNR;
    public RRC_MeasResultListEUTRA measResultListEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measResultListNR","measResultListEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measResultListNR","measResultListEUTRA" };
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
