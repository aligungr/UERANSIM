/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_ULInformationTransferMRDC_IEs extends AsnSequence {
    public AsnOctetString ul_DCCH_MessageNR; // optional
    public AsnOctetString ul_DCCH_MessageEUTRA; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_1 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_1 extends AsnSequence {
    }
}

