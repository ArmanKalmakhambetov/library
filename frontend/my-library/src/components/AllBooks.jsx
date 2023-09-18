import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input } from 'reactstrap';
export default function AllBooks(props) {
    const [books, setBooks] = useState([]);


    const deleteBook =(message)=>{

        fetch(`http://localhost:5050/api/books/${message}`, {
            method: "DELETE"
        })
            .then(data => {
                console.log(data)
            })

        // e.preventDefault();
        console.log('The link was clicked.',message);
    }

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
    }, [deleteBook]);



    const [modal, setModal] = useState(false);

    const toggle = () => {
        setModal(!modal);
    };

    useEffect(() => {
        // You can put any code that should run when the component mounts or when 'modal' changes here.
        // For example, you could make an API call, update state, or perform other side effects.
    }, [modal]);

    const editBook = (e) => {
        e.preventDefault();
        console.log("console:", e.target.value);

    }

    const [bookTitle, setBookTitle] = useState();




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
                                    <button className="btn btn-danger" onClick={ () => deleteBook(book.id)}>Delete</button>
                                    {/*<button className="btn btn-primary" onClick={() => modalEdit()}>Edit</button>*/}

                                    <Button color="primary" onClick={toggle}>Edit</Button>
                                    <Modal isOpen={modal} toggle={toggle} className={props.className}>
                                        <ModalHeader toggle={toggle}>Modal title</ModalHeader>
                                        <ModalBody>
                                            <input value={bookTitle} type="text" className="input" onChange={(e) => {
                                            setBookTitle(e.target.value)}
                                            }/>
                                            <Input placeholder={book.author} />
                                            <Input placeholder={book.description} />
                                            <Input placeholder={book.image} />
                                            <Input placeholder={book.year} />
                                            <form onSubmit={editBook}>
                                                <button type="submit">submit</button>
                                            </form>
                                        </ModalBody>
                                        <ModalFooter>
                                            <Button color="primary" onClick={toggle}>Apply</Button>{' '}
                                            <Button color="secondary" onClick={toggle}>Cancel</Button>
                                        </ModalFooter>
                                    </Modal>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </ul>
        </div>
    );
}
