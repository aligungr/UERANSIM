/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.PduSession;
import tr.havelsan.ueransim.app.common.ProcedureTransaction;

public class SmContext {

    public PduSession[] pduSessions;
    public ProcedureTransaction[] procedureTransactions;

    public SmContext() {
        this.pduSessions = new PduSession[16];
        this.procedureTransactions = new ProcedureTransaction[255];
    }
}
