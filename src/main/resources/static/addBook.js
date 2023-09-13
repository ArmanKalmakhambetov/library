
document.addEventListener("DOMContentLoaded", function () {
    const addBookForm = document.getElementById("addBookForm");

    // Обработчик события отправки формы
    addBookForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Предотвращаем стандартное действие отправки формы

        // Получаем данные из формы
        const title = document.getElementById("title").value;
        const author = document.getElementById("author").value;
        const description = document.getElementById("description").value;
        const image = document.getElementById("image").value;

        // Создаем объект с данными книги
        const bookData = {
            title: title,
            author: author,
            description: description,
            image: image,
        };

        // Отправляем данные на сервер
        fetch("/api/books", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(bookData),
        })
            .then((response) => response.json())
            .then((data) => {
                // Обработка успешного ответа от сервера
                console.log("Книга успешно добавлена:", data);

                // Очищаем форму после успешного добавления
                addBookForm.reset();

                // Добавляем новую книгу в список (если необходимо)
                // Например, можно вызвать функцию для обновления списка книг
                // getAllBooks();
            })
            .catch((error) => {
                // Обработка ошибок при отправке данных на сервер
                console.error("Ошибка:", error);
                alert("Произошла ошибка при добавлении книги");
            });
    });
});
