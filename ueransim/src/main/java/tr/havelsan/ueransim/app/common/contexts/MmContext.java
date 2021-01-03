/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.enums.ECmState;
import tr.havelsan.ueransim.app.common.enums.EMmState;
import tr.havelsan.ueransim.app.common.enums.EMmSubState;
import tr.havelsan.ueransim.app.common.enums.ERmState;
import tr.havelsan.ueransim.nas.impl.ies.IE5gGutiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsTrackingAreaIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsTrackingAreaIdentityList;
import tr.havelsan.ueransim.nas.impl.ies.IESuciMobileIdentity;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;

public class MmContext {

    public ERmState rmState;
    public ECmState cmState;
    public EMmState mmState;
    public EMmSubState mmSubState;

    public RegistrationRequest registrationRequest;

    public IESuciMobileIdentity storedSuci;
    public IE5gGutiMobileIdentity storedGuti;

    public IE5gsTrackingAreaIdentity lastVisitedRegisteredTai;
    public IE5gsTrackingAreaIdentityList taiList;

    public long lastPlmnSearchTrigger;

    public MmContext() {
        this.rmState = ERmState.RM_DEREGISTERED;
        this.cmState = ECmState.CM_IDLE;
        this.mmState = EMmState.MM_NULL;
        this.mmSubState = EMmSubState.MM_NULL__NA;
    }
}
