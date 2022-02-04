import {useEffect, useState} from "react";
import axios from "axios";
import {Table} from "react-bootstrap";

export default function TransactionsView(){
    const [transactions, setTransactions] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:8080/transactions")
            .then(response => setTransactions(response.data))
            .catch(console.log);
    }, [])
    return(
        <Table striped bordered hover variant="dark">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            {transactions.map((t, index) =>
                <tr key={t.transactionId}>
                    <td>{index + 1}</td>
                    <td>{t.client.firstName}</td>
                    <td>{t.client.lastName}</td>
                    <td>{t.transactionDate}</td>
                    <td>{t.price}</td>
                </tr>
            )}
            </tbody>
        </Table>
    );
}