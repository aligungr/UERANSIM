package com.runsim.backend.ue;

import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.ngap.Values;
import com.runsim.backend.ngap.ngap_commondatatypes.Criticality;
import com.runsim.backend.ngap.ngap_commondatatypes.ProcedureCode;
import com.runsim.backend.ngap.ngap_commondatatypes.ProtocolIE_ID;
import com.runsim.backend.ngap.ngap_ies.*;
import com.runsim.backend.ngap.ngap_pdu_contents.InitialUEMessage;
import com.runsim.backend.ngap.ngap_pdu_contents.UplinkNASTransport;
import com.runsim.backend.ngap.ngap_pdu_descriptions.InitiatingMessage;
import com.runsim.backend.ngap.ngap_pdu_descriptions.NGAP_PDU;
import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;

import java.util.ArrayList;

public class UeUtils {

    private static UserLocationInformationNR createUserLocationInformationNr() {
        var userLocationInformationNr = new UserLocationInformationNR();
        userLocationInformationNr.nR_CGI = new NR_CGI();
        userLocationInformationNr.nR_CGI.pLMNIdentity = new PLMNIdentity(new byte[]{0x00, 0x01, 0x10});
        userLocationInformationNr.nR_CGI.nRCellIdentity = new NRCellIdentity(new byte[]{0x00, 0x00, 0x01, 0x10, 0x00}, 36);
        userLocationInformationNr.tAI = new TAI();
        userLocationInformationNr.tAI.tAC = new TAC(new byte[]{0x00, 0x00, 0x75});
        userLocationInformationNr.tAI.pLMNIdentity = new PLMNIdentity(new byte[]{0x00, 0x01, 0x10});
        return userLocationInformationNr;
    }

    public static NGAP_PDU createInitialUeMessage(NasMessage nasMessage, int ranUeNgapIdValue, int establishmentCauseValue) {
        var ranUeNgapId = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        ranUeNgapId.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
        ranUeNgapId.criticality = new Criticality(Criticality.ASN_reject);
        ranUeNgapId.value = new OpenTypeValue(new RAN_UE_NGAP_ID(ranUeNgapIdValue));

        var nasPdu = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        nasPdu.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
        nasPdu.criticality = new Criticality(Criticality.ASN_reject);
        nasPdu.value = new OpenTypeValue(new NAS_PDU(NasEncoder.nasPdu(nasMessage)));

        var userLocationInformation = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        userLocationInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        userLocationInformation.criticality = new Criticality(Criticality.ASN_reject);
        try {
            userLocationInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, createUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }

        var establishmentCause = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        establishmentCause.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RRCEstablishmentCause);
        establishmentCause.criticality = new Criticality(Criticality.ASN_ignore);
        establishmentCause.value = new OpenTypeValue(new RRCEstablishmentCause(establishmentCauseValue));

        var protocolIEs = new ArrayList<InitialUEMessage.ProtocolIEs.SEQUENCE>();
        protocolIEs.add(ranUeNgapId);
        protocolIEs.add(nasPdu);
        protocolIEs.add(userLocationInformation);
        protocolIEs.add(establishmentCause);

        var initialUEMessage = new InitialUEMessage();
        initialUEMessage.protocolIEs = new InitialUEMessage.ProtocolIEs(protocolIEs);

        var initiatingMessage = new InitiatingMessage();
        initiatingMessage.procedureCode = new ProcedureCode(Values.NGAP_Constants__id_InitialUEMessage);
        initiatingMessage.criticality = new Criticality(Criticality.ASN_ignore);
        initiatingMessage.value = new OpenTypeValue(initialUEMessage);

        NGAP_PDU ngapPdu;
        try {
            ngapPdu = new NGAP_PDU(NGAP_PDU.ASN_initiatingMessage, initiatingMessage);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }

        return ngapPdu;
    }

    private static NGAP_PDU createUplinkMessage(NasMessage nasMessage, int amfUeNgapId, int ranUeNgapId) {
        var list = new ArrayList<UplinkNASTransport.ProtocolIEs.SEQUENCE>();

        var uplink = new UplinkNASTransport();
        uplink.protocolIEs = new UplinkNASTransport.ProtocolIEs();
        uplink.protocolIEs.valueList = list;

        var amfUe = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        amfUe.id = new ProtocolIE_ID(Values.NGAP_Constants__id_AMF_UE_NGAP_ID);
        amfUe.criticality = new Criticality(Criticality.ASN_reject);
        amfUe.value = new OpenTypeValue(new AMF_UE_NGAP_ID(amfUeNgapId));
        list.add(amfUe);

        var ranUe = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        ranUe.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
        ranUe.criticality = new Criticality(Criticality.ASN_reject);
        ranUe.value = new OpenTypeValue(new RAN_UE_NGAP_ID(ranUeNgapId));
        list.add(ranUe);

        var nasPayload = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        nasPayload.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
        nasPayload.criticality = new Criticality(Criticality.ASN_reject);
        nasPayload.value = new OpenTypeValue(new NAS_PDU(NasEncoder.nasPdu(nasMessage)));
        list.add(nasPayload);

        var userLocationInformation = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        userLocationInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        userLocationInformation.criticality = new Criticality(Criticality.ASN_reject);
        try {
            userLocationInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, createUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        list.add(userLocationInformation);

        var initiatingMessage = new InitiatingMessage();
        initiatingMessage.procedureCode = new ProcedureCode(Values.NGAP_Constants__id_UplinkNASTransport);
        initiatingMessage.criticality = new Criticality(Criticality.ASN_ignore);
        initiatingMessage.value = new OpenTypeValue(uplink);

        try {
            return new NGAP_PDU(NGAP_PDU.ASN_initiatingMessage, initiatingMessage);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }
}
