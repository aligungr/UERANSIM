/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.rrc.RrcMessage;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_InitialUE_Identity;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EstablishmentCause;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSetupRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSetupRequest_IEs;

public class RrcConnectionManagement {

    public static void sendRrcSetupRequest(UeRrcContext ctx) {
        RRC_RRCSetupRequest rrc = new RRC_RRCSetupRequest();
        rrc.rrcSetupRequest = new RRC_RRCSetupRequest_IEs();
        rrc.rrcSetupRequest.establishmentCause = RRC_EstablishmentCause.MO_DATA;
        rrc.rrcSetupRequest.ue_Identity = new RRC_InitialUE_Identity();
        rrc.rrcSetupRequest.ue_Identity.randomValue = AsnBitString.spare(39);

        RrcTransport.sendRrcMessage(ctx, new RrcMessage(rrc));
    }
}
