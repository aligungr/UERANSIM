/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkTxDirectCurrentList;

public class RRC_RRCReconfigurationComplete_v1530_IEs extends AsnSequence {
    public RRC_UplinkTxDirectCurrentList uplinkTxDirectCurrentList; // optional
    public RRC_RRCReconfigurationComplete_v1560_IEs nonCriticalExtension; // optional
}

