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

public class PduSessionEstablishmentRequest extends PlainSmMessage {
    public IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate;
    public IEPduSessionType pduSessionType;
    public IESscMode sscMode;
    public IE5gSmCapability smCapability;
    public IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters;
    public IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested;
    public IESmPduDnRequestContainer smPduDnRequestContainer;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionEstablishmentRequest() {
        super(EMessageType.PDU_SESSION_ESTABLISHMENT_REQUEST);
    }

    public PduSessionEstablishmentRequest(IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate, IEPduSessionType pduSessionType, IESscMode sscMode, IE5gSmCapability smCapability, IEMaximumNumberOfSupportedPacketFilters maximumNumberOfSupportedPacketFilters, IEAlwaysOnPduSessionRequested alwaysOnPduSessionRequested, IESmPduDnRequestContainer smPduDnRequestContainer, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.integrityProtectionMaximumDataRate = integrityProtectionMaximumDataRate;
        this.pduSessionType = pduSessionType;
        this.sscMode = sscMode;
        this.smCapability = smCapability;
        this.maximumNumberOfSupportedPacketFilters = maximumNumberOfSupportedPacketFilters;
        this.alwaysOnPduSessionRequested = alwaysOnPduSessionRequested;
        this.smPduDnRequestContainer = smPduDnRequestContainer;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("integrityProtectionMaximumDataRate");
        builder.optionalIE1(0x9, "pduSessionType");
        builder.optionalIE1(0xA, "sscMode");
        builder.optionalIE(0x28, "smCapability");
        builder.optionalIE(0x55, "maximumNumberOfSupportedPacketFilters");
        builder.optionalIE1(0xB, "alwaysOnPduSessionRequested");
        builder.optionalIE(0x39, "smPduDnRequestContainer");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
