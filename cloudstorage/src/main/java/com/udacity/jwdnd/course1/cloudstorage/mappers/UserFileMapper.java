package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFileMapper {

    UserFileVO getFileByUsername(String username);
}
