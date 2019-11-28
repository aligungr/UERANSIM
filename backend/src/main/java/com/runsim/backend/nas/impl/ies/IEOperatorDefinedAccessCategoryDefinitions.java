package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.bits.Bit5;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.OctetString;

import java.util.List;

public class IEOperatorDefinedAccessCategoryDefinitions extends InformationElement6 {
    public List<VOperatorDefinedAccessCategoryDefinition> operatorDefinedAccessCategoryDefinitions;

    @Override
    protected IEOperatorDefinedAccessCategoryDefinitions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEOperatorDefinedAccessCategoryDefinitions();
        res.operatorDefinedAccessCategoryDefinitions = Utils.decodeList(stream, new VOperatorDefinedAccessCategoryDefinition()::decode, length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        operatorDefinedAccessCategoryDefinitions.forEach(item -> item.encode(stream));
    }

    public static class VOperatorDefinedAccessCategoryDefinition extends NasValue {
        public Octet precedence;
        public Bit5 operatorDefinedAccessCategoryNumber;
        public EPresenceOfStandardizedAccessCategory psac;
        public OctetString criteria;
        public Bit5 standardizedAccessCategory;

        @Override
        public VOperatorDefinedAccessCategoryDefinition decode(OctetInputStream stream) {
            var res = new VOperatorDefinedAccessCategoryDefinition();

            int length = stream.readOctetI();
            res.precedence = stream.readOctet();

            int octet = stream.readOctetI();
            res.operatorDefinedAccessCategoryNumber = new Bit5(octet);
            res.psac = EPresenceOfStandardizedAccessCategory.fromValue(octet >> 7 & 0b1);

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

        public static class EPresenceOfStandardizedAccessCategory extends ProtocolEnum {
            public static final EPresenceOfStandardizedAccessCategory NOT_INCLUDED
                    = new EPresenceOfStandardizedAccessCategory(0b0, "Standardized access category field is not included");
            public static final EPresenceOfStandardizedAccessCategory INCLUDED
                    = new EPresenceOfStandardizedAccessCategory(0b1, "Standardized access category field is included");

            private EPresenceOfStandardizedAccessCategory(int value, String name) {
                super(value, name);
            }

            public static EPresenceOfStandardizedAccessCategory fromValue(int value) {
                return fromValueGeneric(EPresenceOfStandardizedAccessCategory.class, value, null);
            }
        }
    }
}
