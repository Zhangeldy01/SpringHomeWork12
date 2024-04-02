package com.example.SpringHomeWork12.controllers;

import com.example.SpringHomeWork12.models.Note;
import com.example.SpringHomeWork12.services.FileGateWay;
import com.example.SpringHomeWork12.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс, используемый для обработки запросов
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes1")
public class NoteController {

    private final NoteService noteService;
    private final FileGateWay fileGateWay;


    /**
     * Метод обработки Get-запроса
     * @return возвращает список всех заметок
     */
    @GetMapping()
    public ResponseEntity<List<Note>> findAllNotes() {
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    /**
     * Метод обработки Post-запроса, создание новой заметки
     * @param note
     * @return возвращает новую заметку
     */
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        fileGateWay.writeToFile(note.getHeader() + ".txt", note.getContent());
        //addNoteCounter;
        //note.setCreateDate(LocalDateTime.now());
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    /**
     * Получение заметки по id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.findNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    /**
     * Редактирование заметки по id
     * @param note
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable("id") Long id) {
        note.setId(id);
        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }

    /**
     * Удаление заметки по id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
