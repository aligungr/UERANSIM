package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NasSecurityContext {
    public boolean isNew;

    public ETypeOfSecurityContext type;
    public Bit3 ngKsi;

    public OctetString keyNasEnc;
    public OctetString keyNasInt;

    public Count downlinkCount;
    public Count uplinkCount;

    public EConnectionIdentifier connectionIdentifier;

    public Algorithms selectedAlgorithms;

    public NasSecurityContext() {
        this.isNew = false;
        this.type = ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT;
        this.ngKsi = new Bit3(0);
        this.keyNasEnc = new OctetString("");
        this.keyNasInt = new OctetString("");
        this.downlinkCount = new Count();
        this.uplinkCount = new Count();
        this.connectionIdentifier = EConnectionIdentifier.THREE_3GPP_ACCESS;
        this.selectedAlgorithms = new Algorithms();
    }

    public final Count getCount(boolean isUplink) {
        return isUplink ? uplinkCount : downlinkCount;
    }

    public static class Count {
        public Octet2 overflow;
        public Octet sqn;

        public Count() {
            this.overflow = new Octet2();
            this.sqn = new Octet();
        }
    }

    public static class Algorithms {
        public ETypeOfIntegrityProtectionAlgorithm integrity;
        public ETypeOfCipheringAlgorithm ciphering;

        public Algorithms() {
            integrity = ETypeOfIntegrityProtectionAlgorithm.IA0;
            ciphering = ETypeOfCipheringAlgorithm.EA0;
        }
    }

    public static class EConnectionIdentifier extends ProtocolEnum {
        public static final EConnectionIdentifier THREE_3GPP_ACCESS = new EConnectionIdentifier(0x01, "3GPP Access");
        public static final EConnectionIdentifier NON_THREE_3GPP_ACCESS = new EConnectionIdentifier(0x02, "non-3GPP access");

        private EConnectionIdentifier(int value, String name) {
            super(value, name);
        }

        public static EConnectionIdentifier fromValue(int value) {
            return fromValueGeneric(EConnectionIdentifier.class, value, null);
        }
    }
}
