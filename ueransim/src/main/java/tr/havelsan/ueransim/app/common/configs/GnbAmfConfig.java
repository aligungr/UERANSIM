/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.app.common.Guami;

public class GnbAmfConfig {
    public final Guami guami;
    public final String host;
    public final int port;

    public GnbAmfConfig(Guami guami, String host, int port) {
        this.guami = guami;
        this.host = host;
        this.port = port;
    }
}
