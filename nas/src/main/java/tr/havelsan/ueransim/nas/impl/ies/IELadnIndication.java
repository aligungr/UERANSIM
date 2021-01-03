/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;

public class IELadnIndication extends InformationElement6 {
    public IEDnn[] dnns;

    public IELadnIndication() {
    }

    public IELadnIndication(IEDnn[] dnns) {
        this.dnns = dnns;
    }

    @Override
    protected IELadnIndication decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnIndication();
        res.dnns = Utils.decodeList(stream, s -> NasDecoder.ie2346(s, IEDnn.class), length, IEDnn.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        Arrays.stream(dnns).forEach(dnn -> NasEncoder.ie2346(stream, dnn));
    }
}
