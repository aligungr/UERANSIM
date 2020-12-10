package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

/**
 * It represents a simple TokenBucket. In order to save resources is a passive entity: it computes refill tokens number
 * based on the time elapsed between the call to the tryConsume method and the next. In this implementation a token
 * corresponds to a Byte.
 * https://github.com/vladimir-bukhtoyarov/bucket4j/blob/master/doc-pages/token-bucket-brief-overview.md
 */
public interface TokenBucket {
    /**
     * It tries to consume a specific number of tokens.
     * @param numberTokens the number of tokens to consume.
     * @return true if ther is enought tokens, false otherwise.
     */
    boolean tryConsume(int numberTokens);

    /**
     * Update the capacity of the bucket.
     * @param newByteCapacity The new capacity in Byte (i.e the new AMBR treshold).
     */
    void updateCapacity(long newByteCapacity);

}
