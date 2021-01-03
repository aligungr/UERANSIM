/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.nas.impl.enums.ESmCause;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IE5gSmCause extends InformationElement3 {
    public ESmCause value;

    public IE5gSmCause() {
    }

    public IE5gSmCause(ESmCause value) {
        this.value = value;
    }

    @Override
    protected IE5gSmCause decodeIE3(OctetInputStream stream) {
        var res = new IE5gSmCause();
        res.value = ESmCause.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(value.intValue());
    }
}
