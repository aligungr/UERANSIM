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

package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.ngap.ngap_ies.ResetAll;
import tr.havelsan.ueransim.ngap.ngap_ies.ResetType;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGResetAcknowledge;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCause;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

import static tr.havelsan.ueransim.ngap.ngap_ies.ResetType.ASN_nG_Interface;

public class NgResetFlow extends BaseFlow {

    public NgResetFlow(SimulationContext simContext) {
        super(simContext);
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        var ngapCause = NgapCause.RADIO_NETWORK_USER_INACTIVITY;

        var resetAll = new ResetAll();
        var resetType = new ResetType(ASN_nG_Interface, resetAll);

        send(new SendingMessage(
                new NgapBuilder(NgapProcedure.NGReset, NgapCriticality.REJECT)
                        .addCause(ngapCause, NgapCriticality.IGNORE)
                        .addProtocolIE(resetType, NgapCriticality.REJECT), null
        ));

        return this::waitNgResetAcknowledge;
    }

    private State waitNgResetAcknowledge(IncomingMessage message) {
        var ngSetupResponse = message.getNgapMessage(NGResetAcknowledge.class);
        if (ngSetupResponse == null) {
            FlowLogging.logUnhandledMessage(message, NGResetAcknowledge.class);
            return this::waitNgResetAcknowledge;
        }

        return flowComplete();
    }

    @Override
    public void onReceive(IncomingMessage incomingMessage) {

    }

    @Override
    public void onSent(OutgoingMessage outgoingMessage) {

    }
}
