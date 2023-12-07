package si.damjanh.sensorbackend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.damjanh.sensorbackend.dto.response.SensorDto;
import si.damjanh.sensorbackend.models.Sensor;
import si.damjanh.sensorbackend.repositories.SensorRepository;

import java.util.List;


@Service
public class SensorService implements ISensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SensorDto> getSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream().map(sensor -> modelMapper
                .map(sensor, SensorDto.class)).toList();
    }
}
