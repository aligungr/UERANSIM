/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MobilityFromNRCommand_IEs extends AsnSequence {
    public RRC_targetRAT_Type targetRAT_Type; // mandatory
    public AsnOctetString targetRAT_MessageContainer; // mandatory
    public AsnOctetString nas_SecurityParamFromNR; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_26 nonCriticalExtension; // optional

    public static class RRC_targetRAT_Type extends AsnEnumerated {
        public static final RRC_targetRAT_Type EUTRA = new RRC_targetRAT_Type(0);
        public static final RRC_targetRAT_Type SPARE3 = new RRC_targetRAT_Type(1);
        public static final RRC_targetRAT_Type SPARE2 = new RRC_targetRAT_Type(2);
        public static final RRC_targetRAT_Type SPARE1 = new RRC_targetRAT_Type(3);
    
        private RRC_targetRAT_Type(long value) {
            super(value);
        }
    }

    public static class RRC_nonCriticalExtension_26 extends AsnSequence {
    }
}

