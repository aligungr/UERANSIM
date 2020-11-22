/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.itms.IwConnectionRelease;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
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

    public static void receiveInitialContextSetup(GnbSimContext ctx, NGAP_InitialContextSetupRequest message) {
        Log.funcIn("Handling: Initial Context Setup Request");

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
            ctx.nts.findTask(ItmsId.GNB_TASK_MR).push(new IwDownlinkNas(ueId, NasEncoder.nasPduS(nasMessage)));
        }

        Log.funcOut();
    }

    public static void receiveContextReleaseCommand(GnbSimContext ctx, NGAP_UEContextReleaseCommand message) {
        Log.funcIn("Handling: UE Context Release Command (AMF initiated)");

        var ueId = NgapUeManagement.findAssociatedUeForUeNgapIds(ctx, message);

        // todo: NG-RAN node shall release all related signalling and user data transport resources
        // ...

        ctx.nts.findTask(ItmsId.GNB_TASK_MR).push(new IwConnectionRelease(ueId));

        // send release complete message
        var response = new NGAP_UEContextReleaseComplete();
        NgapTransfer.sendNgapUeAssociated(ctx, ueId, response);

        ctx.ueContexts.remove(ueId);

        Log.funcOut();
    }

    public static void receiveContextModificationRequest(GnbSimContext ctx, NGAP_UEContextModificationRequest message) {
        Log.funcIn("Handling: UE Context Modification Request");

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
                Log.funcOut();
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

        NgapTransfer.sendNgapUeAssociated(ctx, ueId, new NGAP_UEContextModificationResponse());
        Log.funcOut();
    }

    private static boolean isUeSecurityCapabilitiesValid(GnbSimContext ctx, NGAP_UESecurityCapabilities capabilities) {
        // todo: check if UE security capabilities are valid
        return true;
    }
}
