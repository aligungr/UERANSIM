package tr.havelsan.ueransim.app.common.testcmd;

public class TestCmd_Ping extends TestCmd {
    public final String address;
    public final int timeoutSec;

    public TestCmd_Ping(String address) {
        this(address, 0);
    }

    public TestCmd_Ping(String address, int timeoutSec) {
        this.address = address;
        this.timeoutSec = timeoutSec;
    }
}
