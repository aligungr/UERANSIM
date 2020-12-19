/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.configs;

public class GnbAmfConfig {
    public final String ngapIp;
    public final int ngapPort;

    public GnbAmfConfig(String ngapIp, int ngapPort) {
        this.ngapIp = ngapIp;
        this.ngapPort = ngapPort;
    }
}
