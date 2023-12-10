const SensorHeader = () => {
    return (
        <div className={"list-group-item list-group-item-secondary"} >
        <div className="row nowrap">
            <div className='col-1'>
                Type
            </div>
            <div className='col-4'>
                Description
            </div>
            <div className='col-5'>
                Comments
            </div>
            <div className='col-2'>
                Status
            </div>
        </div>
    </div>
    );
}

export default SensorHeader;