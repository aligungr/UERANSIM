package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;

public class UeConnectionInfo {
    public int pduSessionId;
    public EPduSessionType sessionType;
    public byte[] pduAddress;
}
