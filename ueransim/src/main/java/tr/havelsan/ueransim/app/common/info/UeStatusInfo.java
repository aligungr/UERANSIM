/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
