package si.damjanh.sensorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import si.damjanh.sensorbackend.models.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    @Query(value = "SELECT * FROM Measurements m WHERE m.sensor_id = ?", nativeQuery = true)
    List<Measurement> findBySensor(Long sensorId);
}
