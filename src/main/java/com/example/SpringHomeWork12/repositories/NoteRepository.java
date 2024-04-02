package com.example.SpringHomeWork12.repositories;

import com.example.SpringHomeWork12.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с БД
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Метод для получения заметки по id
     * @param id
     * @return возвращает заметку
     */
    Optional<Note> findNoteById(Long id);
}
