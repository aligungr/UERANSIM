/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.info;

import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;

public class UeConnectionInfo {
    public boolean isEstablished;
    public int pduSessionId;
    public EPduSessionType sessionType;
    public byte[] pduAddress;
}
