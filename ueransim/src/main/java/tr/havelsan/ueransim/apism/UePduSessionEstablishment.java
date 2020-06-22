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

package tr.havelsan.ueransim.apism;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.configs.PduSessionEstablishmentConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentRequest;
import tr.havelsan.ueransim.nas.impl.messages.UlNasTransport;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UePduSessionEstablishment {

    public static void sendEstablishmentRequest(SimulationContext ctx, PduSessionEstablishmentConfig config) {
        var pduSessionId = UePduSessionManagement.allocatePduSessionId(ctx);
        var procedureTransactionId = UePduSessionManagement.allocateProcedureTransactionId(ctx);

        var pduSessionEstablishmentRequest = new PduSessionEstablishmentRequest();
        pduSessionEstablishmentRequest.pduSessionId = pduSessionId;
        pduSessionEstablishmentRequest.pti = procedureTransactionId;
        pduSessionEstablishmentRequest.integrityProtectionMaximumDataRate =
                new IEIntegrityProtectionMaximumDataRate(
                        IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.FULL_DATA_RATE,
                        IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.FULL_DATA_RATE);
        pduSessionEstablishmentRequest.pduSessionType = new IEPduSessionType(EPduSessionType.IPV4);
        pduSessionEstablishmentRequest.sscMode = new IESscMode(IESscMode.ESscMode.SSC_MODE_1);

        var ulNasTransport = new UlNasTransport();
        ulNasTransport.payloadContainerType = new IEPayloadContainerType(IEPayloadContainerType.EPayloadContainerType.N1_SM_INFORMATION);
        ulNasTransport.payloadContainer = new IEPayloadContainer(new OctetString(NasEncoder.nasPdu(pduSessionEstablishmentRequest)));
        ulNasTransport.pduSessionId = new IEPduSessionIdentity2(pduSessionId.intValue());
        ulNasTransport.requestType = new IERequestType(IERequestType.ERequestType.INITIAL_REQUEST);
        ulNasTransport.sNssa = config.sNssai;
        ulNasTransport.dnn = config.dnn;

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), ulNasTransport));
    }
}
