package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.enums.EConnectionIdentifier;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.structs.NasCount;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class NasSecurityContext {
    public IENasKeySetIdentifier ngKsi;

    public NasCount downlinkCount;
    public NasCount uplinkCount;

    public EConnectionIdentifier connectionIdentifier;

    public UeKeys keys;
    public SelectedAlgorithms selectedAlgorithms;

    public NasSecurityContext(ETypeOfSecurityContext tsc, Bit3 ngKsi) {
        this.ngKsi = new IENasKeySetIdentifier(tsc, ngKsi);
        this.downlinkCount = new NasCount();
        this.uplinkCount = new NasCount();
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
        ctx.downlinkCount = this.downlinkCount.deepCopy();
        ctx.uplinkCount = this.uplinkCount.deepCopy();
        ctx.connectionIdentifier = this.connectionIdentifier;
        ctx.keys = this.keys.deepCopy();
        ctx.selectedAlgorithms = this.selectedAlgorithms;
        return ctx;
    }
}
