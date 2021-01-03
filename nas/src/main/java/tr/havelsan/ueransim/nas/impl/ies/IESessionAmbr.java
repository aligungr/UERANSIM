/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class IESessionAmbr extends InformationElement4 {
    public EUnitForSessionAmbr unitForSessionAmbrForDownlink;
    public Octet2 sessionAmbrForDownlink;
    public EUnitForSessionAmbr unitForSessionAmbrForUplink;
    public Octet2 sessionAmbrForUplink;

    public IESessionAmbr() {
    }

    public IESessionAmbr(EUnitForSessionAmbr unitForSessionAmbrForDownlink, Octet2 sessionAmbrForDownlink, EUnitForSessionAmbr unitForSessionAmbrForUplink, Octet2 sessionAmbrForUplink) {
        this.unitForSessionAmbrForDownlink = unitForSessionAmbrForDownlink;
        this.sessionAmbrForDownlink = sessionAmbrForDownlink;
        this.unitForSessionAmbrForUplink = unitForSessionAmbrForUplink;
        this.sessionAmbrForUplink = sessionAmbrForUplink;
    }

    @Override
    protected IESessionAmbr decodeIE4(OctetInputStream stream, int length) {
        var res = new IESessionAmbr();
        res.unitForSessionAmbrForDownlink = EUnitForSessionAmbr.fromValue(stream.readOctetI());
        res.sessionAmbrForDownlink = stream.readOctet2();
        res.unitForSessionAmbrForUplink = EUnitForSessionAmbr.fromValue(stream.readOctetI());
        res.sessionAmbrForUplink = stream.readOctet2();
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(unitForSessionAmbrForDownlink.intValue());
        stream.writeOctet2(sessionAmbrForDownlink);
        stream.writeOctet(unitForSessionAmbrForUplink.intValue());
        stream.writeOctet(sessionAmbrForUplink.intValue());
    }

    public static class EUnitForSessionAmbr extends ProtocolEnum {
        public static final EUnitForSessionAmbr VALUE_NOT_USED =
                new EUnitForSessionAmbr(0b00000000, "value is not used");
        public static final EUnitForSessionAmbr MULT_1Kbps
                = new EUnitForSessionAmbr(0b00000001, "value is incremented in multiples of 1 Kbps");
        public static final EUnitForSessionAmbr MULT_4Kbps
                = new EUnitForSessionAmbr(0b00000010, "value is incremented in multiples of 4 Kbps");
        public static final EUnitForSessionAmbr MULT_16Kbps
                = new EUnitForSessionAmbr(0b00000011, "value is incremented in multiples of 16 Kbps");
        public static final EUnitForSessionAmbr MULT_64Kbps
                = new EUnitForSessionAmbr(0b00000100, "value is incremented in multiples of 64 Kbps");
        public static final EUnitForSessionAmbr MULT_256kbps
                = new EUnitForSessionAmbr(0b00000101, "value is incremented in multiples of 256 kbps");
        public static final EUnitForSessionAmbr MULT_1Mbps
                = new EUnitForSessionAmbr(0b00000110, "value is incremented in multiples of 1 Mbps");
        public static final EUnitForSessionAmbr MULT_4Mbps
                = new EUnitForSessionAmbr(0b00000111, "value is incremented in multiples of 4 Mbps");
        public static final EUnitForSessionAmbr MULT_16Mbps
                = new EUnitForSessionAmbr(0b00001000, "value is incremented in multiples of 16 Mbps");
        public static final EUnitForSessionAmbr MULT_64Mbps
                = new EUnitForSessionAmbr(0b00001001, "value is incremented in multiples of 64 Mbps");
        public static final EUnitForSessionAmbr MULT_256Mbps
                = new EUnitForSessionAmbr(0b00001010, "value is incremented in multiples of 256 Mbps");
        public static final EUnitForSessionAmbr MULT_1Gbps
                = new EUnitForSessionAmbr(0b00001011, "value is incremented in multiples of 1 Gbps");
        public static final EUnitForSessionAmbr MULT_4Gbps
                = new EUnitForSessionAmbr(0b00001100, "value is incremented in multiples of 4 Gbps");
        public static final EUnitForSessionAmbr MULT_16Gbps
                = new EUnitForSessionAmbr(0b00001101, "value is incremented in multiples of 16 Gbps");
        public static final EUnitForSessionAmbr MULT_64Gbps
                = new EUnitForSessionAmbr(0b00001110, "value is incremented in multiples of 64 Gbps");
        public static final EUnitForSessionAmbr MULT_256Gbps
                = new EUnitForSessionAmbr(0b00001111, "value is incremented in multiples of 256 Gbps");
        public static final EUnitForSessionAmbr MULT_1Tbps
                = new EUnitForSessionAmbr(0b00010000, "value is incremented in multiples of 1 Tbps");
        public static final EUnitForSessionAmbr MULT_4Tbps
                = new EUnitForSessionAmbr(0b00010001, "value is incremented in multiples of 4 Tbps");
        public static final EUnitForSessionAmbr MULT_16Tbps
                = new EUnitForSessionAmbr(0b00010010, "value is incremented in multiples of 16 Tbps");
        public static final EUnitForSessionAmbr MULT_64Tbps
                = new EUnitForSessionAmbr(0b00010011, "value is incremented in multiples of 64 Tbps");
        public static final EUnitForSessionAmbr MULT_256Tbps
                = new EUnitForSessionAmbr(0b00010100, "value is incremented in multiples of 256 Tbps");
        public static final EUnitForSessionAmbr MULT_1Pbps
                = new EUnitForSessionAmbr(0b00010101, "value is incremented in multiples of 1 Pbps");
        public static final EUnitForSessionAmbr MULT_4Pbps
                = new EUnitForSessionAmbr(0b00010110, "value is incremented in multiples of 4 Pbps");
        public static final EUnitForSessionAmbr MULT_16Pbps
                = new EUnitForSessionAmbr(0b00010111, "value is incremented in multiples of 16 Pbps");
        public static final EUnitForSessionAmbr MULT_64Pbps
                = new EUnitForSessionAmbr(0b00011000, "value is incremented in multiples of 64 Pbps");
        public static final EUnitForSessionAmbr MULT_256Pbps
                = new EUnitForSessionAmbr(0b00011001, "value is incremented in multiples of 256 Pbps");

        private EUnitForSessionAmbr(int value, String name) {
            super(value, name);
        }

        public static EUnitForSessionAmbr fromValue(int value) {
            return fromValueGeneric(EUnitForSessionAmbr.class, value, MULT_256Pbps);
        }
    }
}
