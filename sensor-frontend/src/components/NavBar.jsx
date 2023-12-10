import { Link } from "react-router-dom";

const NavBar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark ps-2 pe-2">
            <Link className="navbar-brand ps-5 pe-5" to="/">SensorApp</Link>
            <ul className="navbar-nav">
                <li className="nav-item active">
                    <Link className="nav-link ps-5 pe-5" to="/sensor">Add Sensor</Link>
                </li>
            </ul>
        </nav>
    )
}

export default NavBar;