import React, { useEffect, useState, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input } from 'reactstrap';

export default function BookDetails() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [book, setBook] = useState(null);
    const [modal, setModal] = useState(false);
    const [bookTitle, setBookTitle] = useState('');
    const [bookAuthor, setBookAuthor] = useState('');
    const [bookYear, setBookYear] = useState('');
    const [bookDescription, setBookDescription] = useState('');
    const [bookImage, setBookImage] = useState('');
    const [bookCategories, setBookCategories] = useState([]);
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

    const toggle = () => setModal(!modal);

    const fetchBook = useCallback(() => {
        fetch(`http://localhost:5050/api/books/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then((response) => response.json())
            .then((data) => {
                setBook(data);
            })
            .catch((error) => {
                console.error('There was a problem fetching book data:', error);
            });
    }, [id]);

    useEffect(() => {
        fetchBook();
    }, [fetchBook]);

    const deleteBook = useCallback(() => {
        fetch(`http://localhost:5050/api/books/${id}`, {
            method: 'DELETE',
        })
            .then(() => {
                navigate('/allBooks');
            })
            .catch((error) => {
                console.error('There was a problem deleting the book:', error);
            });
    }, [id, navigate]);

    useEffect(() => {
        if (book) {
            setBookTitle(book.title);
            setBookAuthor(book.author);
            setBookYear(book.year);
            setBookDescription(book.description);
            setBookImage(book.image);
            setBookCategories(book.categories.map(category => category.name).join(', '));  // Если categories является массивом
            // Initialize other fields here that you want to edit
        }
    }, [book]);

    const editBook = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:5050/api/books/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    title: bookTitle,
                    author: bookAuthor,
                    year: bookYear,
                    description: bookDescription,
                    image: bookImage,
                    categories: bookCategories.split(', ').map(name => ({ name }))
                }),
            });

            const updatedBook = await response.json();
            setBook(updatedBook);
            toggle();  // закрыть модальное окно
        } catch (error) {
            console.error('There was a problem updating the book:', error);
        }
    };

    return (
        <div className="container-fluid m-5">
            {book ? (
                <>
                    <div className="row">
                        <div className="col-sm-4">
                            <img
                                src={book.image}
                                alt={`Cover of ${book.title}`}
                                style={{ height: '672px', width: '417px' }}
                            />
                            <div className="my-3">
                                <button className="btn btn-danger me-3" onClick={deleteBook}>Удалить</button>
                                <Button color="primary" onClick={toggle}>Изменить</Button>
                                <Modal isOpen={modal} toggle={toggle}>
                                    <ModalHeader toggle={toggle}>Изменить книгу</ModalHeader>
                                    <ModalBody>
                                        <div className="form-group">
                                            <label className="my-3" htmlFor="title">Название книги</label>
                                            <input value={bookTitle} type="text" className="input form-control"
                                                   onChange={(e) => setBookTitle(e.target.value)} />
                                            <label className="my-3" htmlFor="author">Автор</label>
                                            <input value={bookAuthor} type="text" className="input form-control"
                                                   onChange={(e) => setBookAuthor(e.target.value)} />
                                            <label className="my-3" htmlFor="category">Жанр</label>
                                            <select value={bookCategories} onChange={(e) => setBookCategories(e.target.value)} className="form-control">
                                                {availableCategories.map((category, index) => (
                                                    <option key={index} value={category.name}>{category.name}</option>
                                                ))}
                                            </select>

                                            <label className="my-3" htmlFor="year">Год</label>
                                            <input value={bookYear} type="number" className="input form-control"
                                                   onChange={(e) => setBookYear(e.target.value)} />
                                            <label className="my-3" htmlFor="description">Описание</label>
                                            <input value={bookDescription} type="text" className="input form-control"
                                                   onChange={(e) => setBookDescription(e.target.value)} />
                                            <label className="my-3" htmlFor="image">Ссылка на изображение</label>
                                            <input value={bookImage} type="text" className="input form-control"
                                                   onChange={(e) => setBookImage(e.target.value)} />
                                        </div>

                                    </ModalBody>
                                    <ModalFooter>
                                        <Button color="primary" onClick={editBook}>Изменить</Button>
                                        <Button color="secondary" onClick={toggle}>Отмена</Button>
                                    </ModalFooter>
                                </Modal>
                            </div>
                        </div>
                        <div className="col-sm-8" style={{width: "800px"}}>
                            <h3>{book.title}</h3>
                            <h5>Автор: {book.author}</h5>
                            <h5>Жанр: {book.categories.map((category, index) => (
                                <span key={index}>{category.name}</span>
                            ))}
                            </h5>

                            <h5>Год: <span>{book.year}</span></h5>
                            <h5>Описание книги</h5>
                            <p style={{height: "500", width: "300"}}>{book.description}</p>

                        </div>
                    </div>
                </>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
}

