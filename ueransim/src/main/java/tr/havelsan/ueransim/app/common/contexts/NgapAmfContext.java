/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.enums.EAmfState;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PLMNSupportItem;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_ServedGUAMIItem;
import tr.havelsan.ueransim.sctp.ISctpClient;
import tr.havelsan.ueransim.sctp.SctpAssociation;

import java.util.List;
import java.util.UUID;

public class NgapAmfContext {
    public final UUID ctxId;
    public ISctpClient sctpClient;
    public SctpAssociation association;
    public int nextStream; // next available SCTP stream for uplink
    public String host;
    public int port;
    public String amfName;
    public int relativeCapacity;
    public List<NGAP_ServedGUAMIItem> servedGuamiList;
    public List<NGAP_PLMNSupportItem> plmnSupportList;
    public EAmfState state;

    public NgapAmfContext() {
        this.ctxId = UUID.randomUUID();
        this.state = EAmfState.NOT_CONNECTED;
    }
}
