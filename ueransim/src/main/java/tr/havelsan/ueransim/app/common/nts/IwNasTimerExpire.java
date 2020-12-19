/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.ue.nas.NasTimer;

public class IwNasTimerExpire {
    public final NasTimer timer;

    public IwNasTimerExpire(NasTimer timer) {
        this.timer = timer;
    }
}
