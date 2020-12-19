/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.common.PduSession;
import tr.havelsan.ueransim.app.common.enums.EMmState;
import tr.havelsan.ueransim.app.common.enums.EMmSubState;
import tr.havelsan.ueransim.app.common.enums.ERmState;

public class IwUeStatusUpdate {
    public static final int CONNECTED_GNB = 1;
    public static final int MM_STATE = 2;
    public static final int RM_STATE = 3;
    public static final int SESSION_ESTABLISHMENT = 4;

    public final int what;

    // CONNECTED_GNB
    public String gnbName;

    // MM_STATE
    public EMmState mmState;
    public EMmSubState mmSubState;

    // RM_STATE
    public ERmState rmState;

    // SESSION_ESTABLISHMENT
    public PduSession pduSession;

    public IwUeStatusUpdate(int what) {
        this.what = what;
    }
}
