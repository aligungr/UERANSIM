#ifndef _COMPACT_X25519_H
#define _COMPACT_X25519_H

#ifndef COMPACT_DISABLE_X25519
/* 
 X25519 is a DH key exchange over Curve25519.
 
 Two parties can share their public keys over an open channel and agree upon 
 a shared secret, that a third party cannot derive.
 
 Security concern: if a third party can intercept and change your connection in 
 some way, then X25519 is not enough. The third party could for example run 
 two X25519 sessions, one between themselfs and you, and another one between 
 themselfs and the server. X25519 has no protection against this.
 
 A possible solution is to use Ed25519 to sign the public keys exchanged, with a 
 special set of keys that is known before hand. Depending on your scenario, this 
 either means embedding a set of long-term keys in your application or setting up 
 some kind of Public Key Infrastructure (PKI).
 
 If that sounds to complicated or would not work for your case, checkout 
 the Noise framework. It is a bit less compact than X25519 + Ed25519 but much 
 more flexible in the kind of session setup styles.
*/

#include <stddef.h>
#include <stdint.h>

#define X25519_KEY_SIZE (32)
#define X25519_SHARED_SIZE (32)

/*
 Calculate public & private keypair for X25519 key exchange. 
 Never transmit the private_key!
 
 input:
     - random_seed = random bytes that need to be filled from a good source of 
         random entropy, PLEASE research the options on your platform!
 
 output:
     - private_key = resulting private key, never share this with the other party
     - public_key = public key to be shared with the other party
*/
void compact_x25519_keygen(
    uint8_t private_key[X25519_KEY_SIZE], 
    uint8_t public_key[X25519_KEY_SIZE],
    uint8_t random_seed[X25519_KEY_SIZE]
);

/*
 Calculate shared secret based on the others side public key.
 The shared secret does not have a uniform distribution so common advice 
 is to avoid using it directly as a key. You can use 
 compact_x25519_derive_encryption_key to get a more suitable encryption 
 key out of this shared secret
 
 input:
     - my_private_key = private key that came out of compact_x25519_keygen
     - their_public_key = public key that was shared by the other party
 
 output:
     - shared_secret = X25519_SHARED_SIZE bytes that both sides now share 
*/
void compact_x25519_shared(
    uint8_t shared_secret[X25519_SHARED_SIZE], 
    const uint8_t my_private_key[X25519_KEY_SIZE], 
    const uint8_t their_public_key[X25519_KEY_SIZE]
);

#ifndef COMPACT_DISABLE_X25519_DERIVE
/*
 Derive an more suitable encryption key from the shared secret.
 This is not part of the normal X25519 specification, but the specifications 
 does warn that the shared secret is less suited as symmetric encryption key.
 
 The encryption key is the result of 
 sha512(concat(shared_secret, public_key1, public_key2))[0..key_size]
 
 You have to make sure both parties agree which public key goes in first.
 
 input:
     - key_size = number between 1 and 64, of how big your encryption_key 
         needs to be, make sure there is enough room where 
         the encryption_key points to
     - shared_secret = result of compact_x25519_shared
     - public_key1 = first public key to mix in with the key
     - public_key2 = second public key to mix in with the key
 
 output:
     - encryption_key = supplied buffer is filled with a encryption key
*/
void compact_x25519_derive_encryption_key(
    uint8_t *encryption_key, 
    size_t key_size, 
    const uint8_t shared_secret[X25519_SHARED_SIZE], 
    const uint8_t public_key1[X25519_KEY_SIZE], 
    const uint8_t public_key2[X25519_KEY_SIZE]
);
#endif
#endif
#endif
