package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserFileMapper {

    List<UserFileVO> getFileByUsername(String username);

    List<UserFileVO> getFileByUsernameAndFileName(Map<String, Object> paraMap);
}
