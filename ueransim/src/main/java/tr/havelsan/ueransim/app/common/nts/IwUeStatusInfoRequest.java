/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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
