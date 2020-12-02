/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SRS_Resource;

public class RRC_SRS_Config__srs_ResourceToAddModList extends RRC_SequenceOf<RRC_SRS_Resource> {

    @Override
    public Class<RRC_SRS_Resource> getItemType() {
        return RRC_SRS_Resource.class;
    }

}
