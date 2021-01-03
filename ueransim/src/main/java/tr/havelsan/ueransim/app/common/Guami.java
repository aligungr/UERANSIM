/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.utils.bits.Bit10;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;

public class Guami {
    public final EMccValue mcc;
    public final EMncValue mnc;
    public final Octet amfRegionId;
    public final Bit10 amfSetId;
    public final Bit6 amfPointer;

    public Guami(EMccValue mcc, EMncValue mnc, Octet amfRegionId, Bit10 amfSetId, Bit6 amfPointer) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.amfRegionId = amfRegionId;
        this.amfSetId = amfSetId;
        this.amfPointer = amfPointer;
    }

    @Override
    public String toString() {
        return "Guami{" +
                "mcc=" + mcc +
                ", mnc=" + mnc +
                ", amfRegionId=" + amfRegionId +
                ", amfSetId=" + amfSetId +
                ", amfPointer=" + amfPointer +
                '}';
    }
}
