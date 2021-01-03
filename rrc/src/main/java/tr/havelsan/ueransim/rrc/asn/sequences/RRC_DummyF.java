/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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

