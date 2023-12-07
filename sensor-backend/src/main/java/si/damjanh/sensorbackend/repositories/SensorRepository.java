package si.damjanh.sensorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.damjanh.sensorbackend.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
