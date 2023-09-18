
const bookList = document.getElementById("bookList");

    // Функция для получения всех книг
    function getAllBooks() {
        console.log("fetch get all books")
        fetch("/api/books")
            .then(response => response.json())
            .then(data => {
                // Очищаем текущий список книг
                bookList.innerHTML = "";

                // Добавляем каждую книгу в виде карточки
                data.forEach(book => {
                    const bookCard = document.createElement("div");
                    bookCard.classList.add("card", "mb-3", "book-card"); // Добавляем класс "book-card"
                    bookCard.innerHTML = `
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4 d-flex justify-content-center">
                                    <img src="${book.image}" class="card-img-top" style="height: 300px; width: 200px" alt="Обложка книги">
                                </div>
                                <div class="col-sm-8">
                                    <h5 class="card-title">${book.title}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${book.author}</h6>
                                    <p class="card-text">${book.description}</p>
                                  
                                </div>
                            </div>
                        </div>
                    `;
                    bookList.appendChild(bookCard);
                });
            })
            .catch(error => {
                console.error('Ошибка:', error);
                alert("Произошла ошибка при загрузке списка книг");
            });
        document.getElementById("allBooks-tab").click();

    }

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
                getAllBooks();

            })
            .catch((error) => {
                // Обработка ошибок при отправке данных на сервер
                console.error("Ошибка:", error);
                alert("Произошла ошибка при добавлении книги");
            });
    });
});
