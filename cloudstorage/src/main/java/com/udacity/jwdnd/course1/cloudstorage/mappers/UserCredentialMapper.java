package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserCredentialVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCredentialMapper {

    List<UserCredentialVO> getCredentialsByUsername(String username);
}
