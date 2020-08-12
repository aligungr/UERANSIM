package tr.havelsan.ueransim.sctp;

public class SctpAssociation {

    public final int id;
    public final int inbound;
    public final int outbound;

    public SctpAssociation(int id, int inbound, int outbound) {
        this.id = id;
        this.inbound = inbound;
        this.outbound = outbound;
    }

    @Override
    public String toString() {
        return "SctpAssociation{" +
                "id=" + id +
                ", inbound=" + inbound +
                ", outbound=" + outbound +
                '}';
    }
}
