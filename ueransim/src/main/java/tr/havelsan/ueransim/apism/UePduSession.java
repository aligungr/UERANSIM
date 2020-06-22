package tr.havelsan.ueransim.apism;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.configs.PduSessionEstablishmentConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.nas.impl.enums.EProcedureTransactionIdentity;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentRequest;
import tr.havelsan.ueransim.nas.impl.messages.UlNasTransport;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UePduSession {

    public static void sendEstablishmentRequest(SimulationContext ctx, PduSessionEstablishmentConfig config) {
        var pduSessionEstablishmentRequest = new PduSessionEstablishmentRequest();
        pduSessionEstablishmentRequest.pduSessionId = EPduSessionIdentity.fromValue(config.pduSessionId.intValue());
        pduSessionEstablishmentRequest.pti = EProcedureTransactionIdentity.fromValue(config.procedureTransactionId.intValue());
        pduSessionEstablishmentRequest.integrityProtectionMaximumDataRate =
                new IEIntegrityProtectionMaximumDataRate(
                        IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.FULL_DATA_RATE,
                        IEIntegrityProtectionMaximumDataRate.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.FULL_DATA_RATE);
        pduSessionEstablishmentRequest.pduSessionType = new IEPduSessionType(EPduSessionType.IPV4);
        pduSessionEstablishmentRequest.sscMode = new IESscMode(IESscMode.ESscMode.SSC_MODE_1);

        var ulNasTransport = new UlNasTransport();
        ulNasTransport.payloadContainerType = new IEPayloadContainerType(IEPayloadContainerType.EPayloadContainerType.N1_SM_INFORMATION);
        ulNasTransport.payloadContainer = new IEPayloadContainer(new OctetString(NasEncoder.nasPdu(pduSessionEstablishmentRequest)));
        ulNasTransport.pduSessionId = new IEPduSessionIdentity2(config.pduSessionId);
        ulNasTransport.requestType = new IERequestType(IERequestType.ERequestType.INITIAL_REQUEST);
        ulNasTransport.sNssa = config.sNssai;
        ulNasTransport.dnn = config.dnn;

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), ulNasTransport));
    }
}
