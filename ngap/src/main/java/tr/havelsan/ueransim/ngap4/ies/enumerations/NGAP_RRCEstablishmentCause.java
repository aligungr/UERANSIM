package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_RRCEstablishmentCause extends NgapEnumerated {

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

    protected NGAP_RRCEstablishmentCause(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "RRCEstablishmentCause";
    }

    @Override
    protected String getXmlTagName() {
        return "RRCEstablishmentCause";
    }
}
