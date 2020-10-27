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

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NgapInterfaceManagement {

    public static void sendNgSetupRequest(GnbSimContext ctx, Guami associatedAmf) {
        Log.funcIn("Starting: NGSetupRequest");
        Log.info(Tag.PROCEDURE_RESULT, "NGSetup procedure is starting");

        var msg = new NGAP_NGSetupRequest();
        msg.addProtocolIe(NgapUtils.createGlobalGnbId(ctx.config.gnbId, ctx.config.gnbPlmn));
        msg.addProtocolIe(NgapUtils.createSupportedTAList(ctx.config.supportedTAs));
        msg.addProtocolIe(NGAP_PagingDRX.V64);

        NgapTransfer.sendNgapNonUe(ctx, associatedAmf, msg);
        Log.funcOut();
    }

    public static void receiveNgSetupResponse(GnbSimContext ctx, NGAP_NGSetupResponse message) {
        Log.funcIn("Handling: NGSetupResponse");
        Log.success(Tag.PROCEDURE_RESULT, "NGSetup procedure is successful");

        Log.funcOut();
    }

    public static void receiveNgSetupFailure(GnbSimContext ctx, NGAP_NGSetupFailure message) {
        Log.funcIn("Handling: NGSetupFailure");
        Log.error(Tag.PROCEDURE_RESULT, "NGSetup procedure is failed");

        Log.funcOut();
    }
}
