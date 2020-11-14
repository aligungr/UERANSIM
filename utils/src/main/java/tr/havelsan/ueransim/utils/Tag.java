/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

public enum Tag {
    SYSTEM,
    STATE,
    CONFIG,
    TUN,
    VALUE,
    PROC,
    MESSAGING,
    CONNECTION,
    EVENT,
    NAS_TIMER,
    GTP,
    NGAP_INTERNAL,
    PROCEDURE_RESULT,
    NAS_SECURITY,
    NOT_IMPL_YET,
    UE_APP;

    public boolean dispatch() {
        switch (this) {
            case SYSTEM:
            case CONNECTION:
            default:
                return false;
        }
    }
}
