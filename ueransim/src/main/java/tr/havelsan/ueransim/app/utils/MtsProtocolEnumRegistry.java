/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.utils;

import tr.havelsan.ueransim.mts.*;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;

import java.util.List;
import java.util.Map;

class MtsProtocolEnumRegistry implements TypeRegistry.ICustomTypeRegistry<ProtocolEnum> {

    @Override
    public ProtocolEnum construct(Class<ProtocolEnum> type, Map<String, Object> args) {
        String identifier = null;
        String name = null;
        Integer value = null;

        if (args.containsKey("identifier")) {
            identifier = args.get("identifier").toString();
        }
        if (args.containsKey("name")) {
            name = args.get("name").toString();
        }
        if (args.containsKey("value")) {
            var obj = args.get("value");
            if (obj == null || !Traits.isNumber(obj.getClass()) // && !Traits.isNumberIfString(obj)
            )
                throw new MtsException("invalid value type for enum %s, it must be an int", type.getSimpleName());
            value = new NumberInfo(obj.toString()).intValue();
        }

        if (identifier == null && name == null && value == null) {
            throw new MtsException("specify identifier, name, or value for enum %s", type.getSimpleName());
        }

        if (identifier != null) {
            if (name != null || value != null)
                throw new MtsException("specify exactly one of the following properties: identifier, name, or value for enum %s", type.getSimpleName());

            var res = ProtocolEnum.fromIdentifier((Class<? extends ProtocolEnum>) type, identifier);
            if (res == null)
                throw new MtsException("value not found for enum %s with identifier %s", type.getSimpleName(), identifier);
            return res;
        }

        if (value != null) {
            if (name != null)
                throw new MtsException("specify exactly one of the following properties: identifier, name, or value for enum %s", type.getSimpleName());

            var res = ProtocolEnum.fromIntValue((Class<? extends ProtocolEnum>) type, value);
            if (res == null)
                throw new MtsException("value not found for enum %s with value %d", type.getSimpleName(), value);
            return res;
        }

        var res = ProtocolEnum.fromName((Class<? extends ProtocolEnum>) type, name);
        if (res.size() == 0)
            throw new MtsException("value not found for enum %s with name %s", type.getSimpleName(), name);
        if (res.size() > 1)
            throw new MtsException("multiple values matched for enum %s with name %s", type.getSimpleName(), name);
        return res.get(0);
    }

    @Override
    public boolean isConvertable(Class<?> from, Class<?> to) {
        return ProtocolEnum.class.isAssignableFrom(to) && Traits.isNumberOrString(from);
    }

    @Override
    public void convert(Object from, Class<?> to, List<Conversion<?>> list, int depth) {
        if (Traits.isNumberOrString(from.getClass())) {
            if (Traits.isString(from.getClass())) {
                var fromIdentifier = ProtocolEnum.fromIdentifier((Class<? extends ProtocolEnum>) to, (String) from);
                var fromNames = ProtocolEnum.fromName((Class<? extends ProtocolEnum>) to, (String) from);

                if (fromIdentifier == null && fromNames.size() == 0) {
                    throw new MtsException("invalid value for enum %s", to.getSimpleName());
                }
                if (fromIdentifier == null) {
                    if (fromNames.size() > 1) {
                        throw new MtsException("multiple values matched for enum %s", to.getSimpleName());
                    } else {
                        list.add(new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, fromNames.get(0), depth));
                    }
                } else {
                    list.add(new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, fromIdentifier, depth));
                }
            } else {
                var numberInfo = new NumberInfo(from.toString());
                var res = ProtocolEnum.fromIntValue((Class<? extends ProtocolEnum>) to, numberInfo.intValue());
                list.add(new Conversion<>(numberInfo.isInt() ? ConversionLevel.ASSIGNABLE_TYPE : ConversionLevel.NUMERIC_CONVERSION, res, depth));
            }
        } else {
            // bad type for protocol enum
        }
    }

    @Override
    public Class<ProtocolEnum> getRegisteringClass() {
        return ProtocolEnum.class;
    }
}
