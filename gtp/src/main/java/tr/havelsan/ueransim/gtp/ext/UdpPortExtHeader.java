/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.gtp.ext;

import tr.havelsan.ueransim.utils.octets.Octet2;

public class UdpPortExtHeader extends GtpExtHeader {
    public Octet2 port;
}
