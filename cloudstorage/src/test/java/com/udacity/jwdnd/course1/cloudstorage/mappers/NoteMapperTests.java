package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTest
public class NoteMapperTests {

    private Logger logger = LoggerFactory.getLogger(NoteMapperTests.class);

    @Autowired
    private NoteMapper noteMapper;

    @Test
    public void insertNoteWithNonexistUser() {

        Note newNote = new Note(
                null, "Hello World", "Hello", 1000);

        Assertions.assertThrows(Exception.class, () -> {
            this.noteMapper.insert(newNote);
        });
    }

    @Test
    public void insertNote() {

        Note newNote = new Note(null, "Hello World", "Hello");

        this.noteMapper.insert(newNote);

        Note savedNote = this.noteMapper.getNoteById(newNote.getNoteid());

        Assertions.assertNotNull(savedNote);
        Assertions.assertEquals(newNote.getNotetitle(), savedNote.getNotetitle());
    }

    @Test
    public void findAllNotes() {

        this.noteMapper.deleteAll();

        Note newNote = new Note(null, "Hello World", "Hello");

        this.noteMapper.insert(newNote);

        List<Note> notes = this.noteMapper.getAllNotes();

        Assertions.assertFalse(notes.isEmpty());
        Assertions.assertEquals(1, notes.size());
    }

    @Test
    public void deleteNote() {

        Note newNote = new Note(null, "Hello World", "Hello");

        this.noteMapper.insert(newNote);

        Integer noteId = newNote.getNoteid();

        Assertions.assertNotNull(noteId);

        this.noteMapper.delete(noteId);

        Note note = this.noteMapper.getNoteById(noteId);

        Assertions.assertNull(note);
    }

    @Test
    public void deleteAllNotes() {

        Note newNote = new Note(null, "helloWorld", "hello");

        this.noteMapper.insert(newNote);

        List<Note> notes = this.noteMapper.getAllNotes();

        Assertions.assertFalse(notes.isEmpty());

        this.noteMapper.deleteAll();

        notes = this.noteMapper.getAllNotes();

        Assertions.assertTrue(notes.isEmpty());
    }
}
