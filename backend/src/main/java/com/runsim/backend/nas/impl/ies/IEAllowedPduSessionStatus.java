package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.Bit8;

public class IEAllowedPduSessionStatus extends InformationElement4 {
    public final Bit psi00 = new Bit(0);
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
    protected IEAllowedPduSessionStatus decodeIE4(OctetInputStream stream, int length) {
        var octet1 = stream.readOctet();
        var octet2 = stream.readOctet();

        var res = new IEAllowedPduSessionStatus();
        res.psi01 = new Bit(octet1.getBitI(1));
        res.psi02 = new Bit(octet1.getBitI(2));
        res.psi03 = new Bit(octet1.getBitI(3));
        res.psi04 = new Bit(octet1.getBitI(4));
        res.psi05 = new Bit(octet1.getBitI(5));
        res.psi06 = new Bit(octet1.getBitI(6));
        res.psi07 = new Bit(octet1.getBitI(7));
        res.psi08 = new Bit(octet2.getBitI(0));
        res.psi09 = new Bit(octet2.getBitI(1));
        res.psi10 = new Bit(octet2.getBitI(2));
        res.psi11 = new Bit(octet2.getBitI(3));
        res.psi12 = new Bit(octet2.getBitI(4));
        res.psi13 = new Bit(octet2.getBitI(5));
        res.psi14 = new Bit(octet2.getBitI(6));
        res.psi15 = new Bit(octet2.getBitI(7));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet1 = new Bit8(psi00, psi01, psi02, psi03, psi04, psi05, psi06, psi07);
        var octet2 = new Bit8(psi08, psi09, psi10, psi11, psi12, psi13, psi14, psi15);

        stream.writeOctet(octet1);
        stream.writeOctet(octet2);
    }
}
