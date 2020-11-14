/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

public abstract class IESuciMobileIdentity extends IE5gsMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new IncorrectImplementationException("subtypes must override this method");
    }
}
