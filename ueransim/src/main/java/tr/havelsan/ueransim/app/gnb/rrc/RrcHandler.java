/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.contexts.GnbRrcUeContext;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.rrc.RrcDataUnitType;
import tr.havelsan.ueransim.rrc.RrcEncoding;
import tr.havelsan.ueransim.rrc.RrcMessage;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellGroupId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RRC_TransactionIdentifier;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RrcHandler {

    public static void receiveSetupRequest(GnbRrcContext ctx, GnbRrcUeContext ue, RRC_RRCSetupRequest msg) {
        ue.initialId = msg.rrcSetupRequest.ue_Identity;
        ue.establishmentCause = msg.rrcSetupRequest.establishmentCause;

        var cellGroupConfig = new RRC_CellGroupConfig();
        cellGroupConfig.cellGroupId = new RRC_CellGroupId(0);

        var ies = new RRC_RRCSetup_IEs();
        ies.radioBearerConfig = new RRC_RadioBearerConfig();
        ies.masterCellGroup = new AsnOctetString(RrcEncoding.encodeUperS(cellGroupConfig, RrcDataUnitType.CellGroupConfig));

        RRC_RRCSetup setup = new RRC_RRCSetup();
        setup.rrc_TransactionIdentifier = new RRC_RRC_TransactionIdentifier(1);
        setup.criticalExtensions = new RRC_RRCSetup.RRC_criticalExtensions_14();
        setup.criticalExtensions.rrcSetup = ies;

        RrcTransport.sendRrcMessage(ctx, ue.ctxId, new RrcMessage(setup));
    }

    public static void receiveUlInformationTransfer(GnbRrcContext ctx, GnbRrcUeContext ue, RRC_ULInformationTransfer msg) {
        var nasPdu = msg.criticalExtensions.ulInformationTransfer
                .dedicatedNAS_Message.value;
        if (nasPdu != null) {
            RrcNasTransport.deliverUlNas(ctx, ue.ctxId, nasPdu);
        }
    }
}
