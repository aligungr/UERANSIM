/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BWP_Uplink;

public class RRC_UplinkConfig__uplinkBWP_ToAddModList extends RRC_SequenceOf<RRC_BWP_Uplink> {

    @Override
    public Class<RRC_BWP_Uplink> getItemType() {
        return RRC_BWP_Uplink.class;
    }

}
