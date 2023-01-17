package com.tiktok.config;

import com.tiktok.utils.RSAUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSA工具封装类
 * Created by liujian on 2020/11/18 21：25.
 */
@Data
@Component
public class RSAKeyProperties {

    @Value("${rsa-keypair.public-key-file}")
    private String publicKeyFile;
    @Value("${rsa-keypair.private-key-file}")
    private String privateKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;
    @Value("${rsa-keypair.secret}")
    private String secret;

    @PostConstruct
    public void createRSAKey() throws Exception {
        RSAUtils.generateKey(publicKeyFile, privateKeyFile, secret, 0);
        this.publicKey = RSAUtils.getPublicKey(publicKeyFile);
        this.privateKey = RSAUtils.getPrivateKey(privateKeyFile);
    }
}
