/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.rrc.choices.RRC_InitialUE_Identity;
import tr.havelsan.ueransim.rrc.enums.RRC_EstablishmentCause;
import tr.havelsan.ueransim.rrc.messages.RrcSetupRequest;
import tr.havelsan.ueransim.rrc.sequences.RRC_RRCSetupRequest_IEs;
import tr.havelsan.ueransim.utils.bits.BitString;

public class RrcConnectionManagement {

    public static void sendRrcSetupRequest(UeRrcContext ctx) {
        RrcSetupRequest rrc = new RrcSetupRequest();
        rrc.rrcSetupRequest = new RRC_RRCSetupRequest_IEs();
        rrc.rrcSetupRequest.establishmentCause = RRC_EstablishmentCause.MO_DATA;
        rrc.rrcSetupRequest.ue_Identity = new RRC_InitialUE_Identity();
        rrc.rrcSetupRequest.ue_Identity.randomValue = new BitString();
        rrc.rrcSetupRequest.ue_Identity.randomValue.clear(38);

        RrcTransport.sendRrcMessage(ctx, rrc);
    }
}
