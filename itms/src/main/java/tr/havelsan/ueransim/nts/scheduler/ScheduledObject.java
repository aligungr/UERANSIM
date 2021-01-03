/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nts.scheduler;

public class ScheduledObject {
    public final int arg0;
    public final int arg1;
    public final Object obj;

    public ScheduledObject(int arg0, int arg1, Object obj) {
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.obj = obj;
    }
}
