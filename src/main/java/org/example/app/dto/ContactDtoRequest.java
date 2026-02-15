package org.example.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Java record - клас, мета якого зберігати дані та надавати
 * деякі спеціальні функції, наприклад, немає необхідності описувати
 * конструктор, геттер, equals, hashCode, оскільки в рекорді ці речі будуть
 * автоматично згенеровані компілятором
 */
// @JsonIgnoreProperties допомогає уникнути помилки
// при створенні об'єкту цього типу якщо якесь поле є null
@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String phone) {
}
