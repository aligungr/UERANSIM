package tr.havelsan.ueransim.ngap2;

public enum NgapCriticality {
    REJECT,
    IGNORE,
    NOTIFY;

    int getAsnValue() {
        switch (this) {
            case REJECT:
                return 0;
            case IGNORE:
                return 1;
            case NOTIFY:
                return 2;
            default:
                throw new RuntimeException();
        }
    }
}
