package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

/**
 * It represents a fake TokenBucket. It's an infinite bucket.
 *
 */
class FakeTokenBucket implements TokenBucket{

    @Override
    public boolean tryConsume(int numberTokens) {
        return true;
    }

}
