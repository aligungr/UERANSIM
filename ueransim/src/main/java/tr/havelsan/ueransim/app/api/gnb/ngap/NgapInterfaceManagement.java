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

package tr.havelsan.ueransim.app.api.gnb.ngap;

import tr.havelsan.ueransim.app.api.gnb.GNodeB;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.api.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.app.structs.Guami;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.utils.console.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class NgapInterfaceManagement {

    public static void sendNgSetupRequest(GnbSimContext ctx, Guami associatedAmf) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Starting: NGSetupRequest");
        Logging.info(Tag.PROCEDURE_RESULT, "NGSetup procedure is starting");

        var msg = new NGAP_NGSetupRequest();
        msg.addProtocolIe(NgapUtils.createGlobalGnbId(ctx.config.gnbId, ctx.config.gnbPlmn));
        msg.addProtocolIe(NgapUtils.createSupportedTAList(ctx.config.supportedTAs));
        msg.addProtocolIe(NGAP_PagingDRX.V64);

        GNodeB.sendNgapNonUe(ctx, associatedAmf, msg);
        Logging.funcOut();
    }

    public static void receiveNgSetupResponse(GnbSimContext ctx, NGAP_NGSetupResponse message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: NGSetupResponse");
        Logging.success(Tag.PROCEDURE_RESULT, "NGSetup procedure is successful");

        Logging.funcOut();
    }

    public static void receiveNgSetupFailure(GnbSimContext ctx, NGAP_NGSetupFailure message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: NGSetupFailure");
        Logging.error(Tag.PROCEDURE_RESULT, "NGSetup procedure is failed");

        Logging.funcOut();
    }
}
