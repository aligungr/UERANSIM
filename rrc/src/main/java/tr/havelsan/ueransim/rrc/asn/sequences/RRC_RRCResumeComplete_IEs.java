/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UplinkTxDirectCurrentList;

public class RRC_RRCResumeComplete_IEs extends AsnSequence {
    public RRC_DedicatedNAS_Message dedicatedNAS_Message; // optional
    public AsnInteger selectedPLMN_Identity; // optional, VALUE(1..12)
    public RRC_UplinkTxDirectCurrentList uplinkTxDirectCurrentList; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_9 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_9 extends AsnSequence {
    }
}

