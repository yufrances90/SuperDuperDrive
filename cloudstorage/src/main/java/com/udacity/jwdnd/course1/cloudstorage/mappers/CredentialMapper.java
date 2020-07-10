package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userid) VALUES (" +
            "#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credential getCredentialByCredentialId(Integer credentialid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    void delete(Integer credentialid);

    @Update("UPDATE credentials " +
            "SET url = #{url}, username = #{username}, password = #{password} " +
            "WHERE credentialid = #{credentialid}")
    int update(String url, String username, String password, Integer credentialid);
}
