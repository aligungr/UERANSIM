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
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.ngap2.NgapMessageType;
import tr.havelsan.ueransim.utils.Debugging;

import java.util.UUID;

public class GnbNasTransport {

    public static void receiveUplinkNasTransport(GnbSimContext ctx, UUID associatedUe, NasMessage nasMessage) {
        NgapBuilder ngap;

        if (ctx.ueContexts.containsKey(associatedUe)) {
            ngap = new NgapBuilder(NgapMessageType.UplinkNASTransport);
        } else {
            ngap = new NgapBuilder(NgapMessageType.InitialUEMessage);
            ngap.addProtocolIE(new RRCEstablishmentCause(RRCEstablishmentCause.ASN_mo_Data));

            GnbUeManagement.createUeContext(ctx, associatedUe);
        }

        if (nasMessage != null) {
            ngap.addNasPdu(nasMessage);
        }

        GNodeB.sendToNetworkUeAssociated(ctx, associatedUe, ngap);
    }

    public static void receiveDownlinkNasTransport(GnbSimContext ctx, UUID associatedUe, DownlinkNASTransport message) {
        Debugging.assertThread(ctx);

        var nasMessage = NgapInternal.extractNasMessage(message);
        if (nasMessage != null) {
            Simulation.pushUeEvent(ctx.simCtx, associatedUe, new UeDownlinkNasEvent(NasEncoder.nasPduS(nasMessage)));
        }
    }
}
