/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.gtp;

/**
 * It represents a simple TokenBucket. In order to save resources is a passive entity: it computes refill tokens number
 * based on the time elapsed between the call to the tryConsume method and the next. In this implementation a token
 * corresponds to a Byte.
 * https://github.com/vladimir-bukhtoyarov/bucket4j/blob/master/doc-pages/token-bucket-brief-overview.md
 */
class TokenBucket {

    private final static long REFILL_PERIOD = 1000L;
    private long byteCapacity;
    private double refillTokensPerOneMillis;

    private double availableTokens;
    private long lastRefillTimestamp;

    /**
     * Constructor method.
     *
     * @param byteCapacity The capacity in bit of the bucket (i.e the AMBR treshold).
     */
    public TokenBucket(long byteCapacity) {
        this.byteCapacity = byteCapacity;
        if (byteCapacity != -1) {
            this.refillTokensPerOneMillis = (double) byteCapacity / (double) REFILL_PERIOD;
            this.availableTokens = byteCapacity;
            this.lastRefillTimestamp = System.currentTimeMillis();
        }
    }

    /**
     * It tries to consume a specific number of tokens.
     *
     * @param numberTokens the number of tokens to consume.
     * @return true if ther is enought tokens, false otherwise.
     */
    public boolean tryConsume(long numberTokens) {
        if (byteCapacity > 0) {
            refill();
            if (availableTokens < numberTokens) {
                return false;
            } else {
                availableTokens -= numberTokens;
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Update the capacity of the bucket.
     *
     * @param newByteCapacity The new capacity in Byte (i.e the new AMBR treshold).
     */
    public void updateCapacity(long newByteCapacity) {
        this.byteCapacity = newByteCapacity;
        if (newByteCapacity != -1) {
            this.refillTokensPerOneMillis = (double) byteCapacity / (double) REFILL_PERIOD;
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
            this.availableTokens = Math.min(byteCapacity, availableTokens + refill);
            this.lastRefillTimestamp = currentTimeMillis;
        }
    }
}