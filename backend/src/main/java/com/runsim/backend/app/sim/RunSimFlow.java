package com.runsim.backend.app.sim;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.Ngap;
import com.runsim.backend.app.Json;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.ngap.Values;
import com.runsim.backend.ngap.ngap_commondatatypes.Criticality;
import com.runsim.backend.ngap.ngap_commondatatypes.ProcedureCode;
import com.runsim.backend.ngap.ngap_commondatatypes.ProtocolIE_ID;
import com.runsim.backend.ngap.ngap_ies.*;
import com.runsim.backend.ngap.ngap_pdu_contents.DownlinkNASTransport;
import com.runsim.backend.ngap.ngap_pdu_contents.InitialUEMessage;
import com.runsim.backend.ngap.ngap_pdu_contents.UplinkNASTransport;
import com.runsim.backend.ngap.ngap_pdu_descriptions.InitiatingMessage;
import com.runsim.backend.ngap.ngap_pdu_descriptions.NGAP_PDU;
import com.runsim.backend.utils.Color;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Utils;
import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;

import java.util.ArrayList;
import java.util.Arrays;

public class RunSimFlow extends BaseFlow {
    private final SimulationFlow simulationFlow;
    private int stepIndex;

    public RunSimFlow(SimulationFlow simulationFlow) {
        this.simulationFlow = simulationFlow;
        this.stepIndex = 0;
    }

    @Override
    public State main(Message message) {
        return performStep(message);
    }

    private synchronized State performStep(Message message) {
        Console.printDiv();

        if (stepIndex < simulationFlow.stepCount) {
            Console.println(Color.YELLOW, "[INFO] Step %d/%d is handling.", stepIndex + 1, simulationFlow.stepCount);
        }

        var receivedNas = receivedNasMessage(message);
        if (receivedNas != null) {
            logReceivedMessage(message.getAsPDU(), receivedNas);

            String registeredName = TypeRegistry.getClassName(receivedNas.getClass());
            if (registeredName != null) {
                if (Arrays.asList(simulationFlow.failOnReceive).contains(registeredName)) {
                    Console.println(Color.RED, "[ERROR] Test case failed.");
                    return closeConnection();
                } else if (Arrays.asList(simulationFlow.passOnReceive).contains(registeredName)) {
                    Console.println(Color.GREEN, "[SUCCESS] Test case passed.");
                    return closeConnection();
                }
            }
        }

        if (stepIndex >= simulationFlow.stepCount) {
            Console.println(Color.PURPLE, "[WARNING] Next step called after all steps are done");
            return this::performStep;
        }

        var step = simulationFlow.steps[stepIndex];
        stepIndex++;

        var pdu = makeNgapPdu(step);

        if (step.sleep > 0) {
            Console.print(Color.CYAN, "[INFO] Sleep has started... ");
            int sleep = step.sleep;
            while (sleep > 0) {
                Console.print(Color.CYAN, "(%d)", (sleep / 1000));
                long current = System.currentTimeMillis();
                Utils.sleep(Math.min(sleep, 1000));
                long delta = System.currentTimeMillis() - current;
                sleep -= delta;
            }
            Console.println();
        }

        logSendingMessage(pdu, step.nasMessage);

        Console.println(Color.BLUE, "[INFO] Message is sending...");
        sendPDU(pdu);
        Console.println(Color.BLUE, "[INFO] Message sent.");

        Console.println(Color.YELLOW, "[INFO] Step completed");

        return this::performStep;
    }

    private void logSendingMessage(NGAP_PDU ngap, NasMessage nas) {
        Console.print(Color.PURPLE_BRIGHT, "[INFO] NAS message will be sent: ");
        Console.println(Color.PURPLE_BRIGHT, TypeRegistry.getClassName(nas.getClass()));
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nas));
        if (simulationFlow.config.logNgapPdu) {
            Console.println(Color.WHITE, "[INFO] NGAP PDU is the following: ");
            Console.println(Color.WHITE, Utils.xmlToJson(Ngap.xerEncode(ngap)));
        }
    }

    private void logReceivedMessage(NGAP_PDU ngap, NasMessage nas) {
        Console.print(Color.CYAN_BRIGHT, "[INFO] NAS message received with type: ");
        Console.println(Color.CYAN_BRIGHT, nas.getClass().getSimpleName());
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nas));
        if (simulationFlow.config.logNgapPdu) {
            Console.println(Color.WHITE, "[INFO] NGAP PDU is the following: ");
            Console.println(Color.WHITE, Utils.xmlToJson(Ngap.xerEncode(ngap)));
        }
    }

    private NasMessage receivedNasMessage(Message message) {
        if (message == null)
            return null;
        var value = message.getAsPDU().getValue();
        if (!(value instanceof InitiatingMessage))
            return null;
        value = ((InitiatingMessage) value).value.getDecodedValue();
        if (!(value instanceof DownlinkNASTransport))
            return null;

        NAS_PDU nasPdu = null;

        var protocolIEs = ((DownlinkNASTransport) value).protocolIEs.valueList;
        for (var protocolIE : protocolIEs) {
            if (protocolIE instanceof DownlinkNASTransport.ProtocolIEs.SEQUENCE) {
                value = ((DownlinkNASTransport.ProtocolIEs.SEQUENCE) protocolIE).value.getDecodedValue();
                if (value instanceof NAS_PDU) {
                    nasPdu = (NAS_PDU) value;
                }
            }
        }

        if (nasPdu == null)
            return null;
        return NasDecoder.nasPdu(nasPdu.getValue());
    }

    private NGAP_PDU makeNgapPdu(SimulationStep step) {
        if (step.ngapType.equals(SimulationStep.ENgapType.INITIAL_UE_MESSAGE)) {
            return makeNgapPduInitialUeMessage(step.nasMessage);
        } else if (step.ngapType.equals(SimulationStep.ENgapType.UPLINK_NAS_TRANSPORT)) {
            return makeNgapPduUplinkNasTransport(step.nasMessage);
        } else {
            throw new RuntimeException("ngapType not implemented: " + step.ngapType.name());
        }
    }

    private NGAP_PDU makeNgapPduInitialUeMessage(NasMessage nasMessage) {
        var ranUeNgapId = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        ranUeNgapId.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
        ranUeNgapId.criticality = new Criticality(Criticality.ASN_reject);
        ranUeNgapId.value = new OpenTypeValue(new RAN_UE_NGAP_ID(simulationFlow.config.ranUeNgapId));

        var nasPdu = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        nasPdu.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
        nasPdu.criticality = new Criticality(Criticality.ASN_reject);
        nasPdu.value = new OpenTypeValue(new NAS_PDU(NasEncoder.nasPdu(nasMessage)));

        var userLocationInformation = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        userLocationInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        userLocationInformation.criticality = new Criticality(Criticality.ASN_reject);
        try {
            userLocationInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, makeUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }

        var establishmentCause = new InitialUEMessage.ProtocolIEs.SEQUENCE();
        establishmentCause.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RRCEstablishmentCause);
        establishmentCause.criticality = new Criticality(Criticality.ASN_ignore);
        establishmentCause.value = new OpenTypeValue(new RRCEstablishmentCause(simulationFlow.config.rrcEstablishmentCause.intValue()));

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

    private NGAP_PDU makeNgapPduUplinkNasTransport(NasMessage nasMessage) {
        var protocolIEs = new ArrayList<UplinkNASTransport.ProtocolIEs.SEQUENCE>();

        var uplink = new UplinkNASTransport();
        uplink.protocolIEs = new UplinkNASTransport.ProtocolIEs();
        uplink.protocolIEs.valueList = protocolIEs;

        var amfUe = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        amfUe.id = new ProtocolIE_ID(Values.NGAP_Constants__id_AMF_UE_NGAP_ID);
        amfUe.criticality = new Criticality(Criticality.ASN_reject);
        amfUe.value = new OpenTypeValue(new AMF_UE_NGAP_ID(simulationFlow.config.amfUeNgapId));
        protocolIEs.add(amfUe);

        var ranUe = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        ranUe.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
        ranUe.criticality = new Criticality(Criticality.ASN_reject);
        ranUe.value = new OpenTypeValue(new RAN_UE_NGAP_ID(simulationFlow.config.ranUeNgapId));
        protocolIEs.add(ranUe);

        var nasPayload = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        nasPayload.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
        nasPayload.criticality = new Criticality(Criticality.ASN_reject);
        nasPayload.value = new OpenTypeValue(new NAS_PDU(NasEncoder.nasPdu(nasMessage)));
        protocolIEs.add(nasPayload);

        var userLocationInformation = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        userLocationInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        userLocationInformation.criticality = new Criticality(Criticality.ASN_reject);
        try {
            userLocationInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, makeUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        protocolIEs.add(userLocationInformation);

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

    private UserLocationInformationNR makeUserLocationInformationNr() {
        var config = simulationFlow.config.userLocationInformation;

        var userLocationInformationNr = new UserLocationInformationNR();
        userLocationInformationNr.nR_CGI = new NR_CGI();
        userLocationInformationNr.nR_CGI.pLMNIdentity = Ngap.plmnEncode(config.nrCgi.plmn);
        userLocationInformationNr.nR_CGI.nRCellIdentity = new NRCellIdentity(config.nrCgi.nrCellIdentity.toByteArray(), 36);
        userLocationInformationNr.tAI = new TAI();
        userLocationInformationNr.tAI.tAC = new TAC(config.tai.tac.toByteArray());
        userLocationInformationNr.tAI.pLMNIdentity = Ngap.plmnEncode(config.tai.plmn);
        userLocationInformationNr.timeStamp = new TimeStamp(config.timeStamp.toByteArray());
        return userLocationInformationNr;
    }
}
