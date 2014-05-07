package com.github.toastshaman.dropwizard.auth.jwt.hmac;

import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.google.common.base.Joiner;

import javax.crypto.Mac;

import static com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenUtils.*;
import static com.google.common.base.Preconditions.checkNotNull;

public class HmacSigner {

    private final Mac hmac;

    public HmacSigner(Mac hmac) { this.hmac = hmac; }

    public String sign(JsonWebToken token) {
        checkNotNull(token);

        final String payload = payloadOf(token);
        final String signature = toBase64(sign(bytesOf(payload)));
        return Joiner.on(".").join(payload, signature);
    }

    private byte[] sign(byte[] input) { return hmac.doFinal(input); }
}
