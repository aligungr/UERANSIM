package tr.havelsan.ueransim.sim;

import tr.havelsan.ueransim.sim.ue.UserLocationInformationNr;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class FlowConfig {
    public final int ranUeNgapId;
    public final int amfUeNgapId;
    public final UserLocationInformationNr userLocationInformation;
    public final ERrcEstablishmentCause rrcEstablishmentCause;
    public final boolean logNgapPdu;

    public FlowConfig(int ranUeNgapId, int amfUeNgapId, UserLocationInformationNr userLocationInformation, ERrcEstablishmentCause rrcEstablishmentCause, Boolean logNgapPdu) {
        this.ranUeNgapId = ranUeNgapId;
        this.amfUeNgapId = amfUeNgapId;
        this.userLocationInformation = userLocationInformation;
        this.rrcEstablishmentCause = rrcEstablishmentCause;
        this.logNgapPdu = logNgapPdu == null ? true : logNgapPdu;
    }

    public static class ERrcEstablishmentCause extends ProtocolEnum {
        public static final ERrcEstablishmentCause EMERGENCY = new ERrcEstablishmentCause(0, "emergency");
        public static final ERrcEstablishmentCause HIGH_PRIORITY_ACCESS = new ERrcEstablishmentCause(1, "highPriorityAccess");
        public static final ERrcEstablishmentCause MT_ACCESS = new ERrcEstablishmentCause(2, "mt-access");
        public static final ERrcEstablishmentCause MO_SIGNALLING = new ERrcEstablishmentCause(3, "mo-signalling");
        public static final ERrcEstablishmentCause MO_DATA = new ERrcEstablishmentCause(4, "mo-data");
        public static final ERrcEstablishmentCause MO_VOICE_CALL = new ERrcEstablishmentCause(5, "mo-voiceCall");
        public static final ERrcEstablishmentCause MO_VIDEO_CALL = new ERrcEstablishmentCause(6, "mo-videoCall");
        public static final ERrcEstablishmentCause MO_SMS = new ERrcEstablishmentCause(7, "mo-sms");
        public static final ERrcEstablishmentCause MPS_PRIORITY_ACCESS = new ERrcEstablishmentCause(8, "mps-priorityAccess");
        public static final ERrcEstablishmentCause MCS_PRIORITY_ACCESS = new ERrcEstablishmentCause(9, "mps-ğriorityAccess");

        protected ERrcEstablishmentCause(int value, String name) {
            super(value, name);
        }

        public static ERrcEstablishmentCause fromValue(int value) {
            return fromValueGeneric(ERrcEstablishmentCause.class, value, null);
        }
    }
}
