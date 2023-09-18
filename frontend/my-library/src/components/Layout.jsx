import React from "react";
import { Link, Outlet } from "react-router-dom";

export default function Layout() {
    return (
        <div className="container">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <Link className="navbar-brand" to="/">
                    Калмахамбетова Т.С.
                </Link>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">
                                Home
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/allBooks">
                                Все книги
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/addBooks">
                                Добавить книгу
                            </Link>
                        </li>
                    </ul>
                </div>
            </nav>
            <hr />
            <Outlet />
        </div>
    );
}
