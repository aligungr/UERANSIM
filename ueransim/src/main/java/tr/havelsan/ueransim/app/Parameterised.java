package tr.havelsan.ueransim.app;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import tr.havelsan.ueransim.app.sim.MtsEapAkaAttributes;
import tr.havelsan.ueransim.app.sim.MtsIEEapMessage;
import tr.havelsan.ueransim.app.sim.MtsProtocolEnumRegistry;
import tr.havelsan.ueransim.flows.RegistrationParameterised;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.TypeRegistry;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapIdentity;
import tr.havelsan.ueransim.nas.eap.EapNotification;
import tr.havelsan.ueransim.parameterised.RegistrationInput;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.Utils;

public class Parameterised {

    public static void main(String[] args) throws Exception {
        initMts();

        var registrationInput = (RegistrationInput) MtsDecoder.decode("parameter_test.json");
        Console.println(Json.toJson(registrationInput));

        new RegistrationParameterised(registrationInput).start();
    }

    private static void initMts() {
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
                Eap.EEapType.class,
                RegistrationInput.class
        };

        for (var type : otherTypes) {
            TypeRegistry.registerTypeName(type.getSimpleName(), type);
        }

        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());
        TypeRegistry.registerCustomType(new MtsIEEapMessage());
        TypeRegistry.registerCustomType(new MtsEapAkaAttributes());

        MtsDecoder.setFileProvider((searchDir, path)
                -> Utils.getResourceString(path));
    }
}
