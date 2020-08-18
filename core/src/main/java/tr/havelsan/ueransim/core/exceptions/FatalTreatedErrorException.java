package tr.havelsan.ueransim.core.exceptions;

public class FatalTreatedErrorException extends RuntimeException {
    private final String message;

    public FatalTreatedErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
