package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.BitN;

public class VEmergencyServiceCategory extends NasValue {
    public Bit police;
    public Bit ambulance;
    public Bit fireBrigade;
    public Bit marineGuard;
    public Bit mountainRescue;
    public Bit manuallyInitiatedECall;
    public Bit automaticallyInitiatedECall;
    public final Bit spare = new Bit(0);

    public static VEmergencyServiceCategory decode(OctetInputStream stream) {
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
        var bits = new BitN(police, ambulance, fireBrigade, marineGuard,
                mountainRescue, manuallyInitiatedECall, automaticallyInitiatedECall, spare);
        stream.writeOctet(bits.intValue());
    }
}
