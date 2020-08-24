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

package tr.havelsan.ueransim.app.events.ue;

public class UeCommandEvent extends UeEvent {
    public final Command cmd;

    public UeCommandEvent(Command cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "UeCommandEvent{" +
                "cmd='" + cmd + '\'' +
                '}';
    }

    public enum Command {
        INITIAL_REGISTRATION("initial-registration"),
        PERIODIC_REGISTRATION("periodic-registration"),
        DEREGISTRATION("de-registration"),
        PDU_SESSION_ESTABLISHMENT("pdu-session-establishment");

        public final String cmd;

        Command(String cmd) {
            this.cmd = cmd;
        }

        public static Command fromValue(String cmd) {
            for (var item : values()) {
                if (item.cmd.equals(cmd))
                    return item;
            }
            return null;
        }

        public boolean isMmCommand() {
            switch (this) {
                case INITIAL_REGISTRATION:
                case PERIODIC_REGISTRATION:
                case DEREGISTRATION:
                    return true;
                default:
                    return false;
            }
        }
    }
}
