/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
