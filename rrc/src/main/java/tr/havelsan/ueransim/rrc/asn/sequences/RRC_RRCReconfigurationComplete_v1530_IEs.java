/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkTxDirectCurrentList;

public class RRC_RRCReconfigurationComplete_v1530_IEs extends RRC_Sequence {

    public RRC_UplinkTxDirectCurrentList uplinkTxDirectCurrentList;
    public RRC_RRCReconfigurationComplete_v1560_IEs nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "uplinkTxDirectCurrentList","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "uplinkTxDirectCurrentList","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReconfigurationComplete-v1530-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReconfigurationComplete-v1530-IEs";
    }

}
