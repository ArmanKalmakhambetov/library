import { useState, useEffect } from "react";

export default function Header() {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:5050/api/books`, { method: "GET"})
            .then((response) => console.log(response));
    }, []);

    return(<div><h1>hi</h1></div>)

}