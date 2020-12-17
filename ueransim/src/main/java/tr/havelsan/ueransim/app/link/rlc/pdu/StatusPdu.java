package tr.havelsan.ueransim.app.link.rlc.pdu;

import tr.havelsan.ueransim.app.link.rlc.utils.NackBlock;

import java.util.ArrayList;
import java.util.List;

public class StatusPdu {
    public int ackSn;
    public List<NackBlock> nackBlocks = new ArrayList<>();
}
