/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class PduSession {

    public static final int MIN_ID = 1;
    public static final int MAX_ID = 15;

    public static final PduSession RELEASED = new PduSession(EPduSessionIdentity.NO_VAL);

    public final EPduSessionIdentity id;
    public boolean isEstablished;
    public IEQoSRules authorizedQoSRules;
    public IESessionAmbr sessionAmbr;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;
    public IEPduSessionType sessionType;
    public IEPduAddress pduAddress;

    public PduSession(EPduSessionIdentity id) {
        this.id = id;
    }
}
