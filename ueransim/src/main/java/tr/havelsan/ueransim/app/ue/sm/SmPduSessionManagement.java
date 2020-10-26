/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.ue.sm;

import tr.havelsan.ueransim.app.common.PduSession;
import tr.havelsan.ueransim.app.common.ProcedureTransaction;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.EProcedureTransactionIdentity;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

class SmPduSessionManagement {

    public static EPduSessionIdentity allocatePduSessionId(UeSimContext ctx) {
        var arr = ctx.smCtx.pduSessions;

        int id = -1;
        for (int i = PduSession.MIN_ID; i <= PduSession.MAX_ID; i++) {
            if (arr[i] == null) {
                id = i;
                break;
            }
        }

        if (id == -1) {
            Log.error(Tag.PROC, "PDU session allocation failed");
            return null;
        }

        var val = EPduSessionIdentity.fromValue(id);
        arr[id] = new PduSession(val);

        Log.debug(Tag.PROC, "PDU session allocated: %s", val);
        return val;
    }

    public static EProcedureTransactionIdentity allocateProcedureTransactionId(UeSimContext ctx) {
        var arr = ctx.smCtx.procedureTransactions;

        int id = -1;
        for (int i = ProcedureTransaction.MIN_ID; i <= ProcedureTransaction.MAX_ID; i++) {
            if (arr[i] == null) {
                id = i;
                break;
            }
        }

        if (id == -1) {
            Log.error(Tag.PROC, "PTI allocation failed");
            return null;
        }

        arr[id] = new ProcedureTransaction();

        var val = EProcedureTransactionIdentity.fromValue(id);
        Log.debug(Tag.PROC, "PTI allocated: %s", val);
        return val;
    }

    public static void releaseProcedureTransactionId(UeSimContext ctx, EProcedureTransactionIdentity pti) {
        ctx.smCtx.procedureTransactions[pti.intValue()] = ProcedureTransaction.RELEASED;
        Log.debug(Tag.PROC, "PTI released: %s", pti);
    }

    public static void releasePduSession(UeSimContext ctx, EPduSessionIdentity psi) {
        ctx.smCtx.pduSessions[psi.intValue()] = PduSession.RELEASED;
        Log.debug(Tag.PROC, "PDU Session released: %s", psi);
    }
}
