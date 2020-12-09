package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

/**
 * It represents a simple TokenBucket. In order to save resources is a passive entity: it computes refill tokens number
 * based on the time elapsed between the call to the tryConsume method and the next. In this implementation a token
 * corresponds to a Byte.
 * https://github.com/vladimir-bukhtoyarov/bucket4j/blob/master/doc-pages/token-bucket-brief-overview.md
 */
public class TokenBucket {

    private final static long REFILL_PERIOD = 1000L;
    private final long capacity;
    private final double refillTokensPerOneMillis;

    private double availableTokens;
    private long lastRefillTimestamp;

    /**
     * Constructor method.
     * @param byteCapacity The capacity in Byte of the bucket (i.e the AMBR treshold).
     */
    public TokenBucket(long byteCapacity) {
        this.capacity = byteCapacity;
        this.refillTokensPerOneMillis = (double) byteCapacity / (double) REFILL_PERIOD;
        this.availableTokens = byteCapacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }


    /**
     * It tries to consume a specific number of tokens.
     * @param numberTokens the number of tokens to consume.
     * @return true if ther is enought tokens, false otherwise.
     */
    public boolean tryConsume(int numberTokens) {
        refill();
        if (availableTokens < numberTokens) {
            return false;
        } else {
            availableTokens -= numberTokens;
            return true;
        }
    }

    /*
     * Computes refill tokens number.
     */
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