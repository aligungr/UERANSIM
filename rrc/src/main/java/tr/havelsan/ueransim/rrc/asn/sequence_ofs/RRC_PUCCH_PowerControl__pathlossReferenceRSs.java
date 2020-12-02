/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PUCCH_PathlossReferenceRS;

public class RRC_PUCCH_PowerControl__pathlossReferenceRSs extends RRC_SequenceOf<RRC_PUCCH_PathlossReferenceRS> {

    @Override
    public Class<RRC_PUCCH_PathlossReferenceRS> getItemType() {
        return RRC_PUCCH_PathlossReferenceRS.class;
    }

}
