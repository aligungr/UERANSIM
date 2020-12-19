/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.common.info.GnbStatusInfo;
import tr.havelsan.ueransim.nts.nts.NtsTask;

import java.util.function.Consumer;

public class IwGnbStatusInfoRequest {
    public final NtsTask requester;
    public final Consumer<GnbStatusInfo> consumerFunc;

    public IwGnbStatusInfoRequest(NtsTask requester, Consumer<GnbStatusInfo> consumerFunc) {
        this.requester = requester;
        this.consumerFunc = consumerFunc;
    }
}
