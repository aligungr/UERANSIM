/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.ue.nas.NasTimer;

public class IwNasTimerExpire {
    public final NasTimer timer;

    public IwNasTimerExpire(NasTimer timer) {
        this.timer = timer;
    }
}
