package tr.havelsan.ueransim.app.api.ue.mm;

import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.nas.impl.ies.IEDeRegistrationType;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeOriginating;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class MmDeregistration {

    public static void sendDeregistration(UeSimContext ctx, IEDeRegistrationType.ESwitchOff switchOff) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Starting: UE initiated de-registration procedure");

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

        // todo: T3521

        Logging.funcOut();
    }

    public static void receiveDeregistrationAccept(UeSimContext ctx, DeRegistrationAcceptUeOriginating message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: UE-initiated de-registration procedure completion");

        ctx.ueTimers.t3521.stop();
        ctx.ueTimers.t3519.stop();
        ctx.mmCtx.storedSuci = null;

        Logging.success(Tag.PROCEDURE_RESULT, "De-registration is successful");
        Logging.funcOut();
    }
}
