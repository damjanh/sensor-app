const SensorHeader = () => {
    return (
        <div className={"list-group-item list-group-item-secondary"} >
        <div className="row nowrap">
            <div className='col'>
                Type
            </div>
            <div className='col'>
                Description
            </div>
            <div className='col-6'>
                Comments
            </div>
            <div className='col'>
                Status
            </div>
        </div>
    </div>
    );
}

export default SensorHeader;