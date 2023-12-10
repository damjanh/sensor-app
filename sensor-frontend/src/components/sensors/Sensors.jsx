import React, { useState, useEffect } from 'react';
import axios from 'axios';
import SensorItem from './SensorItem';
import SensorHeader from './SensorHeader';

const Sensors = ({ handleSensorSelected }) => {
    const [sensors, setSensors] = useState([]);
    const [error, setError] = useState(null);
    const [selected, setSelected] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/sensors')
            .then((res) => {
                setError(null);
                setSensors(res.data);
            }).catch((err) => {
                setError(err);
            });
    }, []);

    const handleSensorSelect = (id) => {
        setSelected(id);
        handleSensorSelected(id);
    }

    return (
        <>
            Sensors
            {
                error &&
                <div class="alert alert-danger" role="alert">
                    {error.message}
                </div>
            }
            <ul className="list-group list-group">
                <SensorHeader />
                {
                    sensors.map(it => {
                        return <SensorItem key={it.id}
                            sensor={it}
                            handleSensorSelect={handleSensorSelect}
                            active={it.id === selected}
                        />
                    })}
            </ul>
        </>
    )
}

export default Sensors;