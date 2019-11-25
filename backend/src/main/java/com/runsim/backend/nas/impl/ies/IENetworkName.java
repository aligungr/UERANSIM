package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit3;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.OctetString;

public class IENetworkName extends InformationElement4 {
    public Bit3 numOfSpareBits;
    public EAddCountryInitials addCi;
    public ECodingScheme codingScheme;
    public OctetString textString;

    @Override
    protected IENetworkName decodeIE4(OctetInputStream stream, int length) {
        int flags = stream.readOctetI();

        var res = new IENetworkName();
        res.numOfSpareBits = new Bit3(flags);
        res.addCi = EAddCountryInitials.fromValue(flags >> 3);
        res.codingScheme = ECodingScheme.fromValue(flags >> 4);
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
