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

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class V5gTmsi extends NasValue {
    public Octet4 value;

    public V5gTmsi() {
    }

    public V5gTmsi(Octet4 value) {
        this.value = value;
    }

    public V5gTmsi(long value) {
        this(new Octet4(value));
    }

    public V5gTmsi(String hex) {
        this(new Octet4(hex));
    }

    @Override
    public V5gTmsi decode(OctetInputStream stream) {
        Octet octet3 = stream.readOctet();
        Octet octet2 = stream.readOctet();
        Octet octet1 = stream.readOctet();
        Octet octet0 = stream.readOctet();

        var res = new V5gTmsi();
        res.value = new Octet4(octet3, octet2, octet1, octet0);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet4(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
