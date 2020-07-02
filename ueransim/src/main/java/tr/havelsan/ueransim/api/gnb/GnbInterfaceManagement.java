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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupFailure;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class GnbInterfaceManagement {

    public static void sendNgSetupRequest(GnbSimContext ctx) {
        Logging.funcIn("Starting: NGSetupRequest");
        Logging.info(Tag.PROCEDURE_RESULT, "NGSetup procedure is starting");

        GNodeB.sendToNetwork(ctx,
                new NgapBuilder(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                        .addProtocolIE(Ngap.createGlobalGnbId(ctx.config.gnbId, ctx.config.gnbPlmn), NgapCriticality.REJECT)
                        .addProtocolIE(Ngap.createSupportedTAList(ctx.config.supportedTAs), NgapCriticality.REJECT)
                        .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, Values.NGAP_Constants__id_DefaultPagingDRX)
        );

        Logging.funcOut();
    }

    public static void receiveNgSetupResponse(GnbSimContext ctx, NGSetupResponse message) {
        Logging.funcIn("Handling: NGSetupResponse");
        Logging.success(Tag.PROCEDURE_RESULT, "NGSetup procedure is successful");

        Logging.funcOut();
    }

    public static void receiveNgSetupFailure(GnbSimContext ctx, NGSetupFailure message) {
        Logging.funcIn("Handling: NGSetupFailure");
        Logging.error(Tag.PROCEDURE_RESULT, "NGSetup procedure is failed");

        Logging.funcOut();
    }
}
