package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.CloudStorageApplication;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(CloudStorageApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoteMapperTests {

    private Logger logger = LoggerFactory.getLogger(NoteMapperTests.class);

    @Autowired
    private NoteMapper noteMapper;

    @Test(expected = Exception.class)
    public void insertNoteWithNonexistUser() {

        Note newNote = new Note(
                null, "Hello World", "Hello", 1);

        this.noteMapper.insert(newNote);

        this.noteMapper.deleteAll();
    }

    @Test
    public void insertNote() {

        Note newNote = new Note(null, "Hello World", "Hello");

        this.noteMapper.insert(newNote);

        Note savedNote = this.noteMapper.getNoteById(newNote.getNoteid());

        assertNotNull(savedNote);
        assertEquals(newNote.getNotetitle(), savedNote.getNotetitle());

        this.noteMapper.deleteAll();
    }

    @Test
    public void findAllNotes() {

        Note newNote = new Note(null, "Hello World", "Hello");

        this.noteMapper.insert(newNote);

        List<Note> notes = this.noteMapper.getAllNotes();

        assertFalse(notes.isEmpty());
        assertEquals(1, notes.size());

        this.noteMapper.deleteAll();
    }

    @Test
    public void deleteNote() {

        Note newNote = new Note(null, "Hello World", "Hello");

        Integer noteId = this.noteMapper.insert(newNote);

        assertNotNull(noteId);

        this.noteMapper.delete(noteId);

        List<Note> notes = this.noteMapper.getAllNotes();

        assertTrue(notes.isEmpty());
    }
}
