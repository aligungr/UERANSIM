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

public class IEUesUsageSetting extends InformationElement4 {
    public EUesUsageSetting uesUsageSetting;

    public IEUesUsageSetting() {
    }

    public IEUesUsageSetting(EUesUsageSetting uesUsageSetting) {
        this.uesUsageSetting = uesUsageSetting;
    }

    @Override
    protected IEUesUsageSetting decodeIE4(OctetInputStream stream, int length) {
        var res = new IEUesUsageSetting();
        res.uesUsageSetting = EUesUsageSetting.fromValue(stream.readOctetI() & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(uesUsageSetting.intValue());
    }

    public static class EUesUsageSetting extends ProtocolEnum {
        public static final EUesUsageSetting VOICE_CENTRIC
                = new EUesUsageSetting(0b0, "voice centric");
        public static final EUesUsageSetting DATA_CENTRIC
                = new EUesUsageSetting(0b1, "data centric");

        private EUesUsageSetting(int value, String name) {
            super(value, name);
        }

        public static EUesUsageSetting fromValue(int value) {
            return fromValueGeneric(EUesUsageSetting.class, value, null);
        }
    }
}
