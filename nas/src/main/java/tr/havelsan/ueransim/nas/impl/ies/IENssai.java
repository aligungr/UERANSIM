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
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;

public class IENssai extends InformationElement4 {
    public IESNssai[] sNssais;

    public IENssai() {
    }

    public IENssai(IESNssai[] sNssais) {
        this.sNssais = sNssais;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IENssai();
        res.sNssais = Utils.decodeList(stream, s -> NasDecoder.ie2346(s, IESNssai.class), length, IESNssai.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(sNssais).forEach(snssa -> NasEncoder.ie2346(stream, snssa));
    }
}
