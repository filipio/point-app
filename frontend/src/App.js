import "./App.css"
import TransactionCreator from "./TransactionCreator";
import {BrowserRouter, Link, Route} from "react-router-dom";
import {Nav, Navbar} from "react-bootstrap";
import TransactionsView from "./TransactionsView";
import PointsCalculator from "./PointsCalculator";

function App() {

    return (
        <BrowserRouter>
            <Navbar bg="dark" variant="dark">
                <div className="nav-container">
                    <Nav>
                        <Link to="/">Points Calculator</Link>
                        <Link to={"/createTransaction"}>Create transaction</Link>
                        <Link to={"/transactions"}>Transactions</Link>
                    </Nav>
                </div>
            </Navbar>
            <div className={"router-content"}>
                <Route exact path="/" component={PointsCalculator}/>
                <Route path="/createTransaction" component={TransactionCreator}/>
                <Route path="/transactions" component={TransactionsView}/>
            </div>
        </BrowserRouter>
    );
}

export default App;
