package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserNoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private Logger logger = LoggerFactory.getLogger(NoteService.class);

    private NoteMapper noteMapper;
    private UserNoteMapper userNoteMapper;

    public NoteService(
            NoteMapper noteMapper, UserNoteMapper userNoteMapper) {

        this.noteMapper = noteMapper;
        this.userNoteMapper = userNoteMapper;
    }

    public List<UserNoteVO> getNotesByUsername(String username) {
        return this.userNoteMapper.getNotesByUsername(username);
    }
}
