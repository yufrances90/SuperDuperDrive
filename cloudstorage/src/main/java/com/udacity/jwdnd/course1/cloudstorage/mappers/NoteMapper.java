package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note getNoteById(Integer noteid);

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) VALUES (" +
            "#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void delete(Integer noteid);

    @Delete("DELETE FROM NOTES")
    void deleteAll();

    @Update("UPDATE notes " +
            "SET notetitle = #{notetitle}, notedescription = #{notedescription} " +
            "WHERE noteid = #{noteid}")
    int update(String notetitle, String notedescription, Integer noteid);
}
