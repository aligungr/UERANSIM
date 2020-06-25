/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim;

import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsConstruct;
import tr.havelsan.ueransim.mts.MtsDecoder;
import tr.havelsan.ueransim.mts.MtsInitializer;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.FlowScanner;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class FlowTesting {

    public static void main(String[] args) throws Exception {
        MtsInitializer.initMts();

        var scanner = new Scanner(System.in);

        var config = new LinkedHashMap<String, String>();
        var configYaml = (ImplicitTypedObject) MtsDecoder.decode("config.yaml");
        for (var e : configYaml.getParameters().entrySet()) {
            config.put(e.getKey(), String.valueOf(e.getValue()));
        }

        var types = new LinkedHashMap<String, Class<? extends BaseFlow>>();
        var typeNames = new ArrayList<String>();
        for (String fn : FlowScanner.getFlowNames()) {
            var type = FlowScanner.getFlowType(fn);
            types.put(fn, type);
            typeNames.add(fn);
        }

        var configOrder = new HashMap<String, Integer>();
        for (var entry : config.entrySet()) {
            String key = entry.getKey();
            if (key.matches("^input\\.[a-zA-Z]+$")) {
                configOrder.put(key.substring("input.".length()), configOrder.size());
            }
        }

        typeNames.sort((string1, string2) -> {
            Integer i1 = configOrder.get(string1);
            Integer i2 = configOrder.get(string2);
            if (i1 == null && i2 == null) return 0;
            if (i1 == null) return 1;
            if (i2 == null) return -1;
            return i1.compareTo(i2);
        });

        var simContext = UeRanSim.createSimContext(configYaml);

        simContext.sctpClient.start();

        while (true) {
            Console.printDiv();

            if (!simContext.sctpClient.isOpen())
                break;

            Console.println(Color.BLUE, "Select a flow:");
            Console.print(Color.BLUE, "0) ");
            Console.println(null, "Close connection");
            for (int i = 0; i < typeNames.size(); i++) {
                Console.print(Color.BLUE, i + 1 + ") ");
                Console.println(null, typeNames.get(i));
            }
            Console.print(Color.BLUE, "Selection: ");

            int selection;
            try {
                selection = scanner.nextInt();
            } catch (Exception e) {
                Console.println(Color.YELLOW, "Invalid selection");
                continue;
            }
            scanner.nextLine();
            Console.println();

            if (selection == 0) {
                simContext.sctpClient.close();
                break;
            }

            if (selection < 1 || selection - 1 >= typeNames.size()) {
                Console.println(Color.YELLOW, "Invalid selection: " + selection);
                continue;
            }

            var selectedType = types.get(typeNames.get(selection - 1));
            var ctor = findConstructor(selectedType);
            var inputType = ctor.getParameterCount() > 1 ? ctor.getParameterTypes()[1] : null;

            if (inputType != null) {
                String key = "flows." + typeNames.get(selection - 1);
                ctor.newInstance(simContext, readInputFile(key, "" + config.get(key), inputType))
                        .start();
            } else {
                ctor.newInstance(simContext)
                        .start();
            }
        }
    }

    private static Constructor<BaseFlow> findConstructor(Class<? extends BaseFlow> selectedType) {
        if (selectedType.getDeclaredConstructors().length != 1)
            throw new RuntimeException("zero or multiple constructor found for selected flow");
        return (Constructor<BaseFlow>) selectedType.getDeclaredConstructors()[0];
    }

    private static <T> T readInputFile(String key, String path, Class<T> type) {
        if (path == null || path.length() == 0)
            throw new RuntimeException("please specify flow input file (" + key + ")");
        var inp = MtsDecoder.decode(path);
        return MtsConstruct.construct(type, (ImplicitTypedObject) inp, true);
    }
}
