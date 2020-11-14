/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mm;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEConfigurationUpdateIndication;
import tr.havelsan.ueransim.nas.impl.messages.ConfigurationUpdateCommand;
import tr.havelsan.ueransim.nas.impl.messages.ConfigurationUpdateComplete;
import tr.havelsan.ueransim.utils.console.Log;

public class MmConfiguration {

    public static void receiveConfigurationUpdate(UeSimContext ctx, ConfigurationUpdateCommand message) {
        Log.funcIn("Handling: Configuration Update Command");

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

        Log.funcOut();
    }
}
