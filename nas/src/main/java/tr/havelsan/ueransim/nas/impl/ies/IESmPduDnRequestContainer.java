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

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

import java.nio.charset.StandardCharsets;

public class IESmPduDnRequestContainer extends InformationElement4 {
    public String dnSpecificIdentity;

    public IESmPduDnRequestContainer() {
    }

    public IESmPduDnRequestContainer(String dnSpecificIdentity) {
        this.dnSpecificIdentity = dnSpecificIdentity;
    }

    @Override
    protected IESmPduDnRequestContainer decodeIE4(OctetInputStream stream, int length) {
        var bytes = stream.readOctetArrayB(length);
        var utf8 = new String(bytes, StandardCharsets.UTF_8);

        var res = new IESmPduDnRequestContainer();
        res.dnSpecificIdentity = utf8;
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var bytes = dnSpecificIdentity.getBytes(StandardCharsets.UTF_8);
        stream.writeOctets(bytes);
    }
}
