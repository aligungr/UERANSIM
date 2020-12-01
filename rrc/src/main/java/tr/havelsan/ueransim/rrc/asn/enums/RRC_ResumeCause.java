/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Enumerated;

public class RRC_ResumeCause extends RRC_Enumerated {

    public static final RRC_ResumeCause EMERGENCY = new RRC_ResumeCause("emergency");
    public static final RRC_ResumeCause HIGHPRIORITYACCESS = new RRC_ResumeCause("highPriorityAccess");
    public static final RRC_ResumeCause MT_ACCESS = new RRC_ResumeCause("mt-Access");
    public static final RRC_ResumeCause MO_SIGNALLING = new RRC_ResumeCause("mo-Signalling");
    public static final RRC_ResumeCause MO_DATA = new RRC_ResumeCause("mo-Data");
    public static final RRC_ResumeCause MO_VOICECALL = new RRC_ResumeCause("mo-VoiceCall");
    public static final RRC_ResumeCause MO_VIDEOCALL = new RRC_ResumeCause("mo-VideoCall");
    public static final RRC_ResumeCause MO_SMS = new RRC_ResumeCause("mo-SMS");
    public static final RRC_ResumeCause RNA_UPDATE = new RRC_ResumeCause("rna-Update");
    public static final RRC_ResumeCause MPS_PRIORITYACCESS = new RRC_ResumeCause("mps-PriorityAccess");
    public static final RRC_ResumeCause MCS_PRIORITYACCESS = new RRC_ResumeCause("mcs-PriorityAccess");
    public static final RRC_ResumeCause SPARE1 = new RRC_ResumeCause("spare1");
    public static final RRC_ResumeCause SPARE2 = new RRC_ResumeCause("spare2");
    public static final RRC_ResumeCause SPARE3 = new RRC_ResumeCause("spare3");
    public static final RRC_ResumeCause SPARE4 = new RRC_ResumeCause("spare4");
    public static final RRC_ResumeCause SPARE5 = new RRC_ResumeCause("spare5");

    protected RRC_ResumeCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ResumeCause";
    }

    @Override
    public String getXmlTagName() {
        return "ResumeCause";
    }

}
