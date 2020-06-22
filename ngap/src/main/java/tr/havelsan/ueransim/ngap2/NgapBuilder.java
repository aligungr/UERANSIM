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

package tr.havelsan.ueransim.ngap2;

import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import fr.marben.asnsdk.japi.spe.Value;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.core.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProcedureCode;
import tr.havelsan.ueransim.ngap.ngap_ies.*;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.UnsuccessfulOutcome;
import tr.havelsan.ueransim.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NgapBuilder {

    private NgapCriticality procedureCriticality;
    private NgapProcedure procedure;
    private List<ProtocolIE> protocolIEs;

    public NgapBuilder(NgapProcedure procedure, NgapCriticality criticality) {
        this.protocolIEs = new ArrayList<>();
        this.procedure = procedure;
        this.procedureCriticality = criticality;
    }

    public NgapBuilder addProtocolIE(Value value, NgapCriticality criticality, Integer ieId) {
        this.protocolIEs.add(new ProtocolIE(value, criticality, ieId));
        return this;
    }

    public NgapBuilder addProtocolIE(Value value, NgapCriticality criticality) {
        return addProtocolIE(value, criticality, null);
    }

    public NgapBuilder addProtocolIE(Value value) {
        return addProtocolIE(value, NgapCriticality.REJECT, null);
    }

    public NgapBuilder addRanUeNgapId(long value, NgapCriticality criticality) {
        return addProtocolIE(new RAN_UE_NGAP_ID(value), criticality, Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
    }

    public NgapBuilder addAmfUeNgapId(long value, NgapCriticality criticality) {
        return addProtocolIE(new AMF_UE_NGAP_ID(value), criticality, Values.NGAP_Constants__id_AMF_UE_NGAP_ID);
    }

    public NgapBuilder addNasPdu(byte[] value, NgapCriticality criticality) {
        return addProtocolIE(new NAS_PDU(value), criticality, Values.NGAP_Constants__id_NAS_PDU);
    }

    public NgapBuilder addNasPdu(String hex, NgapCriticality criticality) {
        return addNasPdu(Utils.hexStringToByteArray(hex), criticality);
    }

    public NgapBuilder addNasPdu(NasMessage nasMessage, NgapCriticality criticality) {
        return addNasPdu(NasEncoder.nasPdu(nasMessage), criticality);
    }

    public NgapBuilder addUserLocationInformationNR(UserLocationInformationNr userLocationInformationNr, NgapCriticality criticality) {
        try {
            return addProtocolIE(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, Ngap.createUserLocationInformationNr(userLocationInformationNr)), criticality, Values.NGAP_Constants__id_UserLocationInformation);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }

    public NgapBuilder addCause(NgapCause cause, NgapCriticality criticality) {
        int fieldNum = cause.getFieldNumber();
        Value value;

        switch (fieldNum) {
            case Cause.ASN_radioNetwork:
                value = new CauseRadioNetwork(cause.getAsnValue());
                break;
            case Cause.ASN_transport:
                value = new CauseTransport(cause.getAsnValue());
                break;
            case Cause.ASN_nas:
                value = new CauseNas(cause.getAsnValue());
                break;
            case Cause.ASN_protocol:
                value = new CauseProtocol(cause.getAsnValue());
                break;
            case Cause.ASN_misc:
                value = new CauseMisc(cause.getAsnValue());
                break;
            default:
                throw new ReservedOrInvalidValueException("cause field number");
        }

        Cause c;
        try {
            c = new Cause(cause.getFieldNumber(), value);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }

        return addProtocolIE(c, criticality, Values.NGAP_Constants__id_Cause);
    }

    public NgapBuilder addCause(NgapCause cause) {
        return addCause(cause, NgapCriticality.IGNORE);
    }

    public NGAP_PDU build() {
        int procedureCode = NgapInternal.findProcedureCode(procedure);
        Value procedureContent = NgapInternal.createProcedureValue(procedure);

        ProtocolIeOrdering.processProtocolIEs(protocolIEs, procedureContent);
        for (var protocolIe : protocolIEs) {
            NgapInternal.appendProtocolIe(procedure, procedureContent, protocolIe.criticality, protocolIe.value, protocolIe.id);
        }

        var pduDescription = procedure.pduDescription();

        try {
            switch (pduDescription) {
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

    static class ProtocolIE {
        final Value value;
        final NgapCriticality criticality;
        final Integer id;

        public ProtocolIE(Value value, NgapCriticality criticality, Integer id) {
            this.value = value;
            this.criticality = criticality;
            this.id = id;
        }
    }
}
