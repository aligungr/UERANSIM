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

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class PduSessionEstablishmentAccept extends PlainSmMessage {
    public IEPduSessionType selectedPduSessionType;
    public IESscMode selectedSscMode;
    public IEQoSRules authorizedQoSRules;
    public IESessionAmbr sessionAmbr;
    public IE5gSmCause smCause;
    public IEPduAddress pduAddress;
    public IEGprsTimer rqTimerValue;
    public IESNssai sNssai;
    public IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEEapMessage eapMessage;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;
    public IEDnn dnn;

    public PduSessionEstablishmentAccept() {
        super(EMessageType.PDU_SESSION_ESTABLISHMENT_ACCEPT);
    }

    public PduSessionEstablishmentAccept(IEPduSessionType selectedPduSessionType, IESscMode selectedSscMode, IEQoSRules authorizedQoSRules, IESessionAmbr sessionAmbr, IE5gSmCause smCause, IEPduAddress pduAddress, IEGprsTimer rqTimerValue, IESNssai sNssai, IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication, IEMappedEpsBearerContexts mappedEpsBearerContexts, IEEapMessage eapMessage, IEQoSFlowDescriptions authorizedQoSFlowDescriptions, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions, IEDnn dnn) {
        this();
        this.selectedPduSessionType = selectedPduSessionType;
        this.selectedSscMode = selectedSscMode;
        this.authorizedQoSRules = authorizedQoSRules;
        this.sessionAmbr = sessionAmbr;
        this.smCause = smCause;
        this.pduAddress = pduAddress;
        this.rqTimerValue = rqTimerValue;
        this.sNssai = sNssai;
        this.alwaysOnPduSessionIndication = alwaysOnPduSessionIndication;
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
        this.eapMessage = eapMessage;
        this.authorizedQoSFlowDescriptions = authorizedQoSFlowDescriptions;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
        this.dnn = dnn;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("selectedSscMode", "selectedPduSessionType");
        builder.mandatoryIE("authorizedQoSRules");
        builder.mandatoryIE("sessionAmbr");
        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x29, "pduAddress");
        builder.optionalIE(0x56, "rqTimerValue");
        builder.optionalIE(0x22, "sNssai");
        builder.optionalIE1(0x8, "alwaysOnPduSessionIndication");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x79, "authorizedQoSFlowDescriptions");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
        builder.optionalIE(0x25, "dnn");
    }
}
