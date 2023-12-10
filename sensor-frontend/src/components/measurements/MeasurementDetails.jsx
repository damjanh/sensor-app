import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import moment from 'moment';
import { MEASUREMENT_RANGES, MEASUREMENT_QUERY_TYPES } from '../../constants';

const MeasurementDetails = ({ selectedSensorId, onAddMeasurement }) => {

    const [measurements, setMeasurements] = useState([]);
    const [average, setAverage] = useState(null);
    const [error, setError] = useState(null);
    const type = useRef(MEASUREMENT_QUERY_TYPES[0]);
    const range = useRef(MEASUREMENT_RANGES[0]);

    const calculateAvg = (arr) => {
        let sum = 0;
        let i = 0;

        arr.forEach(function (item, idx) {
            if (item.type === type.current.value) {
                sum += item.value;
                i++;
            }
        });

        return sum / i;
    }

    useEffect(() => {
        if (selectedSensorId !== null) {
            retrieveMeasurements(selectedSensorId)
        }
    }, [selectedSensorId]);

    const retrieveMeasurements = (sensorId) => {
        const to = encodeURIComponent(moment().format());
        const from = encodeURIComponent(moment().subtract(range.current.value, 'hours').format());
        axios.get(`http://localhost:8080/measurements?sensor_id=${sensorId}&from=${from}&to=${to}`)
            .then((res) => {
                setError(null);
                setMeasurements(res.data);
                console.log(res.data)
                setAverage(calculateAvg(res.data));
            }).catch((err) => {
                setError(err);
            });
    }

    const handleQueryChange = () => {
        retrieveMeasurements(selectedSensorId);
    }

    return (
        error ?
            <div class="alert alert-danger m-4" role="alert">
                {error.message}
            </div>
            :
            <div className='p-4'>
                <div className='row'>
                    <div className='col'>
                        <div className="dropdown">
                            <select ref={type} className="form-select" onChange={handleQueryChange}>
                                {
                                    MEASUREMENT_QUERY_TYPES.map(it => {
                                        return (<option className="dropdown-item" key={it} value={it}>{it}</option>)
                                    })
                                }
                            </select>
                        </div>
                    </div>
                    <div className='col'>
                        <div className="dropdown">
                            <select ref={range} className="form-select" onChange={handleQueryChange}>
                                {
                                    MEASUREMENT_RANGES.map(it => {
                                        return (<option className="dropdown-item" key={it} value={it}>Last {it} hours</option>)
                                    })
                                }
                            </select>
                        </div>
                    </div>
                </div>
                <div className='row text-center mt-5 lead'>
                    {!selectedSensorId && <div>Please select a sensor.</div>}
                    {selectedSensorId && (measurements.length === 0 || isNaN(average)) && <div>No Measurements</div>}
                    {selectedSensorId && (measurements.length > 0 && !isNaN(average)) && <div>Average <b>{type.current.value}</b> data for last {range.current.value} hours: <b>{average}</b></div>}
                    <button type="button" className="btn btn-primary m-2" disabled={!selectedSensorId} onClick={() => onAddMeasurement(selectedSensorId)}>Add  Measurement</button>
                </div>
            </div>
    )
}

export default MeasurementDetails;