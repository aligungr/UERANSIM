/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_ResumeCause extends AsnEnumerated {
    public static final RRC_ResumeCause EMERGENCY = new RRC_ResumeCause(0);
    public static final RRC_ResumeCause HIGHPRIORITYACCESS = new RRC_ResumeCause(1);
    public static final RRC_ResumeCause MT_ACCESS = new RRC_ResumeCause(2);
    public static final RRC_ResumeCause MO_SIGNALLING = new RRC_ResumeCause(3);
    public static final RRC_ResumeCause MO_DATA = new RRC_ResumeCause(4);
    public static final RRC_ResumeCause MO_VOICECALL = new RRC_ResumeCause(5);
    public static final RRC_ResumeCause MO_VIDEOCALL = new RRC_ResumeCause(6);
    public static final RRC_ResumeCause MO_SMS = new RRC_ResumeCause(7);
    public static final RRC_ResumeCause RNA_UPDATE = new RRC_ResumeCause(8);
    public static final RRC_ResumeCause MPS_PRIORITYACCESS = new RRC_ResumeCause(9);
    public static final RRC_ResumeCause MCS_PRIORITYACCESS = new RRC_ResumeCause(10);
    public static final RRC_ResumeCause SPARE1 = new RRC_ResumeCause(11);
    public static final RRC_ResumeCause SPARE2 = new RRC_ResumeCause(12);
    public static final RRC_ResumeCause SPARE3 = new RRC_ResumeCause(13);
    public static final RRC_ResumeCause SPARE4 = new RRC_ResumeCause(14);
    public static final RRC_ResumeCause SPARE5 = new RRC_ResumeCause(15);

    private RRC_ResumeCause(long value) {
        super(value);
    }
}

