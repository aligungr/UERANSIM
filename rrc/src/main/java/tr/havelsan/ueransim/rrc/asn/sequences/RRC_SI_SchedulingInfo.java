/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_SI_SchedulingInfo extends AsnSequence {
    public RRC_schedulingInfoList schedulingInfoList; // mandatory, SIZE(1..32)
    public RRC_si_WindowLength si_WindowLength; // mandatory
    public RRC_SI_RequestConfig si_RequestConfig; // optional
    public RRC_SI_RequestConfig si_RequestConfigSUL; // optional
    public AsnBitString systemInformationAreaID; // optional, SIZE(24)

    public static class RRC_si_WindowLength extends AsnEnumerated {
        public static final RRC_si_WindowLength S5 = new RRC_si_WindowLength(0);
        public static final RRC_si_WindowLength S10 = new RRC_si_WindowLength(1);
        public static final RRC_si_WindowLength S20 = new RRC_si_WindowLength(2);
        public static final RRC_si_WindowLength S40 = new RRC_si_WindowLength(3);
        public static final RRC_si_WindowLength S80 = new RRC_si_WindowLength(4);
        public static final RRC_si_WindowLength S160 = new RRC_si_WindowLength(5);
        public static final RRC_si_WindowLength S320 = new RRC_si_WindowLength(6);
        public static final RRC_si_WindowLength S640 = new RRC_si_WindowLength(7);
        public static final RRC_si_WindowLength S1280 = new RRC_si_WindowLength(8);
    
        private RRC_si_WindowLength(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_schedulingInfoList extends AsnSequenceOf<RRC_SchedulingInfo> {
    }
}

