/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_RRCEstablishmentCause extends NGAP_Enumerated {

    public static final NGAP_RRCEstablishmentCause EMERGENCY = new NGAP_RRCEstablishmentCause("emergency");
    public static final NGAP_RRCEstablishmentCause HIGHPRIORITYACCESS = new NGAP_RRCEstablishmentCause("highPriorityAccess");
    public static final NGAP_RRCEstablishmentCause MT_ACCESS = new NGAP_RRCEstablishmentCause("mt-Access");
    public static final NGAP_RRCEstablishmentCause MO_SIGNALLING = new NGAP_RRCEstablishmentCause("mo-Signalling");
    public static final NGAP_RRCEstablishmentCause MO_DATA = new NGAP_RRCEstablishmentCause("mo-Data");
    public static final NGAP_RRCEstablishmentCause MO_VOICECALL = new NGAP_RRCEstablishmentCause("mo-VoiceCall");
    public static final NGAP_RRCEstablishmentCause MO_VIDEOCALL = new NGAP_RRCEstablishmentCause("mo-VideoCall");
    public static final NGAP_RRCEstablishmentCause MO_SMS = new NGAP_RRCEstablishmentCause("mo-SMS");
    public static final NGAP_RRCEstablishmentCause MPS_PRIORITYACCESS = new NGAP_RRCEstablishmentCause("mps-PriorityAccess");
    public static final NGAP_RRCEstablishmentCause MCS_PRIORITYACCESS = new NGAP_RRCEstablishmentCause("mcs-PriorityAccess");
    public static final NGAP_RRCEstablishmentCause NOTAVAILABLE = new NGAP_RRCEstablishmentCause("notAvailable");

    protected NGAP_RRCEstablishmentCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "RRCEstablishmentCause";
    }

    @Override
    public String getXmlTagName() {
        return "RRCEstablishmentCause";
    }
}
