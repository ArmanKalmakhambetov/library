import React, { useState, useEffect, useCallback } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input } from 'reactstrap';
import { useNavigate } from 'react-router-dom';

export default function AllBooks() {
    const [books, setBooks] = useState([]);
    const navigate = useNavigate();

    const fetchBooks = useCallback(() => {
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

    const editBook = (e) => {
        e.preventDefault();
        console.log("console:", e.target.value);
    };

    useEffect(() => {
        fetchBooks();
    }, [fetchBooks]);



    const goToBookDetails = (id) => {
        navigate(`/thisBook/${id}`);
    };

    return (
        <div className="container">
            <h1 className="mx-4">Все книги</h1>
            <ul>
                {books.map((book) => (
                    <div className="card mb-3" key={book.id} onClick={() => goToBookDetails(book.id)}>
                        <div className="card-body">
                            <div className="row">
                                <div className="col-sm-1 d-flex justify-content-center">
                                    <img src={book.image} className="card-img-top"
                                         style={{ height: '113px', width: '76px' }} alt="Обложка книги" />
                                </div>
                                <div className="col-sm-8">
                                    <h5 className="card-title">{book.title}</h5>
                                    <h6 className="card-subtitle mb-2 text-muted">{book.author}</h6>
                                    <p className="card-text">{book.description}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </ul>
        </div>
    );
}

