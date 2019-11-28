package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VPlmn;
import com.runsim.backend.nas.impl.values.VTrackingAreaIdentity;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet3;

import java.util.ArrayList;
import java.util.List;

public class IEServiceAreaList extends InformationElement4 {
    public List<VPartialServiceAreaList> partialServiceAreaLists;

    @Override
    protected IEServiceAreaList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEServiceAreaList();
        res.partialServiceAreaLists = Utils.decodeList(stream, VPartialServiceAreaList::decode, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        partialServiceAreaLists.forEach(item -> item.encode(stream));
    }

    public static abstract class VPartialServiceAreaList extends NasValue {
        public EAllowedType allowedType;

        public static VPartialServiceAreaList decode(OctetInputStream stream) {
            var octet = stream.peekOctet();
            int typeOfList = octet.getBitRangeI(5, 6);

            VPartialServiceAreaList res;

            switch (typeOfList) {
                case 0b00:
                    res = VPartialServiceAreaList00.decode(stream);
                    break;
                case 0b01:
                    res = VPartialServiceAreaList01.decode(stream);
                    break;
                case 0b10:
                    res = VPartialServiceAreaList10.decode(stream);
                    break;
                case 0b11:
                    res = VPartialServiceAreaList11.decode(stream);
                    break;
                default:
                    throw new DecodingException("invalid type of list for service area list");
            }

            res.allowedType = EAllowedType.fromValue(octet.getBitI(7));
            return res;
        }
    }

    public static class VPartialServiceAreaList00 extends VPartialServiceAreaList {
        public VPlmn plmn;
        public List<Octet3> tacs;

        public static VPartialServiceAreaList00 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList00();
            res.plmn = VPlmn.decode(stream);
            res.tacs = new ArrayList<>();
            for (int i = 0; i < count; i++)
                res.tacs.add(stream.readOctet3());
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tacs.size() == 0)
                throw new EncodingException("tacs cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tacs.size() - 1);
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

        public static VPartialServiceAreaList01 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList01();
            res.plmn = VPlmn.decode(stream);
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
        public List<VTrackingAreaIdentity> tais;

        public static VPartialServiceAreaList10 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList10();
            res.tais = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                res.tais.add(VTrackingAreaIdentity.decode(stream));
            }
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tais.size() == 0)
                throw new EncodingException("tais cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tais.size() - 1);
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

        public static VPartialServiceAreaList11 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialServiceAreaList11();
            res.plmn = VPlmn.decode(stream);
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
