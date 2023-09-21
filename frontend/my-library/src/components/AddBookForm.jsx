import React, {useCallback, useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {useNavigate} from "react-router-dom";

export default function AddBookForm() {
    const [availableCategories, setAvailableCategories] = useState([]);

    const fetchCategories = useCallback(() => {
        fetch('http://localhost:5050/api/books/categories', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then((response) => response.json())
            .then((data) => {
                setAvailableCategories(data);
            })
            .catch((error) => {
                console.error('There was a problem fetching categories:', error);
            });
    }, []);

    useEffect(() => {
        fetchCategories();
    }, [fetchCategories]);

    const [bookData, setBookData] = useState({
        author: "",
        categories: [], // Change to an array
        description: "",
        image: "",
        title: "",
        year: 0,
    });


    const navigate = useNavigate();

    const handleChange = (event) => {
        const { name, value } = event.target;

        // For the 'categories' field, use an array with a single string value
        if (name === 'categories') {
            setBookData({ ...bookData, [name]: [value] });
        } else {
            setBookData({ ...bookData, [name]: value });
        }
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
                    categories: {
                        name: ""
                    },
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
                        <label className="my-3" htmlFor="category">Жанр</label>
                        <select
                            value={bookData.categories.name} // Set the selected category's name
                            onChange={handleChange}
                            className="form-control"
                            name="categories" // Set the name to "categories" for the handleChange function
                        >
                            {availableCategories.map((category, index) => (
                                <option key={index} value={category.name}>
                                    {category.name}
                                </option>
                            ))}
                        </select>
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
