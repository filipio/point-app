import DropdownButton from "react-bootstrap/DropdownButton";
import Dropdown from "react-bootstrap/Dropdown";
import DatePicker from "react-datepicker";
import "./App.css"
import axios from "axios";
import {useEffect, useState} from "react";

function App() {
    const DEFAULT_DROPDOWN_TEXT = "Choose client";

    const [data, setData] = useState([]);
    const [client, setClient] = useState(null);
    const [startDate, setStartDate] = useState(new Date());
    const [clientMonthPoints, setClientMonthPoints] = useState(0);
    const [clientTotalPoints, setClientTotalPoints] = useState(0);
    const [datePrefix, setDatePrefix] = useState("");

    useEffect(() => {
        axios("http://localhost:8080")
            .then(response => {
                setData(response.data.map(d => ({id : d.clientId, name : d.firstName.concat(" ", d.lastName)})));
            })
            .catch(err => console.log(err))
    }, []);

    useEffect( () => {
        if(data[0]){
            setClient(data[0]);
        }
    }, [data]);

    useEffect( () => {
        if(client !== null){
            updateClientPoints(startDate);
            axios.get(`http://localhost:8080/clients/${client.id}/totalPoints`)
                .then(response => setClientTotalPoints(response.data));
        }
    }, [client])

    const updateClientPoints = (date) => {
        const month = date.getMonth() + 1;
        const datePrefix = month < 10 ? "0" : "";
        setDatePrefix(datePrefix);
        setStartDate(date);
        const year = date.getFullYear();
        const params = new URLSearchParams([['year', year], ['month', month]]);
        axios.get(`http://localhost:8080/clients/${client.id}/points`, {params})
            .then(response => setClientMonthPoints(response.data));

    }

  return (
      <div className={"main-area"}>
          <div className={"main-container"}>
              <DropdownButton id="dropdown-item-button" title={client === null ? DEFAULT_DROPDOWN_TEXT : client.name}>
                  {data.map((client, index) =>
                      <Dropdown.Item as="button" key={index} onClick={event => setClient(client)}>{client.name}</Dropdown.Item>
                  )}
              </DropdownButton>
              <div>
                  <DatePicker
                      selected={startDate}
                      onChange={updateClientPoints}
                      dateFormat="MM/yyyy"
                      showMonthYearPicker
                  />
              </div>
          </div>
          <h3>Points for {datePrefix + (startDate.getMonth() + 1)}/{startDate.getFullYear()} : {clientMonthPoints}</h3>
          <h3>Total points : {clientTotalPoints}</h3>
      </div>

  );
}

export default App;
