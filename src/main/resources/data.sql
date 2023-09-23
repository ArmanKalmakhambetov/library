-- Создание таблицы 'book'
CREATE TABLE IF NOT EXISTS books (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
                                    author VARCHAR(255),
                                    year INT,
                                    page_volume INT,
                                    rating INT,
                                    review VARCHAR(800),
                                    description VARCHAR(800),
                                    image VARCHAR(800)

);

-- Создание таблицы 'category'
CREATE TABLE IF NOT EXISTS category (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
);

-- Создание таблицы 'category_of_books' с внешними ключами
CREATE TABLE IF NOT EXISTS category_of_books (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 book_id INT,
                                                 category_id INT,
                                                 FOREIGN KEY (book_id) REFERENCES books(id),
                                                 FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Вставляем тестовые категории
INSERT INTO category (name) VALUES
                                ('Фантастика'),
                                ('Детектив'),
                                ('Роман'),
                                ('Поэзия');

-- Вставляем тестовые книги
INSERT INTO books (title, author, year, page_volume, rating, review, description, image) VALUES
                                                                ('Космическая одиссея', 'Артур Кларк', 1968, 355, 5, 'Так себе', 'Роман «2001: Космическая Одиссея»...', 'https://cv8.litres.ru/pub/c/cover_415/10323688.webp'),
                                                                ('Собачье сердце', 'Михаил Булгаков', 1925, 355, 5, 'Так себе', 'Повесть «Собачье сердце»...', 'https://cv1.litres.ru/pub/c/cover_415/133810.webp'),
                                                                ('Война и мир', 'Лев Толстой', 1869, 355, 5, 'Так себе', 'Роман «Война и мир», одно из величайших произведений...', 'https://cv4.litres.ru/pub/c/cover_415/66691848.webp'),
                                                                ('Стихи', 'Александр Пушкин', 1836, 355, 5, 'Так себе', 'Содержание: «У лукоморья…»...', 'https://cv1.litres.ru/pub/c/cover_415/176813.webp');

-- Вставляем тестовые категории книг
INSERT INTO category_of_books (book_id, category_id) VALUES
                                                         (1, 1),
                                                         (2, 2),
                                                         (3, 3),
                                                         (4, 4);

