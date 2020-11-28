/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.enums;

public enum ERrcState {
    RRC_CONNECTED,
    RRC_INACTIVE,
    RRC_IDLE;

    public boolean hasConnection() {
        return this != RRC_IDLE;
    }
}
