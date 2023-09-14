// getAllBooks.js
getAllBooks()
function getAllBooks() {
    console.log("fetch get all books")
    const bookList = document.getElementById("bookList");
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
}

export { getAllBooks }; // Экспортируем функцию
