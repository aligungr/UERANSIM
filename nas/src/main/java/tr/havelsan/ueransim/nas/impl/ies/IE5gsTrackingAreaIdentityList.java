/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.exceptions.DecodingException;
import tr.havelsan.ueransim.utils.exceptions.EncodingException;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet3;

import java.util.Arrays;

public class IE5gsTrackingAreaIdentityList extends InformationElement4 {
    public VPartialTrackingAreaIdentityList[] partialTrackingAreaIdentityLists;

    public IE5gsTrackingAreaIdentityList() {
    }

    public IE5gsTrackingAreaIdentityList(VPartialTrackingAreaIdentityList[] partialTrackingAreaIdentityLists) {
        this.partialTrackingAreaIdentityLists = partialTrackingAreaIdentityLists;
    }

    @Override
    protected IE5gsTrackingAreaIdentityList decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsTrackingAreaIdentityList();
        res.partialTrackingAreaIdentityLists = Utils.decodeList(stream, octetInputStream -> new VPartialTrackingAreaIdentityList().decode(stream), length, VPartialTrackingAreaIdentityList.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(partialTrackingAreaIdentityLists).forEach(item -> item.encode(stream));
    }

    public static class VPartialTrackingAreaIdentityList extends NasValue {

        public VPartialTrackingAreaIdentityList() {
        }

        @Override
        public VPartialTrackingAreaIdentityList decode(OctetInputStream stream) {
            var octet = stream.peekOctet();
            int typeOfList = octet.getBitRangeI(5, 6);

            switch (typeOfList) {
                case 0b00:
                    return new VPartialTrackingAreaIdentityList00().decode(stream);
                case 0b01:
                    return new VPartialTrackingAreaIdentityList01().decode(stream);
                case 0b10:
                    return new VPartialTrackingAreaIdentityList10().decode(stream);
                default:
                    throw new DecodingException("invalid type of list for partical tracking area identity list");
            }
        }

        @Override
        public void encode(OctetOutputStream stream) {

        }
    }

    public static class VPartialTrackingAreaIdentityList00 extends VPartialTrackingAreaIdentityList {
        public VPlmn mccMnc;
        public Octet3[] tacs;

        public VPartialTrackingAreaIdentityList00() {
        }

        public VPartialTrackingAreaIdentityList00(VPlmn mccMnc, Octet3[] tacs) {
            this.mccMnc = mccMnc;
            this.tacs = tacs;
        }

        @Override
        public VPartialTrackingAreaIdentityList00 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialTrackingAreaIdentityList00();
            res.mccMnc = new VPlmn().decode(stream);
            res.tacs = new Octet3[count];
            for (int i = 0; i < count; i++) {
                res.tacs[i] = stream.readOctet3();
            }
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tacs.length == 0)
                throw new EncodingException("tacs cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tacs.length - 1);
            flags = flags.setBitRange(5, 6, 0b00);
            stream.writeOctet(flags);
            mccMnc.encode(stream);
            for (var tac : tacs) {
                stream.writeOctet3(tac);
            }
        }
    }

    public static class VPartialTrackingAreaIdentityList01 extends VPartialTrackingAreaIdentityList {
        public VPlmn mccMnc;
        public Octet3 tac;

        public VPartialTrackingAreaIdentityList01() {
        }

        public VPartialTrackingAreaIdentityList01(VPlmn mccMnc, Octet3 tac) {
            this.mccMnc = mccMnc;
            this.tac = tac;
        }

        @Override
        public VPartialTrackingAreaIdentityList01 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialTrackingAreaIdentityList01();
            res.mccMnc = new VPlmn().decode(stream);
            res.tac = stream.readOctet3();
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            var flags = new Octet();
            flags = flags.setBitRange(0, 4, 0);
            flags = flags.setBitRange(5, 6, 0b01);
            stream.writeOctet(flags);
            mccMnc.encode(stream);
            stream.writeOctet3(tac);
        }
    }

    public static class VPartialTrackingAreaIdentityList10 extends VPartialTrackingAreaIdentityList {
        public VTrackingAreaIdentity[] tais;

        public VPartialTrackingAreaIdentityList10() {
        }

        public VPartialTrackingAreaIdentityList10(VTrackingAreaIdentity[] tais) {
            this.tais = tais;
        }

        @Override
        public VPartialTrackingAreaIdentityList10 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialTrackingAreaIdentityList10();
            res.tais = new VTrackingAreaIdentity[count];
            for (int i = 0; i < count; i++)
                res.tais[i] = new VTrackingAreaIdentity().decode(stream);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tais.length == 0)
                throw new EncodingException("tais cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tais.length - 1);
            flags = flags.setBitRange(5, 6, 0b10);
            stream.writeOctet(flags);
            for (var tai : tais) {
                tai.encode(stream);
            }
        }
    }
}
