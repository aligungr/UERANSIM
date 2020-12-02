/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_TCI_State;

public class RRC_PDSCH_Config__tci_StatesToAddModList extends RRC_SequenceOf<RRC_TCI_State> {

    @Override
    public Class<RRC_TCI_State> getItemType() {
        return RRC_TCI_State.class;
    }

}
