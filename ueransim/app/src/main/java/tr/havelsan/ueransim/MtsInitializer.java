package tr.havelsan.ueransim;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.mts.*;
import tr.havelsan.ueransim.nas.EapDecoder;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapIdentity;
import tr.havelsan.ueransim.nas.eap.EapNotification;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class MtsInitializer {

    public static void initMts() {
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().ignoreClassVisibility().whitelistPackages(Constants.NAS_IMPL_PREFIX).scan()) {
            var classInfoList = scanResult.getAllClasses();
            for (var classInfo : classInfoList) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(classInfo.getName());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String typeName = Utils.getTypeName(clazz);
                TypeRegistry.registerTypeName(typeName, clazz);
            }
        }

        final Class<?>[] otherTypes = new Class[]{
                Eap.class,
                EapAkaPrime.class,
                EapIdentity.class,
                EapNotification.class,
                EapAkaPrime.EAttributeType.class,
                EapAkaPrime.ESubType.class,
                Eap.ECode.class,
                Eap.EEapType.class
        };

        for (var type : otherTypes) {
            TypeRegistry.registerTypeName(type.getSimpleName(), type);
        }

        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());
        TypeRegistry.registerCustomType(new MtsIEEapMessage());
        TypeRegistry.registerCustomType(new MtsEapAkaAttributes());

        MtsDecoder.setFileProvider((searchDir, path) -> {
            try {
                String content = Files.readString(Paths.get(searchDir, path));
                if (path.endsWith(".yaml"))
                    content = Utils.convertYamlToJson(content);
                return content;
            } catch (NoSuchFileException e) {
                throw new MtsException("file not found: %s", e.getFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

class MtsEapAkaAttributes implements TypeRegistry.ICustomTypeRegistry<EapAkaPrime.Attributes> {

    @Override
    public EapAkaPrime.Attributes construct(Class<EapAkaPrime.Attributes> type, Map<String, Object> args) {
        var attr = new EapAkaPrime.Attributes();
        for (var entry : args.entrySet()) {
            var key = EapAkaPrime.EAttributeType.fromIdentifier(EapAkaPrime.EAttributeType.class, entry.getKey());
            if (key == null) {
                throw new MtsException("key not found for EapAkaPrime.Attributes: %s", entry.getKey());
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
    public Class<EapAkaPrime.Attributes> getRegisteringClass() {
        return EapAkaPrime.Attributes.class;
    }
}

class MtsIEEapMessage implements TypeRegistry.ICustomTypeRegistry<IEEapMessage> {

    @Override
    public IEEapMessage construct(Class<IEEapMessage> type, Map<String, Object> args) {
        if (args.size() == 1 && args.containsKey("payload")) {
            var payload = args.get("payload");
            if (payload == null)
                throw new MtsException("payload cannot be null");
            if (!(payload instanceof String))
                throw new MtsException("payload must be a string");

            var payloadString = (String) payload;
            if (payloadString.length() == 0)
                throw new MtsException("payload cannot be empty");

            String hex;
            try {
                hex = new String(Base64.getDecoder().decode((payloadString)));
            } catch (Exception e) {
                throw new MtsException("payload is not a valid base64 string");
            }

            byte[] bytes;
            try {
                bytes = Utils.hexStringToByteArray(hex);
            } catch (Exception e) {
                throw new MtsException("decoded payload base64 is not a valid hex string");
            }

            return new IEEapMessage(EapDecoder.eapPdu(new OctetInputStream(bytes)));
        }

        return MtsConstruct.construct(type, args, false);
    }

    @Override
    public boolean isConvertable(Class<?> from, Class<?> to) {
        return false;
    }

    @Override
    public void convert(Object from, Class<?> to, List<Conversion<?>> list, int depth) {
    }

    @Override
    public Class<IEEapMessage> getRegisteringClass() {
        return IEEapMessage.class;
    }
}

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
