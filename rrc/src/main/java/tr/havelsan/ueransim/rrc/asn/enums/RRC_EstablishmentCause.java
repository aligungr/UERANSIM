/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_EstablishmentCause extends AsnEnumerated {
    public static final RRC_EstablishmentCause EMERGENCY = new RRC_EstablishmentCause(0);
    public static final RRC_EstablishmentCause HIGHPRIORITYACCESS = new RRC_EstablishmentCause(1);
    public static final RRC_EstablishmentCause MT_ACCESS = new RRC_EstablishmentCause(2);
    public static final RRC_EstablishmentCause MO_SIGNALLING = new RRC_EstablishmentCause(3);
    public static final RRC_EstablishmentCause MO_DATA = new RRC_EstablishmentCause(4);
    public static final RRC_EstablishmentCause MO_VOICECALL = new RRC_EstablishmentCause(5);
    public static final RRC_EstablishmentCause MO_VIDEOCALL = new RRC_EstablishmentCause(6);
    public static final RRC_EstablishmentCause MO_SMS = new RRC_EstablishmentCause(7);
    public static final RRC_EstablishmentCause MPS_PRIORITYACCESS = new RRC_EstablishmentCause(8);
    public static final RRC_EstablishmentCause MCS_PRIORITYACCESS = new RRC_EstablishmentCause(9);
    public static final RRC_EstablishmentCause SPARE6 = new RRC_EstablishmentCause(10);
    public static final RRC_EstablishmentCause SPARE5 = new RRC_EstablishmentCause(11);
    public static final RRC_EstablishmentCause SPARE4 = new RRC_EstablishmentCause(12);
    public static final RRC_EstablishmentCause SPARE3 = new RRC_EstablishmentCause(13);
    public static final RRC_EstablishmentCause SPARE2 = new RRC_EstablishmentCause(14);
    public static final RRC_EstablishmentCause SPARE1 = new RRC_EstablishmentCause(15);

    private RRC_EstablishmentCause(long value) {
        super(value);
    }
}

