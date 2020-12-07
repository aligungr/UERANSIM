/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_QuantityConfigRS extends AsnSequence {
    public RRC_FilterConfig ssb_FilterConfig; // mandatory
    public RRC_FilterConfig csi_RS_FilterConfig; // mandatory
}

