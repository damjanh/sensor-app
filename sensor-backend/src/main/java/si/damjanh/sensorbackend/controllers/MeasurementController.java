package si.damjanh.sensorbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import si.damjanh.sensorbackend.dto.request.AddMeasurement;
import si.damjanh.sensorbackend.dto.response.MeasurementDto;
import si.damjanh.sensorbackend.services.IMeasurementService;

import java.text.ParseException;
import java.util.List;

@RestController
public class MeasurementController {

    @Autowired
    private IMeasurementService measurementService;

    @GetMapping("/measurements")
    public List<MeasurementDto> getMeasurements(
            @NonNull @RequestParam(name = "sensor_id") Long sensorId,
            @Nullable @RequestParam(name = "from") String from,
            @Nullable @RequestParam(name = "to") String to
    ) {
        try {
            return measurementService.getMeasurements(sensorId, from, to);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/measurements")
    public MeasurementDto addMeasurement(@RequestBody AddMeasurement newMeasurement) {
        return measurementService.addMeasurement(newMeasurement);
    }
}
