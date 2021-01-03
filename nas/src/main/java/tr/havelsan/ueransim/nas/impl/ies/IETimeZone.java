/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.nas.impl.values.VTimeZone;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IETimeZone extends InformationElement3 {
    public VTimeZone timeZone;

    public IETimeZone() {
    }

    public IETimeZone(VTimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    protected IETimeZone decodeIE3(OctetInputStream stream) {
        var res = new IETimeZone();
        res.timeZone = new VTimeZone().decode(stream);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        timeZone.encode(stream);
    }
}
