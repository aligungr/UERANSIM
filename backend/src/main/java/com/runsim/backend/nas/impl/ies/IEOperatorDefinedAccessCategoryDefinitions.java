package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.values.VOperatorDefinedAccessCategoryDefinition;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;

public class IEOperatorDefinedAccessCategoryDefinitions extends InformationElement6 {
    public List<VOperatorDefinedAccessCategoryDefinition> operatorDefinedAccessCategoryDefinitions;

    @Override
    protected IEOperatorDefinedAccessCategoryDefinitions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEOperatorDefinedAccessCategoryDefinitions();
        res.operatorDefinedAccessCategoryDefinitions = new ArrayList<>();

        int startIndex = stream.currentIndex();
        while (stream.currentIndex() - startIndex < length) {
            res.operatorDefinedAccessCategoryDefinitions.add(VOperatorDefinedAccessCategoryDefinition.decode(stream));
        }

        if (stream.currentIndex() - startIndex > length) {
            throw new DecodingException("ie length exceeds the given length");
        }

        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        for (var operatorDefinedAccessCategoryDefinition : operatorDefinedAccessCategoryDefinitions) {
            operatorDefinedAccessCategoryDefinition.encode(stream);
        }
    }
}
