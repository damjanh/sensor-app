package si.damjanh.sensorbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import si.damjanh.sensorbackend.dto.request.AddMeasurement;
import si.damjanh.sensorbackend.dto.response.MeasurementDto;
import si.damjanh.sensorbackend.services.IMeasurementService;

import java.util.List;

@RestController
public class MeasurementController {

    @Autowired
    private IMeasurementService measurementService;

    @GetMapping("/measurements")
    public List<MeasurementDto> getMeasurements(@RequestParam(name = "sensor_id") Long sensorId) {
        return measurementService.getMeasurements(sensorId);
    }

    @PostMapping("/measurements")
    public MeasurementDto addMeasurement(@RequestBody AddMeasurement newMeasurement) {
        return measurementService.addMeasurement(newMeasurement);
    }
}
