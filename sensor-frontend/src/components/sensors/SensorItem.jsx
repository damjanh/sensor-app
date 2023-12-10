const SensorItem = ({ sensor, handleSensorSelect, active }) => {
    return (
        <li className={active ? "list-group-item list-group-item-action active" : "list-group-item list-group-item-action"} onClick={() => { handleSensorSelect(sensor.id) }}>
            <div className="row nowrap">
                <div className='col'>
                    <small>{sensor.type}</small>
                </div>
                <div className='col'>
                    <small>{sensor.description}</small>
                </div>
                <div className='col-6'>
                    <small>{sensor.comments}</small>
                </div>
                <div className='col'>
                    <small>{sensor.status}</small>
                </div>
            </div>
        </li>
    )
}

export default SensorItem;