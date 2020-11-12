package tr.havelsan.ueransim.app.common.testcmd;

public class TestCmd_Ping extends TestCmd {
    public final String address;
    public final int count;
    public final int timeoutSec;

    public TestCmd_Ping(String address, int count, int timeoutSec) {
        this.address = address;
        this.count = count;
        this.timeoutSec = timeoutSec;
    }
}
