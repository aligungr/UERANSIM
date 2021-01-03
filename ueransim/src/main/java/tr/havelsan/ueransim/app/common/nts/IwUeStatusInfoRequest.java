/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.common.info.UeStatusInfo;
import tr.havelsan.ueransim.nts.nts.NtsTask;

import java.util.function.Consumer;

public class IwUeStatusInfoRequest {
    public final NtsTask requester;
    public final Consumer<UeStatusInfo> consumerFunc;

    public IwUeStatusInfoRequest(NtsTask requester, Consumer<UeStatusInfo> consumerFunc) {
        this.requester = requester;
        this.consumerFunc = consumerFunc;
    }
}
