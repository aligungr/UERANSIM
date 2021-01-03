/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapNotification extends Eap {
    public OctetString rawData;

    public EapNotification(ECode code, Octet id) {
        super(code, id, EEapType.NOTIFICATION);
    }

    public EapNotification(ECode code, Octet id, OctetString rawData) {
        super(code, id, EEapType.NOTIFICATION);
        this.rawData = rawData;
    }
}
