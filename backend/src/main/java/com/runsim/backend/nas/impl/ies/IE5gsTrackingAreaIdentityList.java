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

    public IE5gsTrackingAreaIdentityList() {
    }

    public IE5gsTrackingAreaIdentityList(List<VPartialTrackingAreaIdentityList> partialTrackingAreaIdentityLists) {
        this.partialTrackingAreaIdentityLists = partialTrackingAreaIdentityLists;
    }

    @Override
    protected IE5gsTrackingAreaIdentityList decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsTrackingAreaIdentityList();
        res.partialTrackingAreaIdentityLists = Utils.decodeList(stream, octetInputStream -> new VPartialTrackingAreaIdentityList().decode(stream), length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        partialTrackingAreaIdentityLists.forEach(item -> item.encode(stream));
    }

    public static class VPartialTrackingAreaIdentityList extends NasValue {

        public VPartialTrackingAreaIdentityList() {
        }

        @Override
        public VPartialTrackingAreaIdentityList decode(OctetInputStream stream) {
            var octet = stream.readOctet();
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
        public List<Octet3> tacs;

        public VPartialTrackingAreaIdentityList00() {
        }

        public VPartialTrackingAreaIdentityList00(VPlmn mccMnc, List<Octet3> tacs) {
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
        public List<VTrackingAreaIdentity> tais;

        public VPartialTrackingAreaIdentityList10() {
        }

        public VPartialTrackingAreaIdentityList10(List<VTrackingAreaIdentity> tais) {
            this.tais = tais;
        }

        @Override
        public VPartialTrackingAreaIdentityList10 decode(OctetInputStream stream) {
            var octet = stream.readOctet();
            int count = octet.getBitRangeI(0, 4) + 1;
            if (count > 16) count = 16;

            var res = new VPartialTrackingAreaIdentityList10();
            res.tais = new ArrayList<>();
            for (int i = 0; i < count; i++)
                res.tais.add(new VTrackingAreaIdentity().decode(stream));
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
