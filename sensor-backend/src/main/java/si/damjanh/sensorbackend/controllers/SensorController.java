package si.damjanh.sensorbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import si.damjanh.sensorbackend.dto.response.SensorDto;
import si.damjanh.sensorbackend.services.ISensorService;

import java.util.List;

@RestController
public class SensorController {

    @Autowired
    private ISensorService sensorService;


    @GetMapping("/sensors")
    public List<SensorDto> getSensors() {
        return sensorService.getSensors();
    }

    @PostMapping("/sensors")
    public SensorDto addSensor(@RequestBody si.damjanh.sensorbackend.dto.request.SensorDto newSensor) {
        return sensorService.addSensor(newSensor);
    }
}
