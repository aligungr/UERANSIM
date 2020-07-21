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

import tr.havelsan.ueransim.api.sys.Simulation;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.events.ue.UeDownlinkNasEvent;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IndexToRFSP;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RANPagingPriority;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UESecurityCapabilities;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;

public class GnbUeContextManagement {

    public static void receiveInitialContextSetup(GnbSimContext ctx, NGAP_InitialContextSetupRequest message) {
        Debugging.assertThread(ctx);

        var ueId = GnbUeManagement.findAssociatedUeIdDefault(ctx, message);

        // todo
        GNodeB.sendToNetworkUeAssociated(ctx, ueId, new NGAP_InitialContextSetupResponse());

        var nasMessage = message.getNasMessage();
        if (nasMessage != null) {
            Simulation.pushUeEvent(ctx.simCtx, ueId, new UeDownlinkNasEvent(NasEncoder.nasPduS(nasMessage)));
        }
    }

    public static void receiveContextReleaseCommand(GnbSimContext ctx, NGAP_UEContextReleaseCommand message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: UE Context Release Command (AMF initiated)");

        var ueId = GnbUeManagement.findAssociatedUeForUeNgapIds(ctx, message);

        // todo
        GNodeB.sendToNetworkUeAssociated(ctx, ueId, new NGAP_UEContextReleaseComplete());

        Logging.funcOut();
    }

    public static void receiveContextModificationRequest(GnbSimContext ctx, NGAP_UEContextModificationRequest message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: UE Context Release Command (AMF initiated)");

        var ueId = GnbUeManagement.findAssociatedUeIdDefault(ctx, message);
        var ue = ctx.ueContexts.get(ueId);

        // todo
        GNodeB.sendToNetworkUeAssociated(ctx, ueId, new NGAP_UEContextModificationResponse());

        Logging.funcOut();
    }
}
