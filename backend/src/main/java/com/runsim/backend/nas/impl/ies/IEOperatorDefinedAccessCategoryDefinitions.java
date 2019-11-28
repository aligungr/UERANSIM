package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.values.VOperatorDefinedAccessCategoryDefinition;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IEOperatorDefinedAccessCategoryDefinitions extends InformationElement6 {
    public List<VOperatorDefinedAccessCategoryDefinition> operatorDefinedAccessCategoryDefinitions;

    @Override
    protected IEOperatorDefinedAccessCategoryDefinitions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEOperatorDefinedAccessCategoryDefinitions();
        res.operatorDefinedAccessCategoryDefinitions = Utils.decodeList(stream, VOperatorDefinedAccessCategoryDefinition::decode, length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        operatorDefinedAccessCategoryDefinitions.forEach(item -> item.encode(stream));
    }
}
