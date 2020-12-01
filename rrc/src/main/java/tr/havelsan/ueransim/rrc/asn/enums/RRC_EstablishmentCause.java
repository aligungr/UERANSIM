/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_EstablishmentCause extends RRC_Enumerated {

    public static final RRC_EstablishmentCause EMERGENCY = new RRC_EstablishmentCause("emergency");
    public static final RRC_EstablishmentCause HIGHPRIORITYACCESS = new RRC_EstablishmentCause("highPriorityAccess");
    public static final RRC_EstablishmentCause MT_ACCESS = new RRC_EstablishmentCause("mt-Access");
    public static final RRC_EstablishmentCause MO_SIGNALLING = new RRC_EstablishmentCause("mo-Signalling");
    public static final RRC_EstablishmentCause MO_DATA = new RRC_EstablishmentCause("mo-Data");
    public static final RRC_EstablishmentCause MO_VOICECALL = new RRC_EstablishmentCause("mo-VoiceCall");
    public static final RRC_EstablishmentCause MO_VIDEOCALL = new RRC_EstablishmentCause("mo-VideoCall");
    public static final RRC_EstablishmentCause MO_SMS = new RRC_EstablishmentCause("mo-SMS");
    public static final RRC_EstablishmentCause MPS_PRIORITYACCESS = new RRC_EstablishmentCause("mps-PriorityAccess");
    public static final RRC_EstablishmentCause MCS_PRIORITYACCESS = new RRC_EstablishmentCause("mcs-PriorityAccess");
    public static final RRC_EstablishmentCause SPARE6 = new RRC_EstablishmentCause("spare6");
    public static final RRC_EstablishmentCause SPARE5 = new RRC_EstablishmentCause("spare5");
    public static final RRC_EstablishmentCause SPARE4 = new RRC_EstablishmentCause("spare4");
    public static final RRC_EstablishmentCause SPARE3 = new RRC_EstablishmentCause("spare3");
    public static final RRC_EstablishmentCause SPARE2 = new RRC_EstablishmentCause("spare2");
    public static final RRC_EstablishmentCause SPARE1 = new RRC_EstablishmentCause("spare1");

    protected RRC_EstablishmentCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "EstablishmentCause";
    }

    @Override
    public String getXmlTagName() {
        return "EstablishmentCause";
    }

}
