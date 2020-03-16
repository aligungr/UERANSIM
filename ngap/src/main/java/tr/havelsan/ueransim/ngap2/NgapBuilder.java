package tr.havelsan.ueransim.ngap2;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import fr.marben.asnsdk.japi.spe.Value;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProcedureCode;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU;
import tr.havelsan.ueransim.ngap.ngap_ies.RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.UserLocationInformation;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.UnsuccessfulOutcome;
import tr.havelsan.ueransim.utils.Pair;
import tr.havelsan.ueransim.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NgapBuilder {
    private NgapPduDescription pduDescription;
    private NgapCriticality procedureCriticality;
    private NgapProcedure procedure;
    private List<Pair<Value, NgapCriticality>> protocolIEs;

    public NgapBuilder() {
        this.protocolIEs = new ArrayList<>();
    }

    public NgapBuilder withDescription(NgapPduDescription pduDescription) {
        this.pduDescription = pduDescription;
        return this;
    }

    public NgapBuilder withProcedure(NgapProcedure procedure, NgapCriticality criticality) {
        this.procedure = procedure;
        this.procedureCriticality = criticality;
        return this;
    }

    public NgapBuilder addProtocolIE(Value value, NgapCriticality criticality) {
        this.protocolIEs.add(new Pair<>(value, criticality));
        return this;
    }

    public NgapBuilder addRanUeNgapId(long value, NgapCriticality criticality) {
        return addProtocolIE(new RAN_UE_NGAP_ID(value), criticality);
    }

    public NgapBuilder addAmfUeNgapId(long value, NgapCriticality criticality) {
        return addProtocolIE(new AMF_UE_NGAP_ID(value), criticality);
    }

    public NgapBuilder addNasPdu(byte[] value, NgapCriticality criticality) {
        return addProtocolIE(new NAS_PDU(value), criticality);
    }

    public NgapBuilder addNasPdu(String hex, NgapCriticality criticality) {
        return addNasPdu(Utils.hexStringToByteArray(hex), criticality);
    }

    public NgapBuilder addNasPdu(NasMessage nasMessage, NgapCriticality criticality) {
        return addNasPdu(NasEncoder.nasPdu(nasMessage), criticality);
    }

    public NgapBuilder addUserLocationInformationNR(UserLocationInformationNr userLocationInformationNr, NgapCriticality criticality) {
        try {
            return addProtocolIE(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, Ngap.createUserLocationInformationNr(userLocationInformationNr)), criticality);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }

    public NGAP_PDU build() {
        int procedureCode = NgapInternal.findProcedureCode(procedure);
        Value procedureContent = NgapInternal.createProcedureValue(procedure);
        for (var protocolIe : protocolIEs) {
            var value = protocolIe.first;
            var criticality = protocolIe.second;
            NgapInternal.appendProtocolIe(procedure, procedureContent, criticality, value);
        }

        try {
            switch (this.pduDescription) {
                case INITIATING_MESSAGE: {
                    var desc = new InitiatingMessage();
                    desc.procedureCode = new ProcedureCode(procedureCode);
                    desc.criticality = new Criticality(procedureCriticality.getAsnValue());
                    desc.value = new OpenTypeValue(procedureContent);
                    return new NGAP_PDU(NGAP_PDU.ASN_initiatingMessage, desc);
                }
                case SUCCESSFUL_OUTCOME: {
                    var desc = new SuccessfulOutcome();
                    desc.procedureCode = new ProcedureCode(procedureCode);
                    desc.criticality = new Criticality(procedureCriticality.getAsnValue());
                    desc.value = new OpenTypeValue(procedureContent);
                    return new NGAP_PDU(NGAP_PDU.ASN_successfulOutcome, desc);
                }
                case UNSUCCESSFUL_OUTCOME: {
                    var desc = new UnsuccessfulOutcome();
                    desc.procedureCode = new ProcedureCode(procedureCode);
                    desc.criticality = new Criticality(procedureCriticality.getAsnValue());
                    desc.value = new OpenTypeValue(procedureContent);
                    return new NGAP_PDU(NGAP_PDU.ASN_unsuccessfulOutcome, desc);
                }
                default:
                    throw new RuntimeException();
            }
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }
}
