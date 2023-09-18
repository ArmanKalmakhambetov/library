import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function AddBookForm() {
    const [bookData, setBookData] = useState({
        author: "",
        category: null,
        description: "",
        image: "",
        title: "",
        year: 0,
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setBookData({ ...bookData, [name]: value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        fetch("http://localhost:5050/api/books", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(bookData),
        })
            .then((response) => response.json())
            .then((data) => {
                // Handle success and reset form
                console.log("Book added:", data);
                setBookData({
                    author: "",
                    category: null,
                    description: "",
                    image: "",
                    title: "",
                    year: 0,
                });

                // You can update the books list here if needed.
            })
            .catch((error) => {
                console.error("Error:", error);
                alert("An error occurred while adding the book");
            });
    };

    return (
        <div className="container">
            <h2>Добавить новую книгу</h2>
            <form onSubmit={handleSubmit} encType="multipart/form-data">
                <div className="form-group">
                    <label htmlFor="title">Заголовок книги</label>
                    <input className="form-control"
                        type="text"
                        id="title"
                        name="title"
                        value={bookData.title}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="author">Автор</label>
                    <input className="form-control"
                        type="text"
                        id="author"
                        name="author"
                        value={bookData.author}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="description">Описание</label>
                    <textarea className="form-control"
                        id="description"
                        name="description"
                        value={bookData.description}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="image">Ссылка на изображение</label>
                    <input className="form-control"
                        type="text"
                        id="image"
                        name="image"
                        value={bookData.image}
                        onChange={handleChange}
                    />
                </div>
                <button className="btn btn-primary" type="submit">Добавить книгу</button>
            </form>
        </div>

    );
}
