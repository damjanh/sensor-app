package si.damjanh.sensorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import si.damjanh.sensorbackend.models.Measurement;

import java.util.Date;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    @Query(value = "SELECT * FROM Measurements m WHERE m.sensor_id = ?", nativeQuery = true)
    List<Measurement> findBySensor(Long sensorId);

    @Query(value = "SELECT * FROM Measurement m WHERE m.sensor_id = :sid AND m.stamp >= :from AND m.stamp <= :to", nativeQuery = true)
    List<Measurement> findMeasurementsBySensorWithRange(
            @Param("sid") Long sensorId,
            @Param("from") Date from,
            @Param("to") Date to
    );
}
