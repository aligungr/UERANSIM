package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class NasSecurityContext {
    public IENasKeySetIdentifier ngKsi;
    public boolean isNew;

    public Count downlinkCount;
    public Count uplinkCount;

    public EConnectionIdentifier connectionIdentifier;

    public UeKeys keys;
    public SelectedAlgorithms selectedAlgorithms;

    public NasSecurityContext() {
        this.isNew = true;
        this.ngKsi = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, IENasKeySetIdentifier.NOT_AVAILABLE_OR_RESERVED);
        this.downlinkCount = new Count();
        this.uplinkCount = new Count();
        this.connectionIdentifier = EConnectionIdentifier.THREE_3GPP_ACCESS;
        this.selectedAlgorithms = new SelectedAlgorithms();
        this.keys = new UeKeys();
    }

    public final Count getCount(boolean isUplink) {
        return isUplink ? uplinkCount : downlinkCount;
    }

    public void countOnDecrypt(Octet sequenceNumber, boolean isUplink) {
        if (isUplink) {
            if (uplinkCount.sqn.longValue() > sequenceNumber.longValue()) {
                uplinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
            }
            uplinkCount.sqn = sequenceNumber;
        } else {
            if (downlinkCount.sqn.longValue() > sequenceNumber.longValue()) {
                downlinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
            }
            downlinkCount.sqn = sequenceNumber;
        }
    }

    public void countOnEncrypt(boolean isUplink) {
        if (!isUplink) {
            downlinkCount.sqn = new Octet((downlinkCount.sqn.longValue() + 1) & 0xFF);
            if (downlinkCount.sqn.longValue() == 0) {
                downlinkCount.overflow = new Octet2((downlinkCount.overflow.longValue() + 1) & 0xFFFF);
            }
        } else {
            uplinkCount.sqn = new Octet((uplinkCount.sqn.longValue() + 1) & 0xFF);
            if (uplinkCount.sqn.longValue() == 0) {
                uplinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
            }
        }
    }

    public static class Count {
        public Octet2 overflow;
        public Octet sqn;

        public Count() {
            this.overflow = new Octet2();
            this.sqn = new Octet();
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
