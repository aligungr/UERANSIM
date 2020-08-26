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
 */

package tr.havelsan.ueransim.app.events;

import tr.havelsan.ueransim.app.events.ue.UeCommandEvent;
import tr.havelsan.ueransim.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class EventParser {

    public static String[] possibleEvents() {
        return new ArrayList<>(Utils.streamToList(Arrays.stream(UeCommandEvent.Command.values()).map(x -> x.cmd)))
                .toArray(new String[0]);
    }

    public static BaseEvent parse(String command) {
        var ueCmd = UeCommandEvent.Command.fromValue(command);
        if (ueCmd == null)
            return null;
        return new UeCommandEvent(ueCmd);
    }
}
