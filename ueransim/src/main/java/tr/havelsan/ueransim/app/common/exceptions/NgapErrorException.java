/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.exceptions;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseMisc;

import java.util.UUID;

public class NgapErrorException extends RuntimeException {

    public final NGAP_Enumerated cause;
    public final UUID associatedUe;

    public NgapErrorException(NGAP_Enumerated cause, UUID associatedUe) {
        this.cause = cause;
        this.associatedUe = associatedUe;
    }

    public NgapErrorException(NGAP_Enumerated cause) {
        this(cause, null);
    }

    public NgapErrorException() {
        this(NGAP_CauseMisc.UNSPECIFIED, null);
    }
}
