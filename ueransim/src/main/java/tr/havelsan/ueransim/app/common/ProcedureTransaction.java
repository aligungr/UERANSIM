/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common;

public class ProcedureTransaction {

    public static final int MIN_ID = 1;
    public static final int MAX_ID = 254;

    public static final ProcedureTransaction RELEASED = new ProcedureTransaction();
}
