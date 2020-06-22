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
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class SecurityModeCommand extends PlainMmMessage {
    public IENasSecurityAlgorithms selectedNasSecurityAlgorithms;
    public IENasKeySetIdentifier ngKsi;
    public IEUeSecurityCapability replayedUeSecurityCapabilities;
    public IEImeiSvRequest imeiSvRequest;
    public IEEpsNasSecurityAlgorithms epsNasSecurityAlgorithms;
    public IEAdditional5gSecurityInformation additional5gSecurityInformation;
    public IEEapMessage eapMessage;
    public IEAbba abba;
    public IES1UeNetworkCapability replayedS1UeNetworkCapability;

    public Octet4 _macForNewSC;

    public SecurityModeCommand() {
        super(EMessageType.SECURITY_MODE_COMMAND);
    }

    public SecurityModeCommand(IENasSecurityAlgorithms selectedNasSecurityAlgorithms, IENasKeySetIdentifier ngKsi, IEUeSecurityCapability replayedUeSecurityCapabilities, IEImeiSvRequest imeiSvRequest, IEEpsNasSecurityAlgorithms epsNasSecurityAlgorithms, IEAdditional5gSecurityInformation additional5gSecurityInformation, IEEapMessage eapMessage, IEAbba abba, IES1UeNetworkCapability replayedS1UeNetworkCapability) {
        this();
        this.selectedNasSecurityAlgorithms = selectedNasSecurityAlgorithms;
        this.ngKsi = ngKsi;
        this.replayedUeSecurityCapabilities = replayedUeSecurityCapabilities;
        this.imeiSvRequest = imeiSvRequest;
        this.epsNasSecurityAlgorithms = epsNasSecurityAlgorithms;
        this.additional5gSecurityInformation = additional5gSecurityInformation;
        this.eapMessage = eapMessage;
        this.abba = abba;
        this.replayedS1UeNetworkCapability = replayedS1UeNetworkCapability;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("selectedNasSecurityAlgorithms");
        builder.mandatoryIE1("ngKsi");
        builder.mandatoryIE("replayedUeSecurityCapabilities");
        builder.optionalIE1(0xE, "imeiSvRequest");
        builder.optionalIE(0x57, "epsNasSecurityAlgorithms");
        builder.optionalIE(0x36, "additional5gSecurityInformation");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x38, "abba");
        builder.optionalIE(0x19, "replayedS1UeNetworkCapability");
    }
}
