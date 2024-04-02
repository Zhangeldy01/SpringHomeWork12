package com.example.SpringHomeWork12.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс Note - Заметки
 */
@Data
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID (автоинкрементное)(тип - Long)

    @Column(nullable = false)
    private String header; // Заголовок (не может быть пустым)(тип - String)

    @Column(nullable = false)
    private String content; // Содержимое (не может быть пустым)(тип - String)

  //  private LocalDateTime createDate = LocalDateTime.now(); // Дата создания (автоматически устанавливается при создании заметки)
}
