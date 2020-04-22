package tr.havelsan.ueransim.nas.exceptions;

public class NasSecurityException extends Exception {

    // (arbitrary unique values)
    public static final int MAC_MISMATCH = 1000;
    public static final int NO_SECURITY_CONTEXT = 1001;
    public static final int INVALID_INTEGRITY_ALG = 1002;
    public static final int INVALID_CIPHERING_ALG = 1003;

    private final int reason;

    public NasSecurityException(int reason) {
        this.reason = reason;
    }

    public NasSecurityException(int reason, String message) {
        super(message);
        this.reason = reason;
    }

    public int getReason() {
        return reason;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
