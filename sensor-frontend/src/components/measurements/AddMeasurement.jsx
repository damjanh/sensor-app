import React, { useState, useRef } from 'react';
import axios from 'axios';
import moment from 'moment';
import { MEASUREMENT_TYPES } from '../../constants';

const AddMeasurement = ({ sensorId, onCloseAddMeasurement }) => {

    const type = useRef(null);
    const value = useRef(null);

    const [error, setError] = useState(null);

    const submitMeasurement = (newMeasurement) => {
        axios.post('http://localhost:8080/measurements', newMeasurement)
            .then((res) => {
                setError(null);
                onCloseAddMeasurement();
            }).catch((err) => {
                setError(err);
            });
    }

    const handleSubmit = () => {
        const newMeasurement = {
            sensor_id: sensorId,
            type: type.current.value,
            value: value.current.value,
            stamp: moment(Date.now()).format(),
        }
        console.log(newMeasurement);
        submitMeasurement(newMeasurement);
    }

    return (
        <div className='container pt-5'>
            <h2>Add Measurement</h2>

            <label className="me-2" htmlFor="type">Type:</label>
            <select ref={type} id="type" className="form-select form-select-lg mb-3" defaultValue={MEASUREMENT_TYPES[0]}>
                {
                    MEASUREMENT_TYPES.map(aType => {
                        return (
                            <option key={aType} value={aType}>{aType}</option>
                        )
                    })}
            </select>

            <div className="form-floating mb-2">
                <input ref={value} id="value" type="number" className="form-control " placeholder="Value"></input>
                <label htmlFor="value">Value:</label>
            </div>

            <button type="button" className="btn btn-primary m-2" onClick={handleSubmit}>Submit</button>
            <button type="button" className="btn btn-secondary m-2" onClick={onCloseAddMeasurement}>Cancel</button>

            {
                error &&
                <div className="alert alert-danger" role="alert">
                    {error.message}
                </div>
            }
        </div>
    )
}

export default AddMeasurement;