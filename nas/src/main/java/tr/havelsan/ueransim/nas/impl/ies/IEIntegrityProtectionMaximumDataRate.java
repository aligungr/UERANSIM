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
import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEIntegrityProtectionMaximumDataRate extends InformationElement3 {
    // couldn't have been a longer name I guess
    public EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink;
    public EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink;

    public IEIntegrityProtectionMaximumDataRate() {
    }

    public IEIntegrityProtectionMaximumDataRate(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink, EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink) {
        this.maxRateUplink = maxRateUplink;
        this.maxRateDownlink = maxRateDownlink;
    }

    @Override
    protected IEIntegrityProtectionMaximumDataRate decodeIE3(OctetInputStream stream) {
        var res = new IEIntegrityProtectionMaximumDataRate();
        res.maxRateUplink = EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.fromValue(stream.readOctetI());
        res.maxRateDownlink = EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(maxRateUplink.intValue());
        stream.writeOctet(maxRateDownlink.intValue());
    }

    public static class EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink extends ProtocolEnum {
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink SIXTY_FOR_KBPS
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(0b00000000, "64 kbps");
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink FULL_DATA_RATE
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(0b11111111, "Full data rate");

        private EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink(int value, String name) {
            super(value, name);
        }

        public static EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink fromValue(int value) {
            return fromValueGeneric(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.class, value, null);
        }
    }

    public static class EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink extends ProtocolEnum {
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink SIXTY_FOR_KBPS
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(0b00000000, "64 kbps");
        public static final EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink FULL_DATA_RATE
                = new EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(0b11111111, "Full data rate");

        private EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink(int value, String name) {
            super(value, name);
        }

        public static EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink fromValue(int value) {
            return fromValueGeneric(EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.class, value, null);
        }
    }
}
