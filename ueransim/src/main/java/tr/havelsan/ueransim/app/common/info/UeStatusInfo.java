/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.info;

import java.util.LinkedHashMap;

public class UeStatusInfo {
    public boolean isConnected;
    public String connectedGnb;
    public String mmState;
    public String rmState;
    public LinkedHashMap<Integer, UePduSessionInfo> pduSessions;

    public UeStatusInfo() {
        this.pduSessions = new LinkedHashMap<>();
    }
}
