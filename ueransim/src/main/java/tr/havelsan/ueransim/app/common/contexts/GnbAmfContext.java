/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.sctp.ISctpClient;
import tr.havelsan.ueransim.sctp.SctpAssociation;

public class GnbAmfContext {
    public final Guami guami;
    public ISctpClient sctpClient;
    public SctpAssociation association;
    public int nextStream; // next available SCTP stream for uplink
    public String host;
    public int port;

    public GnbAmfContext(Guami guami) {
        this.guami = guami;
    }
}
