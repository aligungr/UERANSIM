/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
