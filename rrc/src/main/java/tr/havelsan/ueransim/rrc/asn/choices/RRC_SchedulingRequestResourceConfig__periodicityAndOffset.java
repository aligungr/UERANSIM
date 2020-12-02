/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;

public class RRC_SchedulingRequestResourceConfig__periodicityAndOffset extends RRC_Choice {

    public RRC_Null sym2;
    public RRC_Null sym6or7;
    public RRC_Null sl1;
    public RRC_Integer sl2;
    public RRC_Integer sl4;
    public RRC_Integer sl5;
    public RRC_Integer sl8;
    public RRC_Integer sl10;
    public RRC_Integer sl16;
    public RRC_Integer sl20;
    public RRC_Integer sl40;
    public RRC_Integer sl80;
    public RRC_Integer sl160;
    public RRC_Integer sl320;
    public RRC_Integer sl640;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sym2","sym6or7","sl1","sl2","sl4","sl5","sl8","sl10","sl16","sl20","sl40","sl80","sl160","sl320","sl640" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sym2","sym6or7","sl1","sl2","sl4","sl5","sl8","sl10","sl16","sl20","sl40","sl80","sl160","sl320","sl640" };
    }

}
