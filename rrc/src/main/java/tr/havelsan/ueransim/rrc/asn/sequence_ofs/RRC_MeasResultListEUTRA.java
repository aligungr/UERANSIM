/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasResultEUTRA;

public class RRC_MeasResultListEUTRA extends RRC_SequenceOf<RRC_MeasResultEUTRA> {

    @Override
    public String getAsnName() {
        return "MeasResultListEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultListEUTRA";
    }

    @Override
    public Class<RRC_MeasResultEUTRA> getItemType() {
        return RRC_MeasResultEUTRA.class;
    }

}
