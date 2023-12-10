import React, { useState } from "react";
import Sensors from "./sensors/Sensors";
import MeasurementDetails from "./measurements/MeasurementDetails";
import AddMeasurement from "./measurements/AddMeasurement";


const Main = () => {

    const [selectedSensor, setSelectedSensor] = useState(null);
    const [addMeasurementToId, setAddMeasurementToId] = useState(null);

    const handleSensorSelected = (id) => {
        setSelectedSensor(id);
    }

    const handleAddMeasurement = (id) => {
        setAddMeasurementToId(id);
    }

    const handleCloseAddMeasurement = () => {
        setAddMeasurementToId(null);
    }

    return (
        <div className="row ms-2 me-2">
            {
                addMeasurementToId ?
                    <AddMeasurement sensorId={addMeasurementToId} onCloseAddMeasurement={handleCloseAddMeasurement}/>
                    :
                    <>
                        <div className="col-sm">
                            <Sensors handleSensorSelected={handleSensorSelected} />
                        </div>
                        <div className="col-sm">
                            <MeasurementDetails selectedSensorId={selectedSensor} onAddMeasurement={handleAddMeasurement} />
                        </div>
                    </>
            }
        </div>
    )
}

export default Main;