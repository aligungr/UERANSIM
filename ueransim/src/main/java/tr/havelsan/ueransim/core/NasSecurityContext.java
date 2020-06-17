package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class NasSecurityContext {
    public IENasKeySetIdentifier ngKsi;
    public boolean isNew;

    public Count downlinkCount;
    public Count uplinkCount;

    public EConnectionIdentifier connectionIdentifier;

    public UeKeys keys;
    public SelectedAlgorithms selectedAlgorithms;

    public NasSecurityContext(ETypeOfSecurityContext tsc, Bit3 ngKsi) {
        this.isNew = true;
        this.ngKsi = new IENasKeySetIdentifier(tsc, ngKsi);
        this.downlinkCount = new Count();
        this.uplinkCount = new Count();
        this.connectionIdentifier = EConnectionIdentifier.THREE_3GPP_ACCESS;
        this.selectedAlgorithms = new SelectedAlgorithms(ETypeOfIntegrityProtectionAlgorithm.IA0, ETypeOfCipheringAlgorithm.EA0);
        this.keys = new UeKeys();
    }

    public void countOnDecrypt(Octet sequenceNumber) {
        if (downlinkCount.sqn.longValue() > sequenceNumber.longValue()) {
            downlinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
        }
        downlinkCount.sqn = sequenceNumber;
    }

    public void countOnEncrypt() {
        uplinkCount.sqn = new Octet((uplinkCount.sqn.longValue() + 1) & 0xFF);
        if (uplinkCount.sqn.longValue() == 0) {
            uplinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
        }
    }

    public NasSecurityContext deepCopy() {
        var ctx = new NasSecurityContext(this.ngKsi.tsc, this.ngKsi.nasKeySetIdentifier);
        ctx.isNew = this.isNew;
        ctx.downlinkCount = this.downlinkCount.deepCopy();
        ctx.uplinkCount = this.uplinkCount.deepCopy();
        ctx.connectionIdentifier = this.connectionIdentifier;
        ctx.keys = this.keys.deepCopy();
        ctx.selectedAlgorithms = this.selectedAlgorithms;
        return ctx;
    }

    public static class Count {
        public Octet2 overflow;
        public Octet sqn;

        public Count() {
            this.overflow = new Octet2();
            this.sqn = new Octet();
        }

        public Count deepCopy() {
            var res = new Count();
            res.overflow = this.overflow;
            res.sqn = this.sqn;
            return res;
        }

        public Octet4 toOctet4() {
            long value = 0;
            value |= this.overflow.longValue();
            value <<= 8;
            value |= this.sqn.longValue();
            return new Octet4(value);
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
