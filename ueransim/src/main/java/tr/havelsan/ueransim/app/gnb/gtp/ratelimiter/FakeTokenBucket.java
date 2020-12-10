package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

public class FakeTokenBucket implements TokenBucket{

    @Override
    public boolean tryConsume(int numberTokens) {
        return true;
    }

    @Override
    public void updateCapacity(long newByteCapacity) {
        //Ignored.
    }
}
