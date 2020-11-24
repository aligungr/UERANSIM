/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.contexts.NgapUeContext;
import tr.havelsan.ueransim.app.common.exceptions.NgapErrorException;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UE_NGAP_IDs;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseProtocol;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseRadioNetwork;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit10;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class NgapUeManagement {

    public static void createUeContext(NgapGnbContext ctx, UUID ueId) {
        var gnbUeCtx = new NgapUeContext(ueId);
        gnbUeCtx.ranUeNgapId = ++ctx.ueNgapIdCounter;
        gnbUeCtx.amfUeNgapId = null;

        ctx.ueContexts.put(ueId, gnbUeCtx);
        selectAmfForUe(ctx, gnbUeCtx);
    }

    private static UUID findUeByRanId(NgapGnbContext ctx, long ranUeNgapId) {
        // todo: make O(1)
        for (var entry : ctx.ueContexts.entrySet()) {
            if (entry.getValue().ranUeNgapId == ranUeNgapId) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static UUID findUeByAmfId(NgapGnbContext ctx, long amfUeNgapId) {
        // todo: make O(1)
        for (var entry : ctx.ueContexts.entrySet()) {
            if (entry.getValue().amfUeNgapId == amfUeNgapId) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void selectAmfForUe(NgapGnbContext ctx, NgapUeContext ueCtx) {
        // todo: always first configured AMF is selected for now
        ueCtx.associatedAmf = ctx.gnbCtx.config.amfConfigs[0].guami;
    }

    public static Guami selectNewAmfForReAllocation(NgapGnbContext ctx, Guami initiatedAmf, Bit10 amfSetId) {
        Log.funcIn("Handling: Select AMF from AMFSetId");
        Log.debug(Tag.VALUE, "AMFSetId: %s", amfSetId);

        Guami res = null;

        // Filter the AMFs with MCC-MNC-RegionId-SetId
        var amfs = Utils.streamToList(ctx.amfContexts.values().stream().filter(gnbAmfContext ->
                gnbAmfContext.guami.mcc.equals(initiatedAmf.mcc)
                        && gnbAmfContext.guami.mnc.equals(initiatedAmf.mnc)
                        && gnbAmfContext.guami.amfRegionId.equals(initiatedAmf.amfRegionId)
                        && gnbAmfContext.guami.amfSetId.equals(amfSetId)));

        // If set id is the same, select a different AMF from initiated AMF
        if (amfSetId.equals(initiatedAmf.amfSetId)) {
            amfs = Utils.streamToList(amfs.stream().filter(gnbAmfContext ->
                    !gnbAmfContext.guami.amfPointer.equals(initiatedAmf.amfPointer)));
        }

        // Take the first AMF satisfying above conditions
        if (!amfs.isEmpty()) {
            res = amfs.get(0).guami;
        }

        Log.funcOut();
        return res;
    }

    private static UUID findAssociatedUeId(NgapGnbContext ctx, NGAP_AMF_UE_NGAP_ID amfUeNgapId, NGAP_RAN_UE_NGAP_ID ranUeNgapId) {
        if (amfUeNgapId == null || ranUeNgapId == null) {
            throw new NgapErrorException(NGAP_CauseProtocol.ABSTRACT_SYNTAX_ERROR_FALSELY_CONSTRUCTED_MESSAGE);
        }

        long amf = amfUeNgapId.value;
        long ran = ranUeNgapId.value;

        var associatedUe = NgapUeManagement.findUeByRanId(ctx, ran);
        if (associatedUe == null) {
            throw new NgapErrorException(NGAP_CauseRadioNetwork.UNKNOWN_LOCAL_UE_NGAP_ID);
        }

        var gnbUeContext = ctx.ueContexts.get(associatedUe);
        if (gnbUeContext.amfUeNgapId == null) {
            gnbUeContext.amfUeNgapId = amf;
        } else if (amf != gnbUeContext.amfUeNgapId) {
            throw new NgapErrorException(NGAP_CauseRadioNetwork.INCONSISTENT_REMOTE_UE_NGAP_ID);
        }

        return associatedUe;
    }

    private static UUID findAssociatedUeId(NgapGnbContext ctx, NGAP_UE_NGAP_IDs ueNgapIDs) {
        if (ueNgapIDs == null) {
            throw new NgapErrorException(NGAP_CauseProtocol.ABSTRACT_SYNTAX_ERROR_FALSELY_CONSTRUCTED_MESSAGE);
        }
        if (ueNgapIDs.uE_NGAP_ID_pair != null) {
            return findAssociatedUeId(ctx, ueNgapIDs.uE_NGAP_ID_pair.aMF_UE_NGAP_ID, ueNgapIDs.uE_NGAP_ID_pair.rAN_UE_NGAP_ID);
        }
        if (ueNgapIDs.aMF_UE_NGAP_ID != null) {
            var ue = findUeByAmfId(ctx, ueNgapIDs.aMF_UE_NGAP_ID.value);
            if (ue != null) return ue;
            throw new NgapErrorException(NGAP_CauseRadioNetwork.INCONSISTENT_REMOTE_UE_NGAP_ID);
        }
        throw new NgapErrorException(NGAP_CauseProtocol.ABSTRACT_SYNTAX_ERROR_FALSELY_CONSTRUCTED_MESSAGE);
    }

    public static UUID findAssociatedUeIdDefault(NgapGnbContext ctx, NGAP_BaseMessage ngapMessage) {
        var ieAmfUeNgapId = ngapMessage.getProtocolIe(NGAP_AMF_UE_NGAP_ID.class);
        var ieRanUeNgapId = ngapMessage.getProtocolIe(NGAP_RAN_UE_NGAP_ID.class);
        return findAssociatedUeId(ctx, ieAmfUeNgapId, ieRanUeNgapId);
    }

    public static UUID findAssociatedUeForUeNgapIds(NgapGnbContext ctx, NGAP_BaseMessage message) {
        var ie = message.getProtocolIe(NGAP_UE_NGAP_IDs.class);
        return findAssociatedUeId(ctx, ie);
    }
}
