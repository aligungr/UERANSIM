/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;

public class IEPlmnList extends InformationElement4 {
    public VPlmn[] plmns;

    public IEPlmnList() {
    }

    public IEPlmnList(VPlmn[] plmns) {
        this.plmns = plmns;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEPlmnList();
        res.plmns = Utils.decodeList(stream, new VPlmn()::decode, length, VPlmn.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(plmns).forEach(vMccMnc -> vMccMnc.encode(stream));
    }
}
