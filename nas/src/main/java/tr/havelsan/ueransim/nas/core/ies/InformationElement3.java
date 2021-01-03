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
 * Information elements of format V or TV with value part that has fixed length of at least one octet
 */
public abstract class InformationElement3 extends InformationElement {

    protected abstract InformationElement3 decodeIE3(OctetInputStream stream);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream) {
        return decodeIE3(stream);
    }

    public abstract void encodeIE3(OctetOutputStream stream);
}
