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

package tr.havelsan.ueransim.app.api.gnb;

import tr.havelsan.ueransim.app.api.gnb.ngap.*;
import tr.havelsan.ueransim.app.api.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.events.gnb.GnbCommandEvent;
import tr.havelsan.ueransim.app.events.gnb.GnbUplinkNasEvent;
import tr.havelsan.ueransim.app.events.gnb.SctpAssociationSetupEvent;
import tr.havelsan.ueransim.app.structs.Guami;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UserLocationInformation;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Logging;

import java.util.UUID;

public class GNodeB {

    public static void cycle(GnbSimContext ctx) {
        var event = ctx.popEvent();
        if (event != null) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);
        }

        if (event instanceof SctpAssociationSetupEvent) {
            var amfCtx = ctx.amfContexts.get(((SctpAssociationSetupEvent) event).guami);
            amfCtx.association = ((SctpAssociationSetupEvent) event).association;

            NgapInterfaceManagement.sendNgSetupRequest(ctx, amfCtx.guami);
        } else if (event instanceof GnbCommandEvent) {
            var cmd = ((GnbCommandEvent) event).cmd;
            switch (cmd) {
                default:
                    Logging.error(Tag.EVENT, "GnbCommandEvent not recognized: %s", cmd);
                    break;
            }
        } else if (event instanceof GnbUplinkNasEvent) {
            var e = (GnbUplinkNasEvent) event;
            NgapNasTransport.receiveUplinkNasTransport(ctx, e.ue, NasDecoder.nasPdu(e.nasPdu));
        }
    }
}
