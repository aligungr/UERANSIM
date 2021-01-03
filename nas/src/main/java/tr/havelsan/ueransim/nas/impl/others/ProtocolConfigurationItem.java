/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
