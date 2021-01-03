/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit;

public class IEPduSessionStatus extends InformationElement4 {
    public final Bit psi00 = new Bit(0); // this bit specified as spare;
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

    public IEPduSessionStatus() {
    }

    public IEPduSessionStatus(Bit psi01, Bit psi02, Bit psi03, Bit psi04, Bit psi05, Bit psi06, Bit psi07, Bit psi08, Bit psi09, Bit psi10, Bit psi11, Bit psi12, Bit psi13, Bit psi14, Bit psi15) {
        this.psi01 = psi01;
        this.psi02 = psi02;
        this.psi03 = psi03;
        this.psi04 = psi04;
        this.psi05 = psi05;
        this.psi06 = psi06;
        this.psi07 = psi07;
        this.psi08 = psi08;
        this.psi09 = psi09;
        this.psi10 = psi10;
        this.psi11 = psi11;
        this.psi12 = psi12;
        this.psi13 = psi13;
        this.psi14 = psi14;
        this.psi15 = psi15;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var octet0 = stream.readOctet();
        var octet1 = stream.readOctet();

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

        // other octets are specified as spare (if any)
        stream.readOctetString(length - 2);

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var bits1 = new Bit[]{psi00, psi01, psi02, psi03, psi04, psi05, psi06, psi07};
        var bits2 = new Bit[]{psi08, psi09, psi10, psi11, psi12, psi13, psi14, psi15};

        int octet1 = 0, octet2 = 0;
        for (int i = 0; i < 8; i++) {
            octet1 |= bits1[8 - i - 1].intValue();
            octet1 <<= 1;

            octet2 |= bits2[8 - i - 1].intValue();
            octet2 <<= 1;
        }

        stream.writeOctet(octet1);
        stream.writeOctet(octet2);
    }
}
