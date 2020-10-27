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
