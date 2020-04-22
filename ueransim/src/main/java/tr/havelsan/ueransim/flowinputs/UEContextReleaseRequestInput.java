package tr.havelsan.ueransim.flowinputs;

public class UEContextReleaseRequestInput {
    public final long ranUeNgapId;
    public final long amfUeNgapId;

    public UEContextReleaseRequestInput(long ranUeNgapId, long amfUeNgapId) {
        this.ranUeNgapId = ranUeNgapId;
        this.amfUeNgapId = amfUeNgapId;
    }
}
