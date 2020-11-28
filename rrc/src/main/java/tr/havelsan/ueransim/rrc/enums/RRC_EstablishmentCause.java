/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.enums;

import tr.havelsan.ueransim.rrc.core.RrcEnum;

public class RRC_EstablishmentCause extends RrcEnum {

    public static final RRC_EstablishmentCause EMERGENCY = new RRC_EstablishmentCause("emergency");
    public static final RRC_EstablishmentCause HIGH_PRIORITY_ACCESS = new RRC_EstablishmentCause("highPriorityAccess");
    public static final RRC_EstablishmentCause MT_ACCESS = new RRC_EstablishmentCause("mt-Access");
    public static final RRC_EstablishmentCause MO_SIGNALLING = new RRC_EstablishmentCause("mo-Signalling");
    public static final RRC_EstablishmentCause MO_DATA = new RRC_EstablishmentCause("mo-Data");
    public static final RRC_EstablishmentCause MO_VOICE_CALL = new RRC_EstablishmentCause("mo-VoiceCall");
    public static final RRC_EstablishmentCause MO_VIDEO_CALL = new RRC_EstablishmentCause("mo-VideoCall");
    public static final RRC_EstablishmentCause MO_SMS = new RRC_EstablishmentCause("mo-SMS");
    public static final RRC_EstablishmentCause MPS_PRIORITY_ACCESS = new RRC_EstablishmentCause("mps-PriorityAccess");
    public static final RRC_EstablishmentCause MCS_PRIORITY_ACCESS = new RRC_EstablishmentCause("mcs-PriorityAccess");

    protected RRC_EstablishmentCause(String sValue) {
        super(sValue);
    }
}
