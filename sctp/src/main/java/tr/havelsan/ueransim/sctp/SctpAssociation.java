/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

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
