package tr.havelsan.ueransim.app.gnb.gtp.ratelimiter;

import java.util.Optional;

public class TokenBucketBuilder {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static TokenBucket createTokenBucket(Optional<Long> capacity) {
        return capacity.isPresent() ? new SimpleTokenBucket(capacity.get()) : new FakeTokenBucket();
    }
}
