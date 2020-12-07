/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DummyF extends AsnSequence {
    public AsnInteger maxNumberPeriodicCSI_ReportPerBWP; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberAperiodicCSI_ReportPerBWP; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberSemiPersistentCSI_ReportPerBWP; // mandatory, VALUE(0..4)
    public AsnInteger simultaneousCSI_ReportsAllCC; // mandatory, VALUE(5..32)
}

