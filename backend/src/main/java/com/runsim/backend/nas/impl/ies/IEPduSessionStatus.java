package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit;

public class IEPduSessionStatus extends InformationElement4 {
    private final Bit psi00 = new Bit(0); // this bit specified as spare;
    public Bit psi01;
    public Bit psi02;
    public Bit psi03;
    public Bit psi04;
    public Bit psi05;
    public Bit psi06;
    public Bit psi07;
    public Bit psi08;
    public Bit psi09;
    public Bit psi10;
    public Bit psi11;
    public Bit psi12;
    public Bit psi13;
    public Bit psi14;
    public Bit psi15;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var octet0 = stream.readOctet();
        var octet1 = stream.readOctet();
        // (other octets are specified as spare (if any))

        var res = new IEPduSessionStatus();

        res.psi01 = octet0.getBit(1);
        res.psi02 = octet0.getBit(2);
        res.psi03 = octet0.getBit(3);
        res.psi04 = octet0.getBit(4);
        res.psi05 = octet0.getBit(5);
        res.psi06 = octet0.getBit(6);
        res.psi07 = octet0.getBit(7);

        res.psi08 = octet1.getBit(0);
        res.psi09 = octet1.getBit(1);
        res.psi10 = octet1.getBit(2);
        res.psi11 = octet1.getBit(3);
        res.psi12 = octet1.getBit(4);
        res.psi13 = octet1.getBit(5);
        res.psi14 = octet1.getBit(6);
        res.psi15 = octet1.getBit(7);

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
