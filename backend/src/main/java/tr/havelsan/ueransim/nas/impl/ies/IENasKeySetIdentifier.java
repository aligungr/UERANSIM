package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IENasKeySetIdentifier extends InformationElement1 {
    /*
     * 'no key is available' for UE to network
     * 'reserved' for network to UE
     */
    public static final Bit3 NOT_AVAILABLE_OR_RESERVED = new Bit3(0b111);

    public ETypeOfSecurityContext tsc;
    public Bit3 nasKeySetIdentifier;

    public IENasKeySetIdentifier() {
    }

    public IENasKeySetIdentifier(ETypeOfSecurityContext tsc, Bit3 nasKeySetIdentifier) {
        this.tsc = tsc;
        this.nasKeySetIdentifier = nasKeySetIdentifier;
    }

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        int val = value.intValue();

        var res = new IENasKeySetIdentifier();
        res.tsc = ETypeOfSecurityContext.fromValue(val >> 3 & 0b1);
        res.nasKeySetIdentifier = new Bit3(val & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return tsc.intValue() << 3 | nasKeySetIdentifier.intValue();
    }

    public static class ETypeOfSecurityContext extends ProtocolEnum {
        public static final ETypeOfSecurityContext NATIVE_SECURITY_CONTEXT
                = new ETypeOfSecurityContext(0b0, "Native security context (for KSI_AMF)");
        public static final ETypeOfSecurityContext MAPPED_SECURITY_CONTEXT
                = new ETypeOfSecurityContext(0b1, "Mapped security context (for KSI_ASME)");

        private ETypeOfSecurityContext(int value, String name) {
            super(value, name);
        }

        public static ETypeOfSecurityContext fromValue(int value) {
            return fromValueGeneric(ETypeOfSecurityContext.class, value, null);
        }
    }
}
