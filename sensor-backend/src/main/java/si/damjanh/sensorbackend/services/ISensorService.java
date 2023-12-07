package si.damjanh.sensorbackend.services;

import si.damjanh.sensorbackend.dto.response.SensorDto;

import java.util.List;

public interface ISensorService {
    List<SensorDto> getSensors();

    SensorDto addSensor(si.damjanh.sensorbackend.dto.request.SensorDto newSensor);
}
