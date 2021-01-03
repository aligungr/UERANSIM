/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.impl.values.VQoSFlowDescription;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;

public class IEQoSFlowDescriptions extends InformationElement6 {
    public VQoSFlowDescription[] qosFlowDescriptions;

    public IEQoSFlowDescriptions() {
    }

    public IEQoSFlowDescriptions(VQoSFlowDescription[] qosFlowDescriptions) {
        this.qosFlowDescriptions = qosFlowDescriptions;
    }

    @Override
    protected IEQoSFlowDescriptions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEQoSFlowDescriptions();
        res.qosFlowDescriptions = Utils.decodeList(stream, octetInputStream ->
                new VQoSFlowDescription().decode(octetInputStream), length, VQoSFlowDescription.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        Arrays.stream(qosFlowDescriptions).forEach(item -> item.encode(stream));
    }
}
