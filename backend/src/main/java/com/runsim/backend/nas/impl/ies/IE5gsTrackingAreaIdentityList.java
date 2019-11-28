package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.nas.core.NasValue;
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

public class IE5gsTrackingAreaIdentityList extends InformationElement4 {
    public List<VPartialTrackingAreaIdentityList> partialTrackingAreaIdentityLists;

    @Override
    protected IE5gsTrackingAreaIdentityList decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsTrackingAreaIdentityList();
        res.partialTrackingAreaIdentityLists = Utils.decodeList(stream, VPartialTrackingAreaIdentityList::decode, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        partialTrackingAreaIdentityLists.forEach(item -> item.encode(stream));
    }

    public static abstract class VPartialTrackingAreaIdentityList extends NasValue {

        public static VPartialTrackingAreaIdentityList decode(OctetInputStream stream) {
            var octet = stream.readOctet();

            int numberOfElements = octet.getBitRangeI(0, 4) + 1; // WARNING: plus 1 is required
            // From spec: All other values are unused and shall be interpreted as 16, if received by the UE.
            if (numberOfElements > 16)
                numberOfElements = 16;

            int typeOfList = octet.getBitRangeI(5, 6);

            switch (typeOfList) {
                case 0b00:
                    return VPartialTrackingAreaIdentityList00.decode(stream, numberOfElements);
                case 0b01:
                    return VPartialTrackingAreaIdentityList01.decode(stream, numberOfElements);
                case 0b10:
                    return VPartialTrackingAreaIdentityList10.decode(stream, numberOfElements);
                default:
                    throw new DecodingException("invalid type of list for partical tracking area identity list");
            }
        }
    }

    public static class VPartialTrackingAreaIdentityList00 extends VPartialTrackingAreaIdentityList {
        public VPlmn mccMnc;
        public List<Octet3> tacs;

        public static VPartialTrackingAreaIdentityList00 decode(OctetInputStream stream, int count) {
            var res = new VPartialTrackingAreaIdentityList00();
            res.mccMnc = VPlmn.decode(stream);
            res.tacs = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                res.tacs.add(stream.readOctet3());
            }
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            if (tacs.size() == 0)
                throw new EncodingException("tacs cannot be empty");

            var flags = new Octet();
            flags = flags.setBitRange(0, 4, tacs.size() - 1);
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

        public static VPartialTrackingAreaIdentityList01 decode(OctetInputStream stream, int count) {
            var res = new VPartialTrackingAreaIdentityList01();
            res.mccMnc = VPlmn.decode(stream);
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
        public List<VTrackingAreaIdentity> tais;

        public static VPartialTrackingAreaIdentityList10 decode(OctetInputStream stream, int count) {
            var res = new VPartialTrackingAreaIdentityList10();
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
            stream.writeOctet(flags);
            for (var tai : tais) {
                tai.encode(stream);
            }
        }
    }
}
