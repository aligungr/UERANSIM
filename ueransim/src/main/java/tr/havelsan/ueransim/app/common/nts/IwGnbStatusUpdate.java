/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

public class IwGnbStatusUpdate {
    public static final int INITIAL_SCTP_ESTABLISHED = 1;

    public final int what;

    // INITIAL_SCTP_ESTABLISHED
    public boolean isInitialSctpEstablished;

    public IwGnbStatusUpdate(int what) {
        this.what = what;
    }
}
