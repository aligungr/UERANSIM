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

import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.ngap.ngap_ies.PagingDRX;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.URSimUtils;

import static tr.havelsan.ueransim.ngap.Values.NGAP_Constants__id_DefaultPagingDRX;

public class GnbInterfaceManagement {

    public static void sendNgSetupRequest(GnbSimContext ctx) {
        GnbMessaging.sendToNetwork(ctx,
                new NgapBuilder(NgapProcedure.NGSetupRequest, NgapCriticality.REJECT)
                        .addProtocolIE(URSimUtils.createGlobalGnbId(ctx.config.gnbId, ctx.config.gnbPlmn), NgapCriticality.REJECT)
                        .addProtocolIE(URSimUtils.createSupportedTAList(ctx.config.supportedTAs), NgapCriticality.REJECT)
                        .addProtocolIE(new PagingDRX(PagingDRX.ASN_v64), NgapCriticality.IGNORE, NGAP_Constants__id_DefaultPagingDRX),
                null
        );
    }

    public static void handleNgSetupResponse(GnbSimContext ctx, NGSetupResponse message) {
        // todo
    }
}
