package tr.havelsan.ueransim.app.link.rlc.pdu;

public class NackBlock {
    public int nackSn;
    public int soStart;

    // (The special SOend value "1111111111111111" is used to indicate that the missing portion
    // of the RLC SDU includes all bytes to the last byte of the RLC SDU.)
    public int soEnd;

    public int nackRange;
}
