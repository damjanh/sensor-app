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

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SensorMeasurementService implements ISensorService, IMeasurementService {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

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
            measurement.setStamp(Date.from(Instant.parse(newMeasurement.getStamp())));
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
    public List<MeasurementDto> getMeasurements(Long sensorId, String from, String to) {
        final Optional<Sensor> sensor = sensorRepository.findById(sensorId);

        if (sensor.isEmpty()) {
            return new ArrayList<>();
        }

        if (from != null && to != null) {
            Instant tsFrom = OffsetDateTime.parse(from).toInstant();
            Instant tsTo = OffsetDateTime.parse(to).toInstant();

            return measurementRepository.findMeasurementsBySensorWithRange(
                    sensorId,
                    Date.from(tsFrom),
                    Date.from(tsTo)
            ).stream().map(it -> {
                MeasurementDto dto = modelMapper.map(it, MeasurementDto.class);
                dto.setPosition(sensor.get().getPosition());
                return dto;
            }).toList();
        } else {
            return sensor.map(value -> value.getMeasurements()
                            .stream()
                            .map(it -> {
                                MeasurementDto dto = modelMapper.map(it, MeasurementDto.class);
                                dto.setPosition(sensor.get().getPosition());
                                dto.setStamp(sdf.format(it.getStamp()));
                                return dto;
                            })
                            .toList())
                    .orElseGet(ArrayList::new);
        }
    }
}
