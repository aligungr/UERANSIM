/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;

public class RRC_DLInformationTransfer_IEs extends AsnSequence {
    public RRC_DedicatedNAS_Message dedicatedNAS_Message; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_38 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_38 extends AsnSequence {
    }
}

