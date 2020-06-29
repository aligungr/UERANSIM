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

package tr.havelsan.ueransim.api.ue;

import tr.havelsan.ueransim.api.gnb.GnbMessaging;
import tr.havelsan.ueransim.api.nas.NasSecurity;
import tr.havelsan.ueransim.api.ue.mm.*;
import tr.havelsan.ueransim.api.ue.sm.UePduSessionEstablishment;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.utils.FlowLogging;

public class UeMessaging {

    public static void sendNas(UeSimContext ctx, NasMessage message) {
        NasMessage securedNas = NasSecurity.encryptNasMessage(ctx.currentNsc, message);
        GnbMessaging.sendFromUe(ctx.simCtx.gnb, ctx, securedNas);
    }

    public static void handleNas(UeSimContext ctx, NasMessage message) {
        message = NasSecurity.decryptNasMessage(ctx.currentNsc, message);

        if (message instanceof AuthenticationRequest) {
            UeAuthentication.handleAuthenticationRequest(ctx, (AuthenticationRequest) message);
        } else if (message instanceof AuthenticationResult) {
            UeAuthentication.handleAuthenticationResult(ctx, (AuthenticationResult) message);
        } else if (message instanceof AuthenticationResponse) {
            UeAuthentication.handleAuthenticationResponse(ctx, (AuthenticationResponse) message);
        } else if (message instanceof AuthenticationReject) {
            UeAuthentication.handleAuthenticationReject(ctx, (AuthenticationReject) message);
        } else if (message instanceof RegistrationReject) {
            UeRegistration.handleRegistrationReject(ctx, (RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            UeIdentity.handleIdentityRequest(ctx, (IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            UeRegistration.handleRegistrationAccept(ctx, (RegistrationAccept) message);
        } else if (message instanceof ServiceAccept) {
            UeService.handleServiceAccept(ctx, (ServiceAccept) message);
        } else if (message instanceof ServiceReject) {
            UeService.handleServiceReject(ctx, (ServiceReject) message);
        } else if (message instanceof SecurityModeCommand) {
            UeSecurity.handleSecurityModeCommand(ctx, (SecurityModeCommand) message);
        } else if (message instanceof PduSessionEstablishmentAccept) {
            UePduSessionEstablishment.handleEstablishmentAccept(ctx, (PduSessionEstablishmentAccept) message);
        } else if (message instanceof PduSessionEstablishmentReject) {
            UePduSessionEstablishment.handleEstablishmentReject(ctx, (PduSessionEstablishmentReject) message);
        } else {
            FlowLogging.logUnhandledMessage(message);
        }
    }
}
