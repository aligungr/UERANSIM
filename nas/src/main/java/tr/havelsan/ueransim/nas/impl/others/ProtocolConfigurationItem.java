/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.others;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class ProtocolConfigurationItem {
    public final int id;
    public final boolean isUplink;
    public final OctetString content;

    public ProtocolConfigurationItem(int id, boolean isUplink, OctetString content) {
        this.id = id;
        this.isUplink = isUplink;
        this.content = content;
    }
}
