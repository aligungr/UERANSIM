package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

class SimpleTokenBucket implements TokenBucket {

    private final static long REFILL_PERIOD = 1000L;
    private long capacity;
    private final double refillTokensPerOneMillis;

    private double availableTokens;
    private long lastRefillTimestamp;

    /**
     * Constructor method.
     * @param byteCapacity The capacity in Byte of the bucket (i.e the AMBR treshold).
     */
    public SimpleTokenBucket(long byteCapacity) {
        this.capacity = byteCapacity;
        this.refillTokensPerOneMillis = (double) byteCapacity / (double) REFILL_PERIOD;
        this.availableTokens = byteCapacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    @Override
    public boolean tryConsume(int numberTokens) {
        refill();
        if (availableTokens < numberTokens) {
            return false;
        } else {
            availableTokens -= numberTokens;
            return true;
        }
    }

    @Override
    public void updateCapacity(long newByteCapacity) {
        this.capacity = newByteCapacity;
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