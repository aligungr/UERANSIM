package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;


/**
 * A generic TokenBucket.
 * It allows to try to consume tokens and to upgrade the capacity.
 */
public interface TokenBucket {
    /**
     * It tries to consume a specific number of tokens.
     * @param numberTokens the number of tokens to consume.
     * @return true if ther is enought tokens, false otherwise.
     */
    boolean tryConsume(int numberTokens);

}
