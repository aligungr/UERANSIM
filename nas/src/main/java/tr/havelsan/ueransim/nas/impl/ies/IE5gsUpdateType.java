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
import tr.havelsan.ueransim.utils.octets.Octet;

public class IE5gsUpdateType extends InformationElement4 {
    public ESmsRequested smsRequested;
    public ENgRanRadioCapabilityUpdate ngRanRcu;

    public IE5gsUpdateType() {
    }

    public IE5gsUpdateType(ESmsRequested smsRequested, ENgRanRadioCapabilityUpdate ngRanRcu) {
        this.smsRequested = smsRequested;
        this.ngRanRcu = ngRanRcu;
    }

    @Override
    protected IE5gsUpdateType decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsUpdateType();
        res.smsRequested = ESmsRequested.fromValue(stream.peekOctetI() & 0b1);
        res.ngRanRcu = ENgRanRadioCapabilityUpdate.fromValue(stream.readOctetI() >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBit(0, smsRequested.intValue());
        octet = octet.setBit(1, ngRanRcu.intValue());
        stream.writeOctet(octet);
    }

    public static class ENgRanRadioCapabilityUpdate extends ProtocolEnum {
        public static final ENgRanRadioCapabilityUpdate NOT_NEEDED
                = new ENgRanRadioCapabilityUpdate(0b0, "NG-RAN radio capability update not needed");
        public static final ENgRanRadioCapabilityUpdate NEEDED
                = new ENgRanRadioCapabilityUpdate(0b1, "NG-RAN radio capability update needed");

        private ENgRanRadioCapabilityUpdate(int value, String name) {
            super(value, name);
        }

        public static ENgRanRadioCapabilityUpdate fromValue(int value) {
            return fromValueGeneric(ENgRanRadioCapabilityUpdate.class, value, null);
        }
    }

    public static class ESmsRequested extends ProtocolEnum {
        public static final ESmsRequested NOT_SUPPORTED
                = new ESmsRequested(0b0, "SMS over NAS not supported");
        public static final ESmsRequested SUPPORTED
                = new ESmsRequested(0b1, "SMS over NAS supported");

        private ESmsRequested(int value, String name) {
            super(value, name);
        }

        public static ESmsRequested fromValue(int value) {
            return fromValueGeneric(ESmsRequested.class, value, null);
        }
    }
}
