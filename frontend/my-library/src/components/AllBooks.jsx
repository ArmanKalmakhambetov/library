import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function AllBooks() {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        // Fetch books data from your API
        fetch("http://localhost:5050/api/books", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => response.json())
            .then(data => {
                setBooks(data);
            });
    }, []);

    return (
        <div className="container">
            <h1>Все книги</h1>
            <ul>
                {books.map((book) => (
                    <div className="card mb-3" key={book.id}>
                        <div className="card-body">
                            <div className="row">
                                <div className="col-sm-4 d-flex justify-content-center">
                                    <img src={book.image} className="card-img-top"
                                         style={{ height: '300px', width: '200px' }} alt="Обложка книги" />
                                </div>
                                <div className="col-sm-8">
                                    <h5 className="card-title">{book.title}</h5>
                                    <h6 className="card-subtitle mb-2 text-muted">{book.author}</h6>
                                    <p className="card-text">{book.description}</p>
                                    <button className="btn btn-danger">Delete</button>
                                    <button className="btn btn-primary">Edit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </ul>
        </div>
    );
}
