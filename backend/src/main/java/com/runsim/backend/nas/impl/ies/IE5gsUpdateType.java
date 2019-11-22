package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class IE5gsUpdateType extends InformationElement4 {
    public ESmsRequested smsRequested;
    public ENgRanRadioCapabilityUpdate ngRanRcu;

    @Override
    protected IE5gsUpdateType decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsUpdateType();
        res.smsRequested = ESmsRequested.fromValue(stream.peekOctetI());
        res.ngRanRcu = ENgRanRadioCapabilityUpdate.fromValue(stream.readOctetI() >> 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBit(0, smsRequested.intValue());
        octet = octet.setBit(1, ngRanRcu.intValue());
        stream.writeOctet(octet.intValue());
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
            return fromValueGeneric(ENgRanRadioCapabilityUpdate.class, value);
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
            return fromValueGeneric(ESmsRequested.class, value);
        }
    }
}
