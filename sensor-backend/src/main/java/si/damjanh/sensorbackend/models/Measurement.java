package si.damjanh.sensorbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Measurement {

    @Id
    Long id;

    String stamp;

    String type;

    Long value;
}
