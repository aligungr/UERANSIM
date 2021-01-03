/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.nas.sec;

import tr.havelsan.ueransim.app.common.NasCount;
import tr.havelsan.ueransim.app.common.SelectedAlgorithms;
import tr.havelsan.ueransim.app.common.UeKeys;
import tr.havelsan.ueransim.app.common.contexts.NasContext;
import tr.havelsan.ueransim.app.common.enums.EConnectionIdentifier;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class NasSecurityContext {
    public final NasContext nasCtx;
    public IENasKeySetIdentifier ngKsi;

    public NasCount downlinkCount;
    public NasCount uplinkCount;

    public EConnectionIdentifier connectionIdentifier;

    public UeKeys keys;
    public SelectedAlgorithms selectedAlgorithms;

    public NasSecurityContext(NasContext nasCtx, ETypeOfSecurityContext tsc, Bit3 ngKsi) {
        this.nasCtx = nasCtx;
        this.ngKsi = new IENasKeySetIdentifier(tsc, ngKsi);
        this.downlinkCount = new NasCount();
        this.uplinkCount = new NasCount();
        this.connectionIdentifier = EConnectionIdentifier.THREE_3GPP_ACCESS;
        this.selectedAlgorithms = new SelectedAlgorithms(ETypeOfIntegrityProtectionAlgorithm.IA0, ETypeOfCipheringAlgorithm.EA0);
        this.keys = new UeKeys();
    }

    public void updateDownlinkCount(NasCount validatedCount) {
        downlinkCount.overflow = validatedCount.overflow;
        downlinkCount.sqn = validatedCount.sqn;
    }

    public NasCount estimatedDownlinkCount(Octet sequenceNumber) {
        var count = new NasCount();
        count.sqn = downlinkCount.sqn;
        count.overflow = downlinkCount.overflow;

        if (count.sqn.longValue() > sequenceNumber.longValue()) {
            count.overflow = new Octet2((count.overflow.longValue() + 1) & 0xFFFF);
        }
        count.sqn = sequenceNumber;
        return count;
    }

    public void countOnEncrypt() {
        uplinkCount.sqn = new Octet((uplinkCount.sqn.longValue() + 1) & 0xFF);
        if (uplinkCount.sqn.longValue() == 0) {
            uplinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
        }
    }

    public NasSecurityContext deepCopy() {
        var ctx = new NasSecurityContext(this.nasCtx, this.ngKsi.tsc, this.ngKsi.nasKeySetIdentifier);
        ctx.downlinkCount = this.downlinkCount.deepCopy();
        ctx.uplinkCount = this.uplinkCount.deepCopy();
        ctx.connectionIdentifier = this.connectionIdentifier;
        ctx.keys = this.keys.deepCopy();
        ctx.selectedAlgorithms = this.selectedAlgorithms;
        return ctx;
    }
}
