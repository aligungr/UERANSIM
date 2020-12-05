/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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
