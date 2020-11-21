/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mm;

import tr.havelsan.ueransim.app.common.enums.EMmState;
import tr.havelsan.ueransim.app.common.enums.EMmSubState;
import tr.havelsan.ueransim.app.common.enums.ERmState;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeTerminated;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeTerminated;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class MmDeregistration {

    public static void sendDeregistration(UeSimContext ctx, IEDeRegistrationType.ESwitchOff switchOff) {
        Log.funcIn("Starting: UE initiated de-registration procedure");

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

        Log.funcOut();
    }

    public static void receiveDeregistrationAccept(UeSimContext ctx, DeRegistrationAcceptUeOriginating message) {
        Log.funcIn("Handling: UE-initiated de-registration procedure completion");

        ctx.ueTimers.t3521.stop();
        ctx.ueTimers.t3519.stop();
        ctx.mmCtx.storedSuci = null;

        MobilityManagement.switchState(ctx, EMmState.MM_DEREGISTERED, EMmSubState.MM_DEREGISTERED__NA);
        MobilityManagement.switchState(ctx, ERmState.RM_DEREGISTERED);

        Log.success(Tag.PROC, "De-registration is successful");
        Log.funcOut();
    }

    // todo
    public static void receiveDeregistrationRequest(UeSimContext ctx, DeRegistrationRequestUeTerminated message) {
        Log.funcIn("Handling: Network-initiated de-registration procedure");

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
        MobilityManagement.switchState(ctx, ERmState.RM_DEREGISTERED);

        Log.funcOut();
    }
}
