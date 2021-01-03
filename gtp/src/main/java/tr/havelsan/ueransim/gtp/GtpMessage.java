/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.gtp;

import tr.havelsan.ueransim.gtp.ext.GtpExtHeader;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.List;

public class GtpMessage {

    // GTP Message Types. (Only GTP-U included)
    public static final int MT_ECHO_REQUEST = 1;
    public static final int MT_ECHO_RESPONSE = 2;
    public static final int MT_ERROR_INDICATION = 26;
    public static final int MT_SUPPORTED_EXT_HEADERS_NOTIFICATION = 31;
    public static final int MT_END_MARKER = 254;
    public static final int MT_G_PDU = 255;

    public Octet msgType;
    public Octet4 teid;
    public Octet2 seq;
    public Octet nPduNum;
    public List<GtpExtHeader> extHeaders;
    public OctetString payload;
}
