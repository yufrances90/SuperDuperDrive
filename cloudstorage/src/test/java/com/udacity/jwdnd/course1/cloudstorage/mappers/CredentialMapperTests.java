package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
public class CredentialMapperTests {

    private Logger logger = LoggerFactory.getLogger(CredentialMapperTests.class);

    @Autowired
    private CredentialMapper credentialMapper;

    @Test
    public void insertCredential() {

        Credential newCredential = new Credential(
                null,
                "www.google.com",
                "hello",
                "sasfdasdfasdf#$#",
                "worldsdfsdf");

        this.credentialMapper.insert(newCredential);

        Integer credentialId = newCredential.getCredentialid();

        Assertions.assertNotNull(credentialId);

        Credential credential = this.credentialMapper.getCredentialByCredentialId(credentialId);

        Assertions.assertNotNull(credential);
        Assertions.assertEquals(newCredential.getUrl(), credential.getUrl());
        Assertions.assertEquals(newCredential.getKey(), credential.getKey());
        Assertions.assertEquals(newCredential.getPassword(), credential.getPassword());
    }

    @Test
    public void deleteCredential() {

        Credential newCredential = new Credential(
                null,
                "www.google.com",
                "hello1",
                "sasfdasdfasdf#$#",
                "worldsdfsdf");

        this.credentialMapper.insert(newCredential);

        Integer credentialId = newCredential.getCredentialid();

        Assertions.assertNotNull(credentialId);

        this.credentialMapper.delete(credentialId);

        Credential credential = this.credentialMapper.getCredentialByCredentialId(credentialId);

        Assertions.assertNull(credential);
    }

    @Test
    public void updateCredential() {

        Credential newCredential = new Credential(
                null,
                "www.google.com",
                "hello1",
                "sasfdasdfasdf#$#",
                "worldsdfsdf");

        this.credentialMapper.insert(newCredential);

        Integer credentialId = newCredential.getCredentialid();

        Assertions.assertNotNull(credentialId);

        this.credentialMapper.update("", "hello", "asdfasdfasf", "123", credentialId);

        Credential credential =
                this.credentialMapper.getCredentialByCredentialId(credentialId);

        Assertions.assertEquals("", credential.getUrl());
        Assertions.assertEquals("hello", credential.getUsername());
        Assertions.assertEquals("123", credential.getPassword());
    }
}
