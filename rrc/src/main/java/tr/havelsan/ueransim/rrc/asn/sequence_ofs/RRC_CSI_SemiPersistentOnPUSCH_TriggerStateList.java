/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_SemiPersistentOnPUSCH_TriggerState;

public class RRC_CSI_SemiPersistentOnPUSCH_TriggerStateList extends RRC_SequenceOf<RRC_CSI_SemiPersistentOnPUSCH_TriggerState> {

    @Override
    public String getAsnName() {
        return "CSI-SemiPersistentOnPUSCH-TriggerStateList";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-SemiPersistentOnPUSCH-TriggerStateList";
    }

    @Override
    public Class<RRC_CSI_SemiPersistentOnPUSCH_TriggerState> getItemType() {
        return RRC_CSI_SemiPersistentOnPUSCH_TriggerState.class;
    }

}
