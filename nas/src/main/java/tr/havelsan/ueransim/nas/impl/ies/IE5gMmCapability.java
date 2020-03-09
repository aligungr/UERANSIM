package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IE5gMmCapability extends InformationElement4 {
    public EEpcNasSupported s1Mode;
    public EHandoverAttachSupported hoAttach;
    public ELtePositioningProtocolCapability lpp;

    public IE5gMmCapability() {
    }

    public IE5gMmCapability(EEpcNasSupported s1Mode, EHandoverAttachSupported hoAttach, ELtePositioningProtocolCapability lpp) {
        this.s1Mode = s1Mode;
        this.hoAttach = hoAttach;
        this.lpp = lpp;
    }

    @Override
    protected IE5gMmCapability decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gMmCapability();
        var octet = stream.readOctet();
        s1Mode = EEpcNasSupported.fromValue(octet.getBitI(0));
        hoAttach = EHandoverAttachSupported.fromValue(octet.getBitI(1));
        lpp = ELtePositioningProtocolCapability.fromValue(octet.getBitI(2));
        // other octets are spare (if any)
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = 0;
        octet |= lpp.intValue();
        octet <<= 1;
        octet |= hoAttach.intValue();
        octet <<= 1;
        octet |= s1Mode.intValue();
        stream.writeOctet(octet);
    }

    public static class EEpcNasSupported extends ProtocolEnum {
        public static final EEpcNasSupported NOT_SUPPORTED
                = new EEpcNasSupported(0b0, "S1 mode not supported");
        public static final EEpcNasSupported SUPPORTED
                = new EEpcNasSupported(0b1, "S1 mode supported");

        private EEpcNasSupported(int value, String name) {
            super(value, name);
        }

        public static EEpcNasSupported fromValue(int value) {
            return fromValueGeneric(EEpcNasSupported.class, value, null);
        }
    }

    public static class ELtePositioningProtocolCapability extends ProtocolEnum {
        public static final ELtePositioningProtocolCapability NOT_SUPPORTED
                = new ELtePositioningProtocolCapability(0b0, "LPP in N1 mode not supported");
        public static final ELtePositioningProtocolCapability SUPPORTED
                = new ELtePositioningProtocolCapability(0b1, "LPP in N1 mode supported");

        private ELtePositioningProtocolCapability(int value, String name) {
            super(value, name);
        }

        public static ELtePositioningProtocolCapability fromValue(int value) {
            return fromValueGeneric(ELtePositioningProtocolCapability.class, value, null);
        }
    }

    public static class EHandoverAttachSupported extends ProtocolEnum {
        public static final EHandoverAttachSupported NOT_SUPPORTED
                = new EHandoverAttachSupported(0b0, "not supported");
        public static final EHandoverAttachSupported SUPPORTED
                = new EHandoverAttachSupported(0b1, "supported");

        private EHandoverAttachSupported(int value, String name) {
            super(value, name);
        }

        public static EHandoverAttachSupported fromValue(int value) {
            return fromValueGeneric(EHandoverAttachSupported.class, value, null);
        }
    }
}
