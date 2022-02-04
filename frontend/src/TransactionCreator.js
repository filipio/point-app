import Form from 'react-bootstrap/Form';
import {useEffect, useState} from "react";
import axios from "axios";
import {Alert, Button, FormGroup, FormLabel} from "react-bootstrap";
import "./Transaction.css";

export default function TransactionCreator() {

    const [validated, setValidated] = useState(false);
    const [error, setError] = useState("");
    const [success, setSuccess] = useState(false);
    const [clients, setClients] = useState([]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const formDataObj = Object.fromEntries(formData.entries());
        setValidated(true);
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            setError("");
            setSuccess(false);
        } else {
            axios.post("http://localhost:8080/transactions", formDataObj)
                .then(result => setSuccess(result.status === 200))
                .catch(err => setError(err.message));
        }
    };

    useEffect(() => {
        axios("http://localhost:8080/clients")
            .then(response => {
                setClients(response.data.map(client => ({
                    id: client.clientId,
                    name: client.firstName.concat(" ", client.lastName)
                })));
            })
            .catch(err => console.log(err))
    }, []);

    const getDayBefore = () => {
        let date = new Date();
        date.setDate(date.getDate() - 1);
        return date;
    };

    return (
        <div className={"area-wrapper"}>
            <div className={"form-wrapper"}>
                <Form noValidate validated={validated} onSubmit={handleSubmit}>
                    <FormGroup className={"mb-3"}>
                        <FormLabel>Client</FormLabel>
                        <Form.Select name={"clientId"} defaultValue={1}>
                            {
                                clients.map(client => <option key={client.id} value={client.id}>{client.name}</option>)
                            }
                        </Form.Select>
                    </FormGroup>
                    <FormGroup className={"mb-3"}>
                        <FormLabel>Date</FormLabel>
                        <Form.Control type={"date"} name={"transactionDate"}
                                      max={getDayBefore().toISOString().split("T")[0]}
                                      defaultValue={getDayBefore().toISOString().split("T")[0]}/>
                        <Form.Control.Feedback type="invalid">
                            Date must be in the past.
                        </Form.Control.Feedback>
                    </FormGroup>
                    <FormGroup className={"mb-3"}>
                        <FormLabel>Price</FormLabel>
                        <Form.Control type={"number"} name={"price"} min={1} defaultValue={1}/>
                        <Form.Control.Feedback type="invalid">
                            Price must be positive, integer number.
                        </Form.Control.Feedback>
                    </FormGroup>
                    <Button type="submit">Submit</Button>
                </Form>
            </div>
            {error && <Alert variant={'danger'}>{error}</Alert>}
            {success && <Alert variant={'success'}>Transaction was successfully added!</Alert>}
        </div>

    )
}

