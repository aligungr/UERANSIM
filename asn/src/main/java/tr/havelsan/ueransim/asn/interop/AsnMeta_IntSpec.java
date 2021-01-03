/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.asn.interop;

import java.util.List;

public class AsnMeta_IntSpec {
    public boolean extensible;
    public boolean strict_enumeration;
    public int width;
    public boolean is_unsigned;
    public List<List<Object>> enums; // Pair of <string, long>
}
