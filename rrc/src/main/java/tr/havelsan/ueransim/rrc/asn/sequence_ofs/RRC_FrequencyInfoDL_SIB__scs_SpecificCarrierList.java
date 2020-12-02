/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SCS_SpecificCarrier;

public class RRC_FrequencyInfoDL_SIB__scs_SpecificCarrierList extends RRC_SequenceOf<RRC_SCS_SpecificCarrier> {

    @Override
    public Class<RRC_SCS_SpecificCarrier> getItemType() {
        return RRC_SCS_SpecificCarrier.class;
    }

}
