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

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit8;

import java.util.Arrays;

public class IEEmergencyNumberList extends InformationElement4 {
    public VEmergencyNumberInformation[] list;

    public IEEmergencyNumberList() {
    }

    public IEEmergencyNumberList(VEmergencyNumberInformation[] list) {
        this.list = list;
    }

    @Override
    protected IEEmergencyNumberList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEEmergencyNumberList();
        res.list = Utils.decodeList(stream, new VEmergencyNumberInformation()::decode, length, VEmergencyNumberInformation.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(list).forEach(item -> item.encode(stream));
    }

    public static class VEmergencyNumberInformation extends NasValue {
        public VEmergencyServiceCategory emergencyServiceCategory;
        public String number;

        @Override
        public VEmergencyNumberInformation decode(OctetInputStream stream) {
            var res = new VEmergencyNumberInformation();
            int length = stream.readOctetI() - 1;
            res.emergencyServiceCategory = new VEmergencyServiceCategory().decode(stream);
            res.number = NasDecoder.bcdString(stream, length, false);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            emergencyServiceCategory.encode(stream);
            NasEncoder.bcdString(stream, number, -1, false, null);
        }
    }

    public static class VEmergencyServiceCategory extends NasValue {
        public Bit police;
        public Bit ambulance;
        public Bit fireBrigade;
        public Bit marineGuard;
        public Bit mountainRescue;
        public Bit manuallyInitiatedECall;
        public Bit automaticallyInitiatedECall;

        public VEmergencyServiceCategory() {
        }

        public VEmergencyServiceCategory(Bit police, Bit ambulance, Bit fireBrigade, Bit marineGuard, Bit mountainRescue, Bit manuallyInitiatedECall, Bit automaticallyInitiatedECall) {
            this.police = police;
            this.ambulance = ambulance;
            this.fireBrigade = fireBrigade;
            this.marineGuard = marineGuard;
            this.mountainRescue = mountainRescue;
            this.manuallyInitiatedECall = manuallyInitiatedECall;
            this.automaticallyInitiatedECall = automaticallyInitiatedECall;
        }

        @Override
        public VEmergencyServiceCategory decode(OctetInputStream stream) {
            var octet = stream.readOctet();

            var res = new VEmergencyServiceCategory();
            res.police = octet.getBit(0);
            res.ambulance = octet.getBit(1);
            res.fireBrigade = octet.getBit(2);
            res.marineGuard = octet.getBit(3);
            res.mountainRescue = octet.getBit(4);
            res.manuallyInitiatedECall = octet.getBit(5);
            res.automaticallyInitiatedECall = octet.getBit(6);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            var bits = new Bit8(police, ambulance, fireBrigade, marineGuard,
                    mountainRescue, manuallyInitiatedECall, automaticallyInitiatedECall, new Bit(0));
            stream.writeOctet(bits.intValue());
        }
    }
}
