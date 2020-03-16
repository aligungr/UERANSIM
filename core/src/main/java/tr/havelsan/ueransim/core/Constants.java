package tr.havelsan.ueransim.core;

public final class Constants {
    public static final String FLOWS_PREFIX = "tr.havelsan.ueransim.flowtesting.flows";
    public static final String NAS_IMPL_PREFIX = "tr.havelsan.ueransim.nas.impl";
    public static final String NGAP_PDU_CONTENTS = "tr.havelsan.ueransim.ngap.ngap_pdu_contents";

    public static final int BACKEND_PORT = 5002;

    public static final int NGAP_PROTOCOL_ID = 60;
    public static final int DEFAULT_STREAM_NUMBER = 1;

    // here assumed always 3 digit, but it can be changed to false for actual production, no problem.
    public static final boolean ALWAYS_LONG_MNC = true;
}
