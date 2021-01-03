/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
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

public class IEServiceAreaList extends InformationElement4 {
    public VPartialServiceAreaList[] partialServiceAreaLists;

    public IEServiceAreaList() {
    }

    public IEServiceAreaList(VPartialServiceAreaList[] partialServiceAreaLists) {
        this.partialServiceAreaLists = partialServiceAreaLists;
    }

    @Override
    protected IEServiceAreaList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEServiceAreaList();
        res.partialServiceAreaLists = Utils.decodeList(stream, octetInputStream -> new VPartialServiceAreaList().decode(octetInputStream), length, VPartialServiceAreaList.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(partialServiceAreaLists).forEach(item -> item.encode(stream));
    }

    public static class VPartialServiceAreaList extends NasValue {
        public EAllowedType allowedType;

        public VPartialServiceAreaList() {
        }

        public VPartialServiceAreaList(EAllowedType allowedType) {
            this.allowedType = allowedType;
        }

        @Override
        public VPartialServiceAreaList decode(OctetInputStream stream) {
            var octet = stream.peekOctet();
            int typeOfList = octet.getBitRangeI(5, 6);

            VPartialServiceAreaList res;

            switch (typeOfList) {
                case 0b00:
                    res = new VPartialServiceAreaList00().decode(stream);
                    break;
                case 0b01:
                    res = new VPartialServiceAreaList01().decode(stream);
                    break;
                case 0b10:
                    res = new VPartialServiceAreaList10().decode(stream);
                    break;
                case 0b11:
                    res = new VPartialServiceAreaList11().decode(stream);
                    break;
                default:
                    throw new DecodingException("invalid type of list for service area list");
            }

            res.allowedType = EAllowedType.fromValue(octet.getBitI(7));
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {

        }
    }

    public static class VPartialServiceAreaList00 extends VPartialServiceAreaList {
        public VPlmn plmn;
        public Octet3[] tacs;

        public VPartialServiceAreaList00() {
        }

        public VPartialServiceAreaList00(VPlmn plmn, Octet3[] tacs) {
            this.plmn = plmn;
            this.tacs = tacs;
        }

        @Override
        public VPartialServiceAreaList00 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList00();
            res.plmn = new VPlmn().decode(stream);
            res.tacs = new Octet3[count];
            for (int i = 0; i < count; i++)
                res.tacs[i] = stream.readOctet3();
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tacs.length == 0)
                throw new EncodingException("tacs cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tacs.length - 1);
            flags = flags.setBitRange(5, 6, 0b00);
            flags = flags.setBit(7, allowedType.intValue());
            stream.writeOctet(flags);
            plmn.encode(stream);
            for (var tac : tacs) {
                stream.writeOctet3(tac);
            }
        }
    }

    public static class VPartialServiceAreaList01 extends VPartialServiceAreaList {
        public VPlmn plmn;
        public Octet3 tac;

        public VPartialServiceAreaList01() {
        }

        public VPartialServiceAreaList01(VPlmn plmn, Octet3 tac) {
            this.plmn = plmn;
            this.tac = tac;
        }

        @Override
        public VPartialServiceAreaList01 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList01();
            res.plmn = new VPlmn().decode(stream);
            res.tac = stream.readOctet3();
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            var flags = new Octet(0);
            flags = flags.setBitRange(0, 4, 0);
            flags = flags.setBitRange(5, 6, 0b01);
            flags = flags.setBit(7, allowedType.intValue());
            stream.writeOctet(flags);
            plmn.encode(stream);
            stream.writeOctet3(tac);
        }
    }

    public static class VPartialServiceAreaList10 extends VPartialServiceAreaList {
        public VTrackingAreaIdentity[] tais;

        public VPartialServiceAreaList10() {
        }

        public VPartialServiceAreaList10(VTrackingAreaIdentity[] tais) {
            this.tais = tais;
        }

        @Override
        public VPartialServiceAreaList10 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList10();
            res.tais = new VTrackingAreaIdentity[count];
            for (int i = 0; i < count; i++) {
                res.tais[i] = new VTrackingAreaIdentity().decode(stream);
            }
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tais.length == 0)
                throw new EncodingException("tais cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tais.length - 1);
            flags = flags.setBitRange(5, 6, 0b10);
            flags = flags.setBit(7, allowedType.intValue());
            stream.writeOctet(flags);
            for (var tai : tais) {
                tai.encode(stream);
            }
        }
    }

    public static class VPartialServiceAreaList11 extends VPartialServiceAreaList {
        public VPlmn plmn;

        public VPartialServiceAreaList11() {
        }

        public VPartialServiceAreaList11(VPlmn plmn) {
            this.plmn = plmn;
        }

        @Override
        public VPartialServiceAreaList11 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList11();
            res.plmn = new VPlmn().decode(stream);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            var flags = new Octet();
            flags = flags.setBitRange(0, 4, 0);
            flags = flags.setBitRange(5, 6, 0b01);
            flags = flags.setBit(7, allowedType.intValue());
            stream.writeOctet(flags);
            plmn.encode(stream);
        }
    }

    public static class EAllowedType extends ProtocolEnum {
        public static final EAllowedType IN_THE_ALLOWED_AREA
                = new EAllowedType(0b0, "TAIs in the list are in the allowed area");
        public static final EAllowedType IN_THE_NON_ALLOWED_AREA
                = new EAllowedType(0b1, "TAIs in the list are in the non-allowed area");

        private EAllowedType(int value, String name) {
            super(value, name);
        }

        public static EAllowedType fromValue(int value) {
            return fromValueGeneric(EAllowedType.class, value, null);
        }
    }
}
