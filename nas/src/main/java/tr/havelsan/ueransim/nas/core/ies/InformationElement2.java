/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.core.ies;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

/**
 * Information elements of format T with value part consisting of 0 octets
 */
public abstract class InformationElement2 extends InformationElement {

    protected abstract InformationElement2 decodeIE2();

    @Override
    public final InformationElement decodeIE(OctetInputStream stream) {
        return decodeIE2();
    }

    public abstract void encodeIE2(OctetOutputStream stream);
}
