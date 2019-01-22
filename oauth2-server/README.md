# OAuth2 Authorization Server

## Generate a new key

### Generate the key

Go in the resources folder.

To generate the key :

`keytool -genkeypair -alias test -keyalg RSA -keypass password -keystore jwt.jks -storepass password`

To get the public key (for the resources servers) :

`keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey`

Exemple  output :

```text
Enter keystore password:  password # Here enter your password
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoi2DIUXb2hZMx65Fe6Kg
L+ovnFT3Xv0TJWKU0vIKdxXspMEI0LnI5DqKjqVOxzDiANVH4j+klqlPLFWBLNzi
1X4/cv2xWw9EQEFFE0csedjoP0h1j8OA9vGHKnSN6B5YrpvSzuOwUdIUbU0tEEnB
SbUcwXCIywFDmPL6ZU/0KMpLK64a11ghL5jTFJJGZS1Z0KQjXqnnAu0/nLPC+Hk3
cvTC/xQJcXhrEWLSjfwP9JJ3w3FVDAIFex9UfvteeG9lfu3EdzoKBbA6heOXkbHO
6PUGgUs2mxqhk0s+viy7B9cONjOhzj2iLHKR7S8AHsjd6e3/MJB3x2Go7zpo02DP
UwIDAQAB
-----END PUBLIC KEY-----
-----BEGIN CERTIFICATE-----
MIIDozCCAougAwIBAgIEbPdKXDANBgkqhkiG9w0BAQsFADCBgTELMAkGA1UEBhMC
RlIxDjAMBgNVBAgTBUNvcnNlMRYwFAYDVQQHEw1Qb3J0by1WZWNjaGlvMRswGQYD
VQQKExJEb3JpYW4gTWFsaXN6ZXdza2kxEDAOBgNVBAsTB1Vua25vd24xGzAZBgNV
BAMTEkRvcmlhbiBNYWxpc3pld3NraTAeFw0xOTAxMjAxODA0MTdaFw0xOTA0MjAx
ODA0MTdaMIGBMQswCQYDVQQGEwJGUjEOMAwGA1UECBMFQ29yc2UxFjAUBgNVBAcT
DVBvcnRvLVZlY2NoaW8xGzAZBgNVBAoTEkRvcmlhbiBNYWxpc3pld3NraTEQMA4G
A1UECxMHVW5rbm93bjEbMBkGA1UEAxMSRG9yaWFuIE1hbGlzemV3c2tpMIIBIjAN
BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoi2DIUXb2hZMx65Fe6KgL+ovnFT3
Xv0TJWKU0vIKdxXspMEI0LnI5DqKjqVOxzDiANVH4j+klqlPLFWBLNzi1X4/cv2x
Ww9EQEFFE0csedjoP0h1j8OA9vGHKnSN6B5YrpvSzuOwUdIUbU0tEEnBSbUcwXCI
ywFDmPL6ZU/0KMpLK64a11ghL5jTFJJGZS1Z0KQjXqnnAu0/nLPC+Hk3cvTC/xQJ
cXhrEWLSjfwP9JJ3w3FVDAIFex9UfvteeG9lfu3EdzoKBbA6heOXkbHO6PUGgUs2
mxqhk0s+viy7B9cONjOhzj2iLHKR7S8AHsjd6e3/MJB3x2Go7zpo02DPUwIDAQAB
oyEwHzAdBgNVHQ4EFgQUe0oYKiuPmzesSeTXpW2qZYFOnJUwDQYJKoZIhvcNAQEL
BQADggEBAAahSqXudi7gvNnVTVmdS+n46iDXHGb9//FE0ag9dm/wcu9sKtCE+lQl
CKYDs+9kSZhVqmbH7LyBzOBxD6Xn5v2OR0gN+8hmaGnsE4TQhzmV8xUgcs9h6gdY
JCibm2wzCrVIiRetwTmhPwrDOXqKt1lN92EN0NpSiH7qM2NB9s5JLEusOcYRiNVk
9oC+mwSflZFwlVQCnTsiQhwboQ8wms3B1vXTwN2X7KQatTwdqXj02raSJUZH80dU
UeKFW5+/CsQvvPNS5ZYcVcXNX7IVHoEaTEsTf+54HgXMDfp+v/6G0jpjt4ceiSP8
qkJteRRHnWEuR7nGW3RKqEnq6S7tlU8=
-----END CERTIFICATE-----

```

And take the public key :

```text
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoi2DIUXb2hZMx65Fe6Kg
L+ovnFT3Xv0TJWKU0vIKdxXspMEI0LnI5DqKjqVOxzDiANVH4j+klqlPLFWBLNzi
1X4/cv2xWw9EQEFFE0csedjoP0h1j8OA9vGHKnSN6B5YrpvSzuOwUdIUbU0tEEnB
SbUcwXCIywFDmPL6ZU/0KMpLK64a11ghL5jTFJJGZS1Z0KQjXqnnAu0/nLPC+Hk3
cvTC/xQJcXhrEWLSjfwP9JJ3w3FVDAIFex9UfvteeG9lfu3EdzoKBbA6heOXkbHO
6PUGgUs2mxqhk0s+viy7B9cONjOhzj2iLHKR7S8AHsjd6e3/MJB3x2Go7zpo02DP
UwIDAQAB
-----END PUBLIC KEY-----
```

Put in a file like `public.key` or `public.txt`.

### Update the method accessTokenConverter() in AuthorizationServerConfig.java

Open the file [AuthorizationServerConfig.java](./src/main/java/fr/dorianmaliszewski/app/oauth2server/config/AuthorizationServerConfig.java) and modify the method :

```java_holder_method_tree
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new CustomTokenEnhancer();
        accessTokenConverter.setKeyPair(
                new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("test")
        );
        return accessTokenConverter;
    }
```

Replace :

- `jwt.jks` by the name of your keystore generated in the resources folder (after keystore option)
- `"password"` by the password used when you generate the key (after keypass and storepass option)
- `"test"` by your alias used to generate the key (after alias option)