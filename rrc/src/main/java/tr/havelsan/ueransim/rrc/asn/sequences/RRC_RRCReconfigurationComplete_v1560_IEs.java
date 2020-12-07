/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_RRCReconfigurationComplete_v1560_IEs extends AsnSequence {
    public RRC_scg_Response scg_Response; // optional
    public RRC_nonCriticalExtension_34 nonCriticalExtension; // optional

    public static class RRC_scg_Response extends AsnChoice {
        public AsnOctetString nr_SCG_Response;
        public AsnOctetString eutra_SCG_Response;
    }

    public static class RRC_nonCriticalExtension_34 extends AsnSequence {
    }
}

