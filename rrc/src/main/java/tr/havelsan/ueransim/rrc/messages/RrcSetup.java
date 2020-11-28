/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.messages;

import tr.havelsan.ueransim.rrc.core.RrcMessage;
import tr.havelsan.ueransim.rrc.inners.RRC_RRCSetup_CriticalExtensions;
import tr.havelsan.ueransim.rrc.integers.RRC_RRC_TransactionIdentifier;

public class RrcSetup extends RrcMessage {
    public RRC_RRC_TransactionIdentifier rrc_TransactionIdentifier;
    public RRC_RRCSetup_CriticalExtensions criticalExtensions;
}
