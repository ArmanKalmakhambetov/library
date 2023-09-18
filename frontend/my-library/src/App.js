import { BrowserRouter, Routes, Route, Link, Outlet } from "react-router-dom";
import Home from "./components/Home";
import Layout from "./components/Layout";
import AllBooks from "./components/AllBooks";
import AddBookForm from "./components/AddBookForm";
export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route path="/" element={<Home />} />
            <Route path="/allbooks" element={<AllBooks />} />
            <Route path="/addbooks" element={<AddBookForm />} />
          </Route>
        </Routes>
      </BrowserRouter>
  );
}



