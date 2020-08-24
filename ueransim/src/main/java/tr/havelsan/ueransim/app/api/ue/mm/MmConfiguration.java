package tr.havelsan.ueransim.app.api.ue.mm;

import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEConfigurationUpdateIndication;
import tr.havelsan.ueransim.nas.impl.messages.ConfigurationUpdateCommand;
import tr.havelsan.ueransim.nas.impl.messages.ConfigurationUpdateComplete;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;

public class MmConfiguration {

    public static void receiveConfigurationUpdate(UeSimContext ctx, ConfigurationUpdateCommand message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: Configuration Update Command");

        ctx.ueTimers.t3346.stop();

        if (message.guti instanceof IE5gGutiMobileIdentity) {
            ctx.mmCtx.storedGuti = (IE5gGutiMobileIdentity) message.guti;
            ctx.mmCtx.storedSuci = null;
            ctx.ueTimers.t3519.stop();
        }

        if (message.taiList != null) {
            ctx.mmCtx.taiList = message.taiList;
        }

        if (message.configurationUpdateIndication != null) {
            if (message.configurationUpdateIndication.red.equals(IEConfigurationUpdateIndication.ERegistrationRequested.REQUESTED)) {
                // todo
            }
            if (message.configurationUpdateIndication.ack.equals(IEConfigurationUpdateIndication.EAcknowledgement.REQUESTED)) {
                MobilityManagement.sendMm(ctx, new ConfigurationUpdateComplete());
            }
        }

        Logging.funcOut();
    }
}
