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

public class PduSessionModificationRequest extends PlainSmMessage {
    public IE5gSmCapability smCapability;
    public IE5gSmCause smCause;
    public IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters;
    public IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested;
    public IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate;
    public IEQoSRules requestedQosRules;
    public IEQoSFlowDescriptions requestedQosFlowDescriptions;
    public IEMappedEpsBearerContexts mappedEpsBearerContexts;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationRequest() {
        super(EMessageType.PDU_SESSION_MODIFICATION_REQUEST);
    }

    public PduSessionModificationRequest(IE5gSmCapability smCapability, IE5gSmCause smCause, IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters, IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested, IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate, IEQoSRules requestedQosRules, IEQoSFlowDescriptions requestedQosFlowDescriptions, IEMappedEpsBearerContexts mappedEpsBearerContexts, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCapability = smCapability;
        this.smCause = smCause;
        this.maximumNumberOfSupportedPacketFilters = maximumNumberOfSupportedPacketFilters;
        this.alwaysOnPduSessionRequested = alwaysOnPduSessionRequested;
        this.integrityProtectionMaximumDataRate = integrityProtectionMaximumDataRate;
        this.requestedQosRules = requestedQosRules;
        this.requestedQosFlowDescriptions = requestedQosFlowDescriptions;
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x28, "smCapability");
        builder.optionalIE(0x59, "smCause");
        builder.optionalIE(0x55, "maximumNumberOfSupportedPacketFilters");
        builder.optionalIE1(0xB, "alwaysOnPduSessionRequested");
        builder.optionalIE(0x13, "integrityProtectionMaximumDataRate");
        builder.optionalIE(0x7A, "requestedQosRules");
        builder.optionalIE(0x79, "requestedQosFlowDescriptions");
        builder.optionalIE(0x7F, "mappedEpsBearerContexts");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
