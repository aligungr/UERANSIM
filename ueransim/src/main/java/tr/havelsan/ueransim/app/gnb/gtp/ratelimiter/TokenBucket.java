package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

public class TokenBucket {

    private final static long REFILL_PERIOD = 1000L;
    private final long capacity;
    private final double refillTokensPerOneMillis;

    private double availableTokens;
    private long lastRefillTimestamp;


    public TokenBucket(long capacity) {
        this.capacity = capacity;
        this.refillTokensPerOneMillis = (double) capacity / (double) REFILL_PERIOD;
        this.availableTokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public boolean tryConsume(int numberTokens) {
        refill();
        if (availableTokens < numberTokens) {
            return false;
        } else {
            availableTokens -= numberTokens;
            return true;
        }
    }

    private void refill() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > lastRefillTimestamp) {
            long millisSinceLastRefill = currentTimeMillis - lastRefillTimestamp;
            double refill = millisSinceLastRefill * refillTokensPerOneMillis;
            this.availableTokens = Math.min(capacity, availableTokens + refill);
            this.lastRefillTimestamp = currentTimeMillis;
        }
    }

}