package si.damjanh.sensorbackend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.damjanh.sensorbackend.dto.request.AddMeasurement;
import si.damjanh.sensorbackend.dto.response.MeasurementDto;
import si.damjanh.sensorbackend.dto.response.SensorDto;
import si.damjanh.sensorbackend.models.Measurement;
import si.damjanh.sensorbackend.models.Sensor;
import si.damjanh.sensorbackend.repositories.MeasurementRepository;
import si.damjanh.sensorbackend.repositories.SensorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SensorMeasurementService implements ISensorService, IMeasurementService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SensorDto> getSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream().map(sensor -> modelMapper
                .map(sensor, SensorDto.class)).toList();
    }

    @Override
    public SensorDto addSensor(si.damjanh.sensorbackend.dto.request.SensorDto newSensor) {
        Sensor savedSensor = sensorRepository.save(modelMapper.map(newSensor, Sensor.class));
        return modelMapper.map(savedSensor, SensorDto.class);
    }

    @Override
    public MeasurementDto addMeasurement(AddMeasurement newMeasurement) {
        Optional<Sensor> sensorFound = sensorRepository.findById(newMeasurement.getSensorId());
        if (sensorFound.isPresent()) {

            Measurement measurement = new Measurement();
            measurement.setStamp(newMeasurement.getStamp());
            measurement.setType(newMeasurement.getType());
            measurement.setValue(newMeasurement.getValue());
            measurement.setSensor(sensorFound.get());
            sensorFound.get().getMeasurements().add(measurement);

            Measurement saved = measurementRepository.save(measurement);

            MeasurementDto returnObj = modelMapper.map(saved, MeasurementDto.class);
            returnObj.setPosition(sensorFound.get().getPosition());
            return returnObj;
        }
        return null;
    }

    @Override
    public List<MeasurementDto> getMeasurements(Long sensorId) {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        return sensor.map(value -> value.getMeasurements()
                .stream()
                .map(it -> {
                    MeasurementDto dto = modelMapper.map(it, MeasurementDto.class);
                    dto.setPosition(sensor.get().getPosition());
                    return dto;
                })
                .toList())
                .orElseGet(ArrayList::new);
    }
}
