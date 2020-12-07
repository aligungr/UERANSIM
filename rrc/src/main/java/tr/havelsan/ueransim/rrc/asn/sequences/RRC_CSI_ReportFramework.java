/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_CSI_ReportFramework extends AsnSequence {
    public AsnInteger maxNumberPeriodicCSI_PerBWP_ForCSI_Report; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberAperiodicCSI_PerBWP_ForCSI_Report; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberSemiPersistentCSI_PerBWP_ForCSI_Report; // mandatory, VALUE(0..4)
    public AsnInteger maxNumberPeriodicCSI_PerBWP_ForBeamReport; // mandatory, VALUE(1..4)
    public AsnInteger maxNumberAperiodicCSI_PerBWP_ForBeamReport; // mandatory, VALUE(1..4)
    public RRC_maxNumberAperiodicCSI_triggeringStatePerCC maxNumberAperiodicCSI_triggeringStatePerCC; // mandatory
    public AsnInteger maxNumberSemiPersistentCSI_PerBWP_ForBeamReport; // mandatory, VALUE(0..4)
    public AsnInteger simultaneousCSI_ReportsPerCC; // mandatory, VALUE(1..8)

    public static class RRC_maxNumberAperiodicCSI_triggeringStatePerCC extends AsnEnumerated {
        public static final RRC_maxNumberAperiodicCSI_triggeringStatePerCC N3 = new RRC_maxNumberAperiodicCSI_triggeringStatePerCC(0);
        public static final RRC_maxNumberAperiodicCSI_triggeringStatePerCC N7 = new RRC_maxNumberAperiodicCSI_triggeringStatePerCC(1);
        public static final RRC_maxNumberAperiodicCSI_triggeringStatePerCC N15 = new RRC_maxNumberAperiodicCSI_triggeringStatePerCC(2);
        public static final RRC_maxNumberAperiodicCSI_triggeringStatePerCC N31 = new RRC_maxNumberAperiodicCSI_triggeringStatePerCC(3);
        public static final RRC_maxNumberAperiodicCSI_triggeringStatePerCC N63 = new RRC_maxNumberAperiodicCSI_triggeringStatePerCC(4);
        public static final RRC_maxNumberAperiodicCSI_triggeringStatePerCC N128 = new RRC_maxNumberAperiodicCSI_triggeringStatePerCC(5);
    
        private RRC_maxNumberAperiodicCSI_triggeringStatePerCC(long value) {
            super(value);
        }
    }
}

