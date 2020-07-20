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

import tr.havelsan.ueransim.core.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap3.NgapDataUnitDescription;
import tr.havelsan.ueransim.ngap4.core.NGAP_Enumerated;
import tr.havelsan.ueransim.ngap4.core.NGAP_Value;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UserLocationInformation;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_ProcedureCode;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap4.pdu.NGAP_InitiatingMessage;
import tr.havelsan.ueransim.ngap4.pdu.NGAP_PDU;
import tr.havelsan.ueransim.ngap4.pdu.NGAP_SuccessfulOutcome;
import tr.havelsan.ueransim.ngap4.pdu.NGAP_UnsuccessfulOutcome;
import tr.havelsan.ueransim.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/*public class NgapBuilder {

    public final NgapMessageType messageType;
    private final List<NGAP_Value> protocolIEs;

    public NgapBuilder(NgapMessageType messageType) {
        this.protocolIEs = new ArrayList<>();
        this.messageType = messageType;
    }

    // WARNING: This method may cause IEs to be mutated.
    // Do not reuse the passed IE, and do not pass reused IE!
    public static NgapBuilder wrapMessage(NGAP_Value value) {
        var nb = new NgapBuilder(NgapMessageType.valueOf(value.getClass().getSimpleName()));
        for (var protocolIe : NgapInternal.extractProtocolIe(value, Value.class)) {
            nb.addProtocolIE(protocolIe);
        }
        return nb;
    }

    public NgapBuilder addProtocolIE(NGAP_Value value) {
        this.protocolIEs.add(value);
        return this;
    }

    public NgapBuilder addRanUeNgapId(long value) {
        return addProtocolIE(new NGAP_RAN_UE_NGAP_ID(value));
    }

    public NgapBuilder addAmfUeNgapId(long value) {
        return addProtocolIE(new NGAP_RAN_UE_NGAP_ID(value));
    }

    public NgapBuilder addNasPdu(byte[] value) {
        return addProtocolIE(new NGAP_NAS_PDU(value));
    }

    public NgapBuilder addNasPdu(String hex) {
        return addNasPdu(Utils.hexStringToByteArray(hex));
    }

    public NgapBuilder addNasPdu(NasMessage nasMessage) {
        return addNasPdu(NasEncoder.nasPdu(nasMessage));
    }

    public NgapBuilder addUserLocationInformationNR(UserLocationInformationNr userLocationInformationNr) {
        var ie = new NGAP_UserLocationInformation();
        ie.userLocationInformationNR = NgapUtils.createUserLocationInformationNr(userLocationInformationNr);
        addProtocolIE(ie);
        return this;
    }

    public NgapBuilder addCause(NGAP_Enumerated cause) {
        var ie = new NGAP_Cause();

        if (cause instanceof NGAP_CauseRadioNetwork) {
            ie.radioNetwork = (NGAP_CauseRadioNetwork) cause;
        } else if (cause instanceof NGAP_CauseTransport) {
            ie.transport = (NGAP_CauseTransport) cause;
        } else if (cause instanceof NGAP_CauseNas) {
            ie.nas = (NGAP_CauseNas) cause;
        } else if (cause instanceof NGAP_CauseProtocol) {
            ie.protocol = (NGAP_CauseProtocol) cause;
        } else if (cause instanceof NGAP_CauseMisc) {
            ie.misc = (NGAP_CauseMisc) cause;
        } else {
            throw new ReservedOrInvalidValueException("enumerated value is not a cause");
        }

        return addProtocolIE(ie);
    }

    public NGAP_PDU build() {
        NGAP_Value messageValue = NgapInternal.createMessageValue(messageType);

        NgapInternal.sortProtocolIEs(messageType, protocolIEs);
        for (var protocolIe : protocolIEs) {
            var ieType = NgapInternal.findIeAsnType(protocolIe);
            var criticality = NgapData.findIeCriticality(messageType, ieType);
            var id = NgapData.findIeId(messageType, ieType);

            NgapInternal.appendProtocolIe(messageType, messageValue, criticality, protocolIe, id);
        }

        int procedureCode = NgapData.findProcedureCode(messageType);
        var pduType = NgapData.findMessageDescription(messageType);
        var messageCriticality = NgapData.findMessageCriticality(messageType);

        var pdu = new NGAP_PDU();

        if (pduType == NgapDataUnitDescription.INITIATING_MESSAGE) {
            pdu.initiatingMessage = new NGAP_InitiatingMessage();
            pdu.initiatingMessage.procedureCode = new NGAP_ProcedureCode(procedureCode);
            pdu.initiatingMessage.criticality = messageCriticality;
            pdu.initiatingMessage.value = new NGAP_OpenTypeValue(messageValue);
        } else if (pduType == NgapDataUnitDescription.SUCCESSFUL_OUTCOME) {
            pdu.successfulOutcome = new NGAP_SuccessfulOutcome();
            pdu.successfulOutcome.procedureCode = new NGAP_ProcedureCode(procedureCode);
            pdu.successfulOutcome.criticality = messageCriticality;
            pdu.successfulOutcome.value = new OpenTypeValue(messageValue);
        } else if (pduType == NgapDataUnitDescription.UNSUCCESSFUL_OUTCOME) {
            pdu.unsuccessfulOutcome = new NGAP_UnsuccessfulOutcome();
            pdu.unsuccessfulOutcome.procedureCode = new NGAP_ProcedureCode(procedureCode);
            pdu.unsuccessfulOutcome.criticality = messageCriticality;
            pdu.unsuccessfulOutcome.value = new OpenTypeValue(messageValue);
        }

        return pdu;
    }
}
*/