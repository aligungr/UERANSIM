/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;

public class IEUeParametersUpdateTransparentContainer extends InformationElement6 {

    @Override
    protected InformationElement6 decodeIE6(OctetInputStream stream, int length) {
        throw new NotImplementedException("Not implemented yet. See: 24.501 9.11.3.53A");
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("Not implemented yet. See: 24.501 9.11.3.53A");
    }
}
