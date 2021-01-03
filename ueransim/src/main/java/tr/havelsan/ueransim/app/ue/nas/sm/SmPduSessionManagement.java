/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.nas.sm;

import tr.havelsan.ueransim.app.common.PduSession;
import tr.havelsan.ueransim.app.common.ProcedureTransaction;
import tr.havelsan.ueransim.app.common.contexts.NasContext;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.EProcedureTransactionIdentity;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

class SmPduSessionManagement {

    public static EPduSessionIdentity allocatePduSessionId(NasContext ctx) {
        var arr = ctx.smCtx.pduSessions;

        int id = -1;
        for (int i = PduSession.MIN_ID; i <= PduSession.MAX_ID; i++) {
            if (arr[i] == null) {
                id = i;
                break;
            }
        }

        if (id == -1) {
            Log.error(Tag.FLOW, "PDU session allocation failed");
            return null;
        }

        var val = EPduSessionIdentity.fromValue(id);
        arr[id] = new PduSession(val);

        Log.debug(Tag.FLOW, "PDU session allocated: %s", val);
        return val;
    }

    public static EProcedureTransactionIdentity allocateProcedureTransactionId(NasContext ctx) {
        var arr = ctx.smCtx.procedureTransactions;

        int id = -1;
        for (int i = ProcedureTransaction.MIN_ID; i <= ProcedureTransaction.MAX_ID; i++) {
            if (arr[i] == null) {
                id = i;
                break;
            }
        }

        if (id == -1) {
            Log.error(Tag.FLOW, "PTI allocation failed");
            return null;
        }

        arr[id] = new ProcedureTransaction();

        var val = EProcedureTransactionIdentity.fromValue(id);
        Log.debug(Tag.FLOW, "PTI allocated: %s", val);
        return val;
    }

    public static void releaseProcedureTransactionId(NasContext ctx, EProcedureTransactionIdentity pti) {
        ctx.smCtx.procedureTransactions[pti.intValue()] = ProcedureTransaction.RELEASED;
        Log.debug(Tag.FLOW, "PTI released: %s", pti);
    }

    public static void releasePduSession(NasContext ctx, EPduSessionIdentity psi) {
        ctx.smCtx.pduSessions[psi.intValue()] = PduSession.RELEASED;
        Log.debug(Tag.FLOW, "PDU Session released: %s", psi);
    }
}
