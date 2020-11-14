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
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.impl.enums.EConfigurationProtocol;
import tr.havelsan.ueransim.nas.impl.others.ProtocolConfigurationOptions;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEExtendedProtocolConfigurationOptions extends InformationElement6 {
    public EConfigurationProtocol configurationProtocol;
    public boolean extension;
    public OctetString options;

    public IEExtendedProtocolConfigurationOptions() {
    }

    @Override
    protected IEExtendedProtocolConfigurationOptions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEExtendedProtocolConfigurationOptions();

        var octet = stream.readOctet();
        res.configurationProtocol = EConfigurationProtocol.fromValue(octet.getBitRangeI(0, 2));
        res.extension = octet.getBitB(7);
        res.options = stream.readOctetString(length - 1);

        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(configurationProtocol.intValue() | ((extension ? 1 : 0) << 7));
        stream.writeOctetString(options);
    }

    public ProtocolConfigurationOptions getAsOptions(boolean isUplink) {
        return ProtocolConfigurationOptions.decode(options, isUplink);
    }
}
