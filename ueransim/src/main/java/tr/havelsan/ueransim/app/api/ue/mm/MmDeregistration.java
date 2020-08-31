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

package tr.havelsan.ueransim.app.api.ue.mm;

import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.enums.EMmState;
import tr.havelsan.ueransim.app.enums.EMmSubState;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeTerminated;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeTerminated;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Logging;

public class MmDeregistration {

    public static void sendDeregistration(UeSimContext ctx, IEDeRegistrationType.ESwitchOff switchOff) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Starting: UE initiated de-registration procedure");

        MobilityManagement.switchState(ctx, EMmState.MM_DEREGISTERED_INITIATED, EMmSubState.MM_DEREGISTERED_INITIATED__NA);

        var request = new DeRegistrationRequestUeOriginating();
        request.deRegistrationType = new IEDeRegistrationType();
        request.deRegistrationType.accessType = IEDeRegistrationType.EDeRegistrationAccessType.THREEGPP_ACCESS;
        request.deRegistrationType.reRegistrationRequired = IEDeRegistrationType.EReRegistrationRequired.NOT_REQUIRED;
        request.deRegistrationType.switchOff = switchOff;
        request.ngKSI = ctx.currentNsCtx.ngKsi;

        if (ctx.mmCtx.storedGuti != null) {
            request.mobileIdentity = ctx.mmCtx.storedGuti;
        } else {
            request.mobileIdentity = MmIdentity.getOrGenerateSuci(ctx);
        }

        MobilityManagement.sendMm(ctx, request);

        if (!switchOff.equals(IEDeRegistrationType.ESwitchOff.SWITCH_OFF)
                && (ctx.mmCtx.mmState == EMmState.MM_REGISTERED || ctx.mmCtx.mmState == EMmState.MM_REGISTERED_INITIATED)) {
            ctx.ueTimers.t3521.start();
        }

        Logging.funcOut();
    }

    public static void receiveDeregistrationAccept(UeSimContext ctx, DeRegistrationAcceptUeOriginating message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: UE-initiated de-registration procedure completion");

        ctx.ueTimers.t3521.stop();
        ctx.ueTimers.t3519.stop();
        ctx.mmCtx.storedSuci = null;

        MobilityManagement.switchState(ctx, EMmState.MM_DEREGISTERED, EMmSubState.MM_DEREGISTERED__NA);

        Logging.success(Tag.PROCEDURE_RESULT, "De-registration is successful");
        Logging.funcOut();
    }

    // todo
    public static void receiveDeregistrationRequest(UeSimContext ctx, DeRegistrationRequestUeTerminated message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: Network-initiated de-registration procedure");

        if (message.deRegistrationType.reRegistrationRequired.equals(IEDeRegistrationType.EReRegistrationRequired.REQUIRED)) {
            ctx.ueTimers.t3346.stop();
            ctx.ueTimers.t3396.stop();
            ctx.ueTimers.t3584.stop();
            ctx.ueTimers.t3585.stop();

        } else {
            if (message.mmCause != null) {
                var cause = message.mmCause.value;
            }
        }

        MobilityManagement.sendMm(ctx, new DeRegistrationAcceptUeTerminated());

        MobilityManagement.switchState(ctx, EMmState.MM_DEREGISTERED, EMmSubState.MM_DEREGISTERED__NA);

        Logging.funcOut();
    }
}
