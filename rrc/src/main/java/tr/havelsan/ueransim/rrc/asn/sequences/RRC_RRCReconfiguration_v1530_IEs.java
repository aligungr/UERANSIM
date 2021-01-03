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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;

public class RRC_RRCReconfiguration_v1530_IEs extends AsnSequence {
    public AsnOctetString masterCellGroup; // optional, SIZE(0..MAX)
    public RRC_fullConfig_1 fullConfig; // optional
    public RRC_dedicatedNAS_MessageList dedicatedNAS_MessageList; // optional, SIZE(1..29)
    public RRC_MasterKeyUpdate masterKeyUpdate; // optional
    public AsnOctetString dedicatedSIB1_Delivery; // optional, SIZE(0..MAX)
    public AsnOctetString dedicatedSystemInformationDelivery; // optional, SIZE(0..MAX)
    public RRC_OtherConfig otherConfig; // optional
    public RRC_RRCReconfiguration_v1540_IEs nonCriticalExtension; // optional

    // SIZE(1..29)
    public static class RRC_dedicatedNAS_MessageList extends AsnSequenceOf<RRC_DedicatedNAS_Message> {
    }

    public static class RRC_fullConfig_1 extends AsnEnumerated {
        public static final RRC_fullConfig_1 TRUE = new RRC_fullConfig_1(0);
    
        private RRC_fullConfig_1(long value) {
            super(value);
        }
    }
}

