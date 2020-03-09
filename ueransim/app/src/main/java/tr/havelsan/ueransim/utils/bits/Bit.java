package tr.havelsan.ueransim.utils.bits;

public final class Bit extends BitN {
    public Bit(int value) {
        super(value, 1);
    }

    public Bit(Bit value) {
        super(value);
    }
}