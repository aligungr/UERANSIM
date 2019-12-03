package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.Bit8;

import java.util.List;

public class IEEmergencyNumberList extends InformationElement4 {
    public List<VEmergencyNumberInformation> list;

    public IEEmergencyNumberList() {
    }

    public IEEmergencyNumberList(List<VEmergencyNumberInformation> list) {
        this.list = list;
    }

    @Override
    protected IEEmergencyNumberList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEEmergencyNumberList();
        res.list = Utils.decodeList(stream, new VEmergencyNumberInformation()::decode, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        list.forEach(item -> item.encode(stream));
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
