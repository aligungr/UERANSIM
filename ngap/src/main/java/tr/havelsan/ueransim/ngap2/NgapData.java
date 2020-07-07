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

package tr.havelsan.ueransim.ngap2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import tr.havelsan.ueransim.utils.Utils;

import java.util.HashMap;

public class NgapData {

    private static HashMap<String, Integer> procedureCodeIds;
    private static HashMap<String, Integer> ieIds;
    private static HashMap<String, PduContent> pduContents;
    private static HashMap<String, ProcedureInfo> procedures;

    static {
        initialize();
    }

    private static void initialize() {
        var gson = new Gson();

        Runnable readNgapConstants = () -> {
            var json = gson.fromJson(Utils.getResourceString("ngap_constants.json"), JsonObject.class);
            var procedureCode = json.get("id-procedureCode").getAsJsonArray();
            var ieId = json.get("id-informationElement").getAsJsonArray();

            procedureCodeIds = new HashMap<>();
            for (int i = 0; i < procedureCode.size(); i++) {
                var obj = procedureCode.get(i).getAsJsonObject();
                procedureCodeIds.put(obj.get("name").getAsString(), obj.get("value").getAsInt());
            }

            ieIds = new HashMap<>();
            for (int i = 0; i < ieId.size(); i++) {
                var obj = ieId.get(i).getAsJsonObject();
                ieIds.put(obj.get("name").getAsString(), obj.get("value").getAsInt());
            }
        };

        Runnable readNgapPduContents = () -> {
            var json = gson.fromJson(Utils.getResourceString("ngap_pduContents.json"), PduContent[].class);
            pduContents = new HashMap<>();
            for (var item : json) {
                pduContents.put(item.name, item);
            }
        };

        Runnable readNgapPduDescriptions = () -> {
            var json = gson.fromJson(Utils.getResourceString("ngap_pduDescriptions.json"), JsonObject.class);
            procedures = gson.fromJson(json.get("procedures"), TypeToken.getParameterized(HashMap.class, String.class, ProcedureInfo.class).getType());
        };

        readNgapConstants.run();
        readNgapPduContents.run();
        readNgapPduDescriptions.run();
    }

    public static class PduContent {
        public final String name;
        public final boolean isPrivate;
        public final InformationElement[] ies;

        public PduContent(String name, boolean isPrivate, InformationElement[] ies) {
            this.name = name;
            this.isPrivate = isPrivate;
            this.ies = ies;
        }

        public static class InformationElement {
            public final String id;
            public final int criticality;
            public final String type;
            public final int presence;

            public InformationElement(String id, int criticality, String type, int presence) {
                this.id = id;
                this.criticality = criticality;
                this.type = type;
                this.presence = presence;
            }
        }
    }

    public static class ProcedureInfo {
        public final String initiatingMessage;
        public final String successfulOutcome;
        public final String unsuccessfulOutcome;
        public final String procedureCode;
        public final String criticality;

        public ProcedureInfo(String initiatingMessage, String successfulOutcome, String unsuccessfulOutcome, String procedureCode, String criticality) {
            this.initiatingMessage = initiatingMessage;
            this.successfulOutcome = successfulOutcome;
            this.unsuccessfulOutcome = unsuccessfulOutcome;
            this.procedureCode = procedureCode;
            this.criticality = criticality;
        }
    }
}
