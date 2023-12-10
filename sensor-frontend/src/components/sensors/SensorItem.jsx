const SensorItem = ({ sensor, handleSensorSelect, active }) => {
    return (
        <li className={active ? "list-group-item list-group-item-action active" : "list-group-item list-group-item-action"} onClick={() => { handleSensorSelect(sensor.id) }}>
            <div className="row nowrap">
                <div className='col-1'>
                    <small>{sensor.type}</small>
                </div>
                <div className='col-4 text-truncate' >
                    <small>{sensor.description}</small>
                </div>
                <div className='col-5 text-truncate'>
                    <small>{sensor.comments}</small>
                </div>
                <div className='col-2 text-truncate'>
                    <small>{sensor.status}</small>
                </div>
            </div>
        </li>
    )
}

export default SensorItem;