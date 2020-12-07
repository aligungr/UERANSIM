/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_DRB_CountInfoList;

public class RRC_CounterCheckResponse_IEs extends AsnSequence {
    public RRC_DRB_CountInfoList drb_CountInfoList; // mandatory
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_27 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_27 extends AsnSequence {
    }
}

