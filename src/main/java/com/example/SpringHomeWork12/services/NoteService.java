package com.example.SpringHomeWork12.services;

import com.example.SpringHomeWork12.models.Note;

import java.util.List;

/**
 * Интерфейс сервиса заметок
 */
public interface NoteService {
    List<Note> findAll();
    Note findNoteById(Long id);
    Note createNote(Note note);
    Note updateNote(Long id, Note note);
    void deleteNote(Long id);

}
