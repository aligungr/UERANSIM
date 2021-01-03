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
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IENetworkName extends InformationElement4 {
    public Bit3 numOfSpareBits;
    public EAddCountryInitials addCi;
    public ECodingScheme codingScheme;
    public OctetString textString;

    public IENetworkName() {
    }

    public IENetworkName(Bit3 numOfSpareBits, EAddCountryInitials addCi, ECodingScheme codingScheme, OctetString textString) {
        this.numOfSpareBits = numOfSpareBits;
        this.addCi = addCi;
        this.codingScheme = codingScheme;
        this.textString = textString;
    }

    @Override
    protected IENetworkName decodeIE4(OctetInputStream stream, int length) {
        int flags = stream.readOctetI();

        var res = new IENetworkName();
        res.numOfSpareBits = new Bit3(flags);
        res.addCi = EAddCountryInitials.fromValue(flags >> 3 & 0b1);
        res.codingScheme = ECodingScheme.fromValue(flags >> 4 & 0b111);
        res.textString = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var flags = new Octet();
        flags = flags.setBitRange(0, 2, numOfSpareBits.intValue());
        flags = flags.setBit(3, addCi.intValue());
        flags = flags.setBitRange(4, 6, codingScheme.intValue());
        stream.writeOctet(flags);
        stream.writeOctetString(textString);
    }

    public static class EAddCountryInitials extends ProtocolEnum {
        public static final EAddCountryInitials SHOULD_NOT_ADD
                = new EAddCountryInitials(0b0, "The MS should not add the letters for the Country's Initials to the text string");
        public static final EAddCountryInitials SHOULD_ADD
                = new EAddCountryInitials(0b1, "The MS should add the letters for the Country's Initials and a separator (e.g. a space) to the text string");

        private EAddCountryInitials(int value, String name) {
            super(value, name);
        }

        public static EAddCountryInitials fromValue(int value) {
            return fromValueGeneric(EAddCountryInitials.class, value, null);
        }
    }

    public static class ECodingScheme extends ProtocolEnum {
        public static final ECodingScheme DEFAULT
                = new ECodingScheme(0b000, "Cell Broadcast data coding scheme, GSM default alphabet, language unspecified, defined in 3GPP TS 23.038");
        public static final ECodingScheme UCS2
                = new ECodingScheme(0b001, "UCS2 (16 bit)");

        private ECodingScheme(int value, String name) {
            super(value, name);
        }

        public static ECodingScheme fromValue(int value) {
            return fromValueGeneric(ECodingScheme.class, value, null);
        }
    }
}
