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
import tr.havelsan.ueransim.utils.octets.Octet4;

// See 3GPP 38.415
public class UlPduSessionInformation extends PduSessionInformation {

    public boolean qmp;                     // (Mandatory) QoS Monitoring Packet, See 5.5.3.8
    public Bit6 qfi;                        // (Mandatory) QOS Flow Identifier, See 5.5.3.3
    public Long dlSendingTsRepeated;        // (Optional, may be null) DL Sending Time Stamp Repeated, See 5.5.3.10
    public Long dlReceivedTs;               // (Optional, may be null) DL Received Time Stamp, See 5.5.3.11
    public Long ulSendingTs;                // (Optional, may be null) UL Sending Time Stamp, See 5.5.3.12
    public Octet4 dlDelayResult;            // (Optional, may be null) DL Delay Result, See 5.5.3.14
    public Octet4 ulDelayResult;            // (Optional, may be null) UL Delay Result, See 5.5.3.16
    public Octet3 ulQfiSeq;                 // (Optional, may be null) UL QFI Sequence Number, See 5.5.3.19

    public UlPduSessionInformation() {
        super(PDU_TYPE_UL);
    }
}
