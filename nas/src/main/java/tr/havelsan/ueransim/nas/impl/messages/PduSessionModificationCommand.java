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

public class PduSessionModificationCommand extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IESessionAmbr sessionAmbr;
    public IEGprsTimer rqTimerValue;
    public IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication;
    public IEQoSRules authorizedQoSRules;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationCommand() {
        super(EMessageType.PDU_SESSION_MODIFICATION_COMMAND);
    }

    public PduSessionModificationCommand(IE5gSmCause smCause, IESessionAmbr sessionAmbr, IEGprsTimer rqTimerValue, IEAlwaysOnPduSessionIndication alwaysOnPduSessionIndication, IEQoSRules authorizedQoSRules, IEMappedEpsBearerContexts mappedEpsBearerContexts, IEQoSFlowDescriptions authorizedQoSFlowDescriptions, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.sessionAmbr = sessionAmbr;
        this.rqTimerValue = rqTimerValue;
        this.alwaysOnPduSessionIndication = alwaysOnPduSessionIndication;
        this.authorizedQoSRules = authorizedQoSRules;
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
        this.authorizedQoSFlowDescriptions = authorizedQoSFlowDescriptions;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x2A, "sessionAmbr");
        builder.optionalIE(0x56, "rqTimerValue");
        builder.optionalIE1(0x8, "alwaysOnPduSessionIndication");
        builder.optionalIE(0x7A, "authorizedQoSRules");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x79, "authorizedQoSFlowDescriptions");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
