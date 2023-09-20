import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {useNavigate} from "react-router-dom";

export default function AddBookForm() {
    const [bookData, setBookData] = useState({
        author: "",
        category: null,
        description: "",
        image: "",
        title: "",
        year: 0,
    });

    const navigate = useNavigate();

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
                })
                navigate("/allBooks");

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
            <div className="container-fluid d-flex justify-content-center">
                <form style={{width: '400px' }} onSubmit={handleSubmit} encType="multipart/form-data">
                    <div className="form-group">
                        <label className="my-3" htmlFor="title">Заголовок книги</label>
                        <input className="form-control"
                               type="text"
                               id="title"
                               name="title"
                               value={bookData.title}
                               onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label className="my-3" htmlFor="author">Автор</label>
                        <input className="form-control"
                               type="text"
                               id="author"
                               name="author"
                               value={bookData.author}
                               onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label className="my-3" htmlFor="year">Год</label>
                        <input className="form-control"
                               type="number"
                               id="year"
                               name="year"
                               value={bookData.year}
                               onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label className="my-3" htmlFor="description">Описание</label>
                        <textarea className="form-control"
                                  id="description"
                                  name="description"
                                  value={bookData.description}
                                  onChange={handleChange}
                        />
                    </div>
                    <div>
                        <label className="my-3" htmlFor="image">Ссылка на изображение</label>
                        <input className="form-control"
                               type="text"
                               id="image"
                               name="image"
                               value={bookData.image}
                               onChange={handleChange}
                        />
                    </div>
                    <button className="btn btn-primary my-3" type="submit">Добавить книгу</button>
                </form>
            </div>

        </div>

    );
}
