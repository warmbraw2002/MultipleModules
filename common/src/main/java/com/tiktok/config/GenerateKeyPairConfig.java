package com.tiktok.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GenerateKeyPairConfig {

    @Value("${SystemConfig.rsa-keypair.algorithm}")
    private String algorithm;

    @Value("${SystemConfig.rsa-keypair.key-size}")
    private Integer keySize;

    @Value("${SystemConfig.rsa-keypair.public-key-file}")
    private String publicKeyFile;

    @Value("${SystemConfig.rsa-keypair.private-key-file}")
    private String privateKeyFile;

    /**
     * 获取指定加密算法
     * @return 读取YAML文件的 SystemConfig.rsa-keypair.algorithm 属性
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * 获取密钥长度，用来初始化
     * @return 读取YAML文件的 SystemConfig.rsa-keypair.key-size 属性
     */
    public Integer getKeySize() {
        return keySize;
    }

    /**
     * 获取公钥存放文件
     * @return 读取YAML文件的 SystemConfig.rsa-keypair.public-key-file 属性
     */
    public String getPublicKeyFile() {
        return publicKeyFile;
    }

    /**
     * 获取私钥存放文件
     * @return 读取YAML文件的 SystemConfig.rsa-keypair.private-key-file 属性
     */
    public String getPrivateKeyFile() {
        return privateKeyFile;
    }
}