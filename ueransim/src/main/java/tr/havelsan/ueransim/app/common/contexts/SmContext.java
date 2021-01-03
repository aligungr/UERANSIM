/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
