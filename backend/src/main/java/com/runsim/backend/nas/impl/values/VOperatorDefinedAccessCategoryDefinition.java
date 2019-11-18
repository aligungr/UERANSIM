package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.impl.enums.EPresenceOfStandardizedAccessCategory;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit5;
import com.runsim.backend.utils.octets.OctetString;

public class VOperatorDefinedAccessCategoryDefinition extends NasValue {
    public int precedence;
    public Bit5 operatorDefinedAccessCategoryNumber;
    public EPresenceOfStandardizedAccessCategory psac;
    public OctetString criteria;
    public Bit5 standardizedAccessCategory;

    public static VOperatorDefinedAccessCategoryDefinition decode(OctetInputStream stream) {
        var res = new VOperatorDefinedAccessCategoryDefinition();

        int length = stream.readOctetI();
        res.precedence = stream.readOctetI();

        int octet = stream.readOctetI();
        res.operatorDefinedAccessCategoryNumber = new Bit5(octet);
        res.psac = EPresenceOfStandardizedAccessCategory.fromValue(octet >> 7);

        int lengthOfCriteria = stream.readOctetI();
        res.criteria = stream.readOctetString(lengthOfCriteria);

        res.standardizedAccessCategory = new Bit5(stream.readOctetI());
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        int totalLength = 4 + criteria.length;
        stream.writeOctet(totalLength);
        stream.writeOctet(precedence);

        int octet = psac.intValue();
        octet <<= 7;
        octet |= operatorDefinedAccessCategoryNumber.intValue();
        stream.writeOctet(octet);

        stream.writeOctet(criteria.length);
        stream.writeOctetString(criteria);

        stream.writeOctet(standardizedAccessCategory.intValue());
    }
}
