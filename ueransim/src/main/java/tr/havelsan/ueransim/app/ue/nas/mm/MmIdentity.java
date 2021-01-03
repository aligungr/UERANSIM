/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.nas.mm;

import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.common.contexts.NasContext;
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
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;

public class MmIdentity {

    public static void receiveIdentityRequest(NasContext ctx, IdentityRequest message) {
        var response = new IdentityResponse();

        if (message.identityType.value.equals(EIdentityType.SUCI)) {
            response.mobileIdentity = getOrGenerateSuci(ctx);
        } else {
            if (message.identityType.value.equals(EIdentityType.IMEI)) {
                response.mobileIdentity = new IEImeiMobileIdentity(ctx.ueCtx.ueConfig.imei);
            } else {
                response.mobileIdentity = new IENoIdentity();
                Log.error(Tag.FLOW, "Requested identity is not available: %s",
                        message.identityType.value.name());
            }
        }

        MobilityManagement.sendMm(ctx, response);
    }

    public static IESuciMobileIdentity getOrGenerateSuci(NasContext ctx) {
        if (ctx.ueTimers.t3519.isRunning()) {
            Log.debug(Tag.FLOW, "T3519 is running, returning stored SUCI.");
            return ctx.mmCtx.storedSuci;
        }

        ctx.mmCtx.storedSuci = generateSuci(ctx.ueCtx.ueConfig.supi);
        Log.debug(Tag.FLOW, "T3519 is not running, new SUCI generated.");

        ctx.ueTimers.t3519.start();

        return ctx.mmCtx.storedSuci;
    }

    // TODO:
    private static IESuciMobileIdentity generateSuci(Supi supi) {
        if (supi == null) {
            return null;
        }
        if (supi.type.equals("imsi")) {
            String imsi = supi.value;
            String mcc = imsi.substring(0, 3);
            String mnc = imsi.substring(3, Constants.USE_LONG_MNC ? 6 : 5);
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
