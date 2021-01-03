/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.gtp.pdusup;

import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet3;

// See 3GPP 38.415
public class DlPduSessionInformation extends PduSessionInformation {

    public boolean qmp;           // (Mandatory) QoS Monitoring Packet, See 5.5.3.8
    public Bit6 qfi;              // (Mandatory) QOS Flow Identifier, See 5.5.3.3
    public boolean rqi;           // (Mandatory) Reflective QOS Indicator, See 5.5.3.4
    public Integer ppi;           // (Optional, may be null) Paging Policy Indicator, See 5.5.3.7
    public Long dlSendingTs;      // (Optional, may be null) DL Sending Time Stamp, See 5.5.3.9
    public Octet3 dlQfiSeq;       // (Optional, may be null) DL QFI Sequence Number, See 5.5.3.18

    public DlPduSessionInformation() {
        super(PDU_TYPE_DL);
    }
}
