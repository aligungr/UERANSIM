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

    public final NgapMessageType messageType;
    private final List<Value> protocolIEs;

    public NgapBuilder(NgapMessageType messageType) {
        this.protocolIEs = new ArrayList<>();
        this.messageType = messageType;
    }

    // WARNING: This method may cause IEs to be mutated.
    // Do not reuse the passed IE, and do not pass reused IE!
    public static NgapBuilder wrapMessage(Value value) {
        var nb = new NgapBuilder(NgapMessageType.valueOf(value.getClass().getSimpleName()));
        for (var protocolIe : NgapInternal.extractProtocolIe(value, Value.class)) {
            nb.addProtocolIE(protocolIe);
        }
        return nb;
    }

    public NgapBuilder addProtocolIE(Value value) {
        this.protocolIEs.add(value);
        return this;
    }

    public NgapBuilder addRanUeNgapId(long value) {
        return addProtocolIE(new RAN_UE_NGAP_ID(value));
    }

    public NgapBuilder addAmfUeNgapId(long value) {
        return addProtocolIE(new AMF_UE_NGAP_ID(value));
    }

    public NgapBuilder addNasPdu(byte[] value) {
        return addProtocolIE(new NAS_PDU(value));
    }

    public NgapBuilder addNasPdu(String hex) {
        return addNasPdu(Utils.hexStringToByteArray(hex));
    }

    public NgapBuilder addNasPdu(NasMessage nasMessage) {
        return addNasPdu(NasEncoder.nasPdu(nasMessage));
    }

    public NgapBuilder addUserLocationInformationNR(UserLocationInformationNr userLocationInformationNr) {
        try {
            return addProtocolIE(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, Ngap.createUserLocationInformationNr(userLocationInformationNr)));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }

    public NgapBuilder addCause(NgapCause cause) {
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

        return addProtocolIE(c);
    }

    public NGAP_PDU build() {
        Value messageValue = NgapInternal.createMessageValue(messageType);

        NgapInternal.sortProtocolIEs(messageType, protocolIEs);
        for (var protocolIe : protocolIEs) {
            var ieType = NgapInternal.findIeAsnType(protocolIe);
            var criticality = NgapData.findIeCriticality(messageType, ieType);
            var id = NgapData.findIeId(messageType, ieType);

            NgapInternal.appendProtocolIe(messageType, messageValue, criticality, protocolIe, id);
        }

        int procedureCode = NgapData.findProcedureCode(messageType);
        var pduType = NgapData.findPduType(messageType);
        var messageCriticality = NgapData.findMessageCriticality(messageType);

        try {
            switch (pduType) {
                case INITIATING_MESSAGE: {
                    var desc = new InitiatingMessage();
                    desc.procedureCode = new ProcedureCode(procedureCode);
                    desc.criticality = new Criticality(messageCriticality.getAsnValue());
                    desc.value = new OpenTypeValue(messageValue);
                    return new NGAP_PDU(NGAP_PDU.ASN_initiatingMessage, desc);
                }
                case SUCCESSFUL_OUTCOME: {
                    var desc = new SuccessfulOutcome();
                    desc.procedureCode = new ProcedureCode(procedureCode);
                    desc.criticality = new Criticality(messageCriticality.getAsnValue());
                    desc.value = new OpenTypeValue(messageValue);
                    return new NGAP_PDU(NGAP_PDU.ASN_successfulOutcome, desc);
                }
                case UNSUCCESSFUL_OUTCOME: {
                    var desc = new UnsuccessfulOutcome();
                    desc.procedureCode = new ProcedureCode(procedureCode);
                    desc.criticality = new Criticality(messageCriticality.getAsnValue());
                    desc.value = new OpenTypeValue(messageValue);
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
