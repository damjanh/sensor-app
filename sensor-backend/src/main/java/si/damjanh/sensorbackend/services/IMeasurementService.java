package si.damjanh.sensorbackend.services;

import si.damjanh.sensorbackend.dto.request.AddMeasurement;
import si.damjanh.sensorbackend.dto.response.MeasurementDto;

import java.util.List;

public interface IMeasurementService {
    MeasurementDto addMeasurement(AddMeasurement newMeasurement);
    List<MeasurementDto> getMeasurements(Long sensorId);
}
