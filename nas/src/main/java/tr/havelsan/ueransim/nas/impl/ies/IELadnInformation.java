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
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;

public class IELadnInformation extends InformationElement6 {
    public VLadn[] ladns;

    public IELadnInformation() {
    }

    public IELadnInformation(VLadn[] ladns) {
        this.ladns = ladns;
    }

    @Override
    protected IELadnInformation decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnInformation();
        res.ladns = Utils.decodeList(stream, new VLadn()::decode, length, VLadn.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        Arrays.stream(ladns).forEach(ladn -> ladn.encode(stream));
    }

    public static class VLadn extends NasValue {
        public IEDnn dnn;
        public IE5gsTrackingAreaIdentityList trackingAreaIdentityList;

        public VLadn() {
        }

        public VLadn(IEDnn dnn, IE5gsTrackingAreaIdentityList trackingAreaIdentityList) {
            this.dnn = dnn;
            this.trackingAreaIdentityList = trackingAreaIdentityList;
        }

        @Override
        public VLadn decode(OctetInputStream stream) {
            var res = new VLadn();
            res.dnn = NasDecoder.ie2346(stream, IEDnn.class);
            res.trackingAreaIdentityList = NasDecoder.ie2346(stream, IE5gsTrackingAreaIdentityList.class);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            NasEncoder.ie2346(stream, dnn);
            NasEncoder.ie2346(stream, trackingAreaIdentityList);
        }
    }
}
