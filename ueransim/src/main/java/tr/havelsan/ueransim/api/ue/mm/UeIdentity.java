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

package tr.havelsan.ueransim.api.ue.mm;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.ies.IEImeiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEImsiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IENoIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IESuciMobileIdentity;
import tr.havelsan.ueransim.nas.impl.messages.IdentityRequest;
import tr.havelsan.ueransim.nas.impl.messages.IdentityResponse;
import tr.havelsan.ueransim.nas.impl.values.VHomeNetworkPki;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.structs.Supi;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class UeIdentity {

    public static void handleIdentityRequest(SimulationContext ctx, IdentityRequest message) {
        Logging.funcIn("Handling: Identity Request");

        IdentityResponse response = new IdentityResponse();

        if (message.identityType.value.equals(EIdentityType.SUCI)) {
            response.mobileIdentity = getOrGenerateSuci(ctx);
        } else {
            if (message.identityType.value.equals(EIdentityType.IMEI)) {
                response.mobileIdentity = new IEImeiMobileIdentity(ctx.ueData.imei);
            } else {
                response.mobileIdentity = new IENoIdentity();
                Logging.error(Tag.PROC, "Requested identity is not available: %s",
                        message.identityType.value.name());
            }
        }

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
        Logging.funcOut();
    }

    public static IESuciMobileIdentity getOrGenerateSuci(SimulationContext ctx) {
        Logging.funcIn("Get or Generate SUCI");
        if (ctx.ueTimers.t3519.isRunning()) {
            Logging.debug(Tag.PROC, "T3519 is running, returning stored SUCI.");
            Logging.funcOut();
            return ctx.ueData.storedSuci;
        }

        ctx.ueData.storedSuci = generateSuci(ctx.ueData.supi);
        Logging.debug(Tag.PROC, "T3519 is not running, new SUCI generated.");

        ctx.ueTimers.t3519.start();

        Logging.funcOut();
        return ctx.ueData.storedSuci;
    }

    // TODO:
    private static IESuciMobileIdentity generateSuci(Supi supi) {
        if (supi == null) {
            return null;
        }
        if (supi.type.equals("imsi")) {
            String imsi = supi.value;
            String mcc = imsi.substring(0, 3);
            String mnc = imsi.substring(3, Constants.ALWAYS_LONG_MNC ? 6 : 5);
            String msin = imsi.substring(mcc.length() + mnc.length());

            IEImsiMobileIdentity res = new IEImsiMobileIdentity();
            res.mcc = EMccValue.fromValue(Integer.parseInt(mcc));
            res.mnc = EMncValue.fromValue(Integer.parseInt(mnc));
            res.routingIndicator = "0000";
            res.protectionSchemaId = IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEME;
            res.homeNetworkPublicKeyIdentifier = new VHomeNetworkPki(0);
            res.schemeOutput = msin;
            return res;
        } else {
            throw new NotImplementedException("this supi format not implemented yet");
        }
    }
}
