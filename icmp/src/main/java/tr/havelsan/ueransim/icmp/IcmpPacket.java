/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.icmp;

import tr.havelsan.ueransim.utils.OctetOutputStream;

public abstract class IcmpPacket {

    abstract void encode(OctetOutputStream stream);
}
