import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const sensorStatues = [
    'ACTIVE', 'ACTIVE_UNCONFIRMED', 'NOT_CLAIMED', 'BANNED'
]

const sensorTypes = [
    '0', '1', '2', '3', '20004'
]

const AddSensor = () => {

    const navigate = useNavigate();

    const [error, setError] = useState(null);

    const type = useRef(null);
    const status = useRef(null);
    const description = useRef(null);
    const comments = useRef(null);
    const position = useRef(null);

    const submitSensor = (newSensor) => {
        axios.post('http://localhost:8080/sensors', newSensor)
            .then((res) => {
                setError(null);
                navigate('/');
            }).catch((err) => {
                setError(err);
            });
    }

    const handleSensorSubmit = () => {
        const newSensor = {
            description: description.current.value,
            comments: comments.current.value,
            position: position.current.value,
            type: type.current.value,
            status: status.current.value,
        }

        submitSensor(newSensor);
    }

    return (
        <div className="container pt-2">
            <h2>Add sensor</h2>
            <div className="dropdown mt-2 mb-2">
                <label className="me-2" htmlFor="type">Type:</label>
                <select ref={type} id="type" className="form-select form-select-lg mb-3" defaultValue={sensorTypes[0]}>
                    {
                        sensorTypes.map(aType => {
                            return (
                                <option key={aType} value={aType}>{aType}</option>
                            )
                        })}
                </select>
            </div>

            <div className="dropdown mt-2 mb-2">
                <label className="me-2" htmlFor="status">Status:</label>
                <select ref={status} id="status" className="form-select form-select-lg mb-3" defaultValue={sensorStatues[0]}>
                    {
                        sensorStatues.map(aType => {
                            return (
                                <option key={aType} value={aType}>{aType}</option>
                            )
                        })}
                </select>
            </div>

            <div className="form-floating mb-2">
                <input id="description" type="text" className="form-control " placeholder="Description" ref={description}></input>
                <label htmlFor="description">Description:</label>
            </div>

            <div className="form-floating mb-2">
                <input id="comments" type="text" className="form-control" placeholder="Comments" ref={comments}></input>
                <label htmlFor="comments">Comments:</label>
            </div>

            <div className="form-floating mb-2">
                <input id="position" type="text" className="form-control" placeholder="Position" ref={position}></input>
                <label htmlFor="position">Position:</label>
            </div>

            <button type="button" className="btn btn-primary m-2" onClick={handleSensorSubmit}>Add Sensor</button>
            <button type="button" className="btn btn-secondary m-2" onClick={() => navigate("/")}>Cancel</button>

            {
                error &&
                <div className="alert alert-danger" role="alert">
                    {error.message}
                </div>
            }
        </div>
    )
}

export default AddSensor;