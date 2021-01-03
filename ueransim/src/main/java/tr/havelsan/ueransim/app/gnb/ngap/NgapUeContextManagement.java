/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.nts.IwUeContextCreate;
import tr.havelsan.ueransim.app.common.nts.IwUeContextUpdate;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_MaskedIMEISV;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseMisc;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingPriority;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_UERadioCapability;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_MobilityRestrictionList;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UEAggregateMaximumBitRate;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UESecurityCapabilities;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;


public class NgapUeContextManagement {

    public static void receiveInitialContextSetup(NgapGnbContext ctx, NGAP_InitialContextSetupRequest message) {
        var ueId = NgapUeManagement.findAssociatedUeIdDefault(ctx, message);
        var ue = ctx.ueContexts.get(ueId);

        ue.indexToRfsp = message.getProtocolIe(NGAP_IndexToRFSP.class);
        ue.maskedImeiSv = message.getProtocolIe(NGAP_MaskedIMEISV.class);
        ue.aggregateMaximumBitRate = message.getProtocolIe(NGAP_UEAggregateMaximumBitRate.class);
        ue.mobilityRestrictions = message.getProtocolIe(NGAP_MobilityRestrictionList.class);
        ue.radioCapability = message.getProtocolIe(NGAP_UERadioCapability.class);
        ue.securityKey = message.getProtocolIe(NGAP_SecurityKey.class);

        var secCaps = message.getProtocolIe(NGAP_UESecurityCapabilities.class);
        if (isUeSecurityCapabilitiesValid(ctx, secCaps)) {
            ue.securityCapabilities = secCaps;
            NgapTransfer.sendNgapUeAssociated(ctx, ueId, new NGAP_InitialContextSetupResponse());
        } else {
            var response = new NGAP_InitialContextSetupFailure();
            response.addProtocolIe(NGAP_CauseMisc.UNSPECIFIED);

            NgapTransfer.sendNgapUeAssociated(ctx, ueId, response);
        }

        var nasMessage = message.getNasMessage();
        if (nasMessage != null) {
            NgapNasTransport.deliverDlNas(ctx, ueId, NasEncoder.nasPduS(nasMessage));
        }

        ctx.gtpTask.push(new IwUeContextCreate(ueId, ue.aggregateMaximumBitRate));
    }

    public static void receiveContextReleaseCommand(NgapGnbContext ctx, NGAP_UEContextReleaseCommand message) {
        var ueId = NgapUeManagement.findAssociatedUeForUeNgapIds(ctx, message);

        // todo: NG-RAN node shall release all related signalling and user data transport resources
        // ...

        // send release complete message
        var response = new NGAP_UEContextReleaseComplete();
        NgapTransfer.sendNgapUeAssociated(ctx, ueId, response);

        ctx.ueContexts.remove(ueId);
    }

    public static void receiveContextModificationRequest(NgapGnbContext ctx, NGAP_UEContextModificationRequest message) {
        var ueId = NgapUeManagement.findAssociatedUeIdDefault(ctx, message);
        var ue = ctx.ueContexts.get(ueId);

        var ieSecurityKey = message.getProtocolIe(NGAP_SecurityKey.class);
        if (ieSecurityKey != null) {
            ue.securityKey = ieSecurityKey;
        }

        var ieSecCaps = message.getProtocolIe(NGAP_UESecurityCapabilities.class);
        if (ieSecCaps != null) {
            if (isUeSecurityCapabilitiesValid(ctx, ieSecCaps)) {
                ue.securityCapabilities = ieSecCaps;
            } else {
                var response = new NGAP_UEContextModificationFailure();
                response.addProtocolIe(NGAP_CauseMisc.UNSPECIFIED);

                NgapTransfer.sendNgapUeAssociated(ctx, ueId, response);
                return;
            }
        }

        var ieIndexToRfsp = message.getProtocolIe(NGAP_IndexToRFSP.class);
        if (ieIndexToRfsp != null) {
            ue.indexToRfsp = ieIndexToRfsp;
        }

        var iePagingPriority = message.getProtocolIe(NGAP_PagingPriority.class);
        if (iePagingPriority != null) {
            ue.pagingPriority = iePagingPriority;
        }

        var ieAggMaxBitRate = message.getProtocolIe(NGAP_UEAggregateMaximumBitRate.class);
        if (ieAggMaxBitRate != null) {
            ue.aggregateMaximumBitRate = ieAggMaxBitRate;
        }

        var ieNewAmfUeNgapId = message.getProtocolIe(NGAP_AMF_UE_NGAP_ID.class, 1);
        if (ieNewAmfUeNgapId != null) {
            long old = ue.amfUeNgapId;
            ue.amfUeNgapId = ieNewAmfUeNgapId.value;
            Log.info(Tag.FLOW, "AMF_UE_NGAP_ID changed from %d to %d.", old, ue.amfUeNgapId);
        }

        // Using ieAggMaxBitRate instead of ue.ieAggMaxBitRate, because it is update but not create.
        //  so we consider received message since the ie can be null.
        ctx.gtpTask.push(new IwUeContextUpdate(ueId, ieAggMaxBitRate));

        NgapTransfer.sendNgapUeAssociated(ctx, ueId, new NGAP_UEContextModificationResponse());
    }

    private static boolean isUeSecurityCapabilitiesValid(NgapGnbContext ctx, NGAP_UESecurityCapabilities capabilities) {
        // todo: check if UE security capabilities are valid
        return true;
    }
}
