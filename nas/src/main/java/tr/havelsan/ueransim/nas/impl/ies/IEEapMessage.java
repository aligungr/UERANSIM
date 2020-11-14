/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapDecoder;
import tr.havelsan.ueransim.nas.eap.EapEncoder;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEEapMessage extends InformationElement6 {
    public Eap eap;

    public IEEapMessage() {
    }

    public IEEapMessage(Eap eap) {
        this.eap = eap;
    }

    @Override
    protected IEEapMessage decodeIE6(OctetInputStream stream, int length) {
        var res = new IEEapMessage();
        res.eap = EapDecoder.eapPdu(stream);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        EapEncoder.eapPdu(stream, eap);
    }
}
