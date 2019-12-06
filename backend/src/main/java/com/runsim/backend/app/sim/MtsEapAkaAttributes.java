package com.runsim.backend.app.sim;

import com.runsim.backend.exceptions.MtsException;
import com.runsim.backend.mts.*;
import com.runsim.backend.nas.eap.EEapAkaAttributeType;
import com.runsim.backend.nas.eap.EapAkaAttributes;
import com.runsim.backend.utils.octets.OctetString;

import java.util.List;
import java.util.Map;

public class MtsEapAkaAttributes implements TypeRegistry.ICustomTypeRegistry<EapAkaAttributes> {

    @Override
    public EapAkaAttributes construct(Class<EapAkaAttributes> type, Map<String, Object> args) {
        var attr = new EapAkaAttributes();
        for (var entry : args.entrySet()) {
            var key = EEapAkaAttributeType.fromIdentifier(EEapAkaAttributeType.class, entry.getKey());
            if (key == null) {
                throw new MtsException("key not found for EapAkaAttributes: %s", entry.getKey());
            }
            var value = entry.getValue();

            if (value instanceof OctetString) {
                attr.getAttributes().put(key, (OctetString) value);
            } else if (value instanceof String) {
                attr.getAttributes().put(key, new OctetString((String) value));
            } else if (value instanceof ImplicitTypedObject) {
                var constructed = MtsConstruct.construct(OctetString.class, ((ImplicitTypedObject) value).getParameters(), true);
                attr.getAttributes().put(key, constructed);
            } else {
                var conversions = MtsConvert.convert(value, OctetString.class, true);
                if (conversions.size() == 0)
                    throw new MtsException("no conversion found for types %s and %s", value.getClass().getSimpleName(), OctetString.class.getSimpleName());
                if (conversions.size() > 1)
                    throw new MtsException("multiple conversion found for types %s and %s", value.getClass().getSimpleName(), OctetString.class.getSimpleName());
                attr.getAttributes().put(key, (OctetString) conversions.get(0).value);
            }
        }
        return attr;
    }

    @Override
    public boolean isConvertable(Class<?> from, Class<?> to) {
        return false;
    }

    @Override
    public void convert(Object from, Class<?> to, List<Conversion<?>> list, int depth) {

    }

    @Override
    public Class<EapAkaAttributes> getRegisteringClass() {
        return EapAkaAttributes.class;
    }
}
