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

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEProtocolConfigurationOptions extends InformationElement4 {
    public EConfigurationProtocol configurationProtocol;
    public OctetString rawData;

    public IEProtocolConfigurationOptions() {
    }

    public IEProtocolConfigurationOptions(EConfigurationProtocol configurationProtocol, OctetString rawData) {
        this.configurationProtocol = configurationProtocol;
        this.rawData = rawData;
    }

    @Override
    protected IEProtocolConfigurationOptions decodeIE4(OctetInputStream stream, int length) {
        var res = new IEProtocolConfigurationOptions();
        res.configurationProtocol = EConfigurationProtocol.fromValue(stream.readOctetI() & 0b111);
        res.rawData = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(configurationProtocol.intValue());
        stream.writeOctetString(rawData);
    }

    public static class EConfigurationProtocol extends ProtocolEnum {
        public static final EConfigurationProtocol PPP
                = new EConfigurationProtocol(0b000, "PPP for use with IP PDP type or IP PDN type");

        private EConfigurationProtocol(int value, String name) {
            super(value, name);
        }

        public static EConfigurationProtocol fromValue(int value) {
            // All values are accepted as PPP for current version of the protocol.
            return PPP;
        }
    }
}
