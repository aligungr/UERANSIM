/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.NotImplementedException;

public class IES1UeSecurityCapability extends InformationElement4 {
    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        throw new NotImplementedException("Not implemented yet: See TS 24.501 9.11.3.48A");
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        throw new NotImplementedException("Not implemented yet: See TS 24.501 9.11.3.48A");
    }
}
