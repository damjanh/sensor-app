package si.damjanh.sensorbackend.models;

import jakarta.persistence.*;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="description", length=50, nullable=false, unique=false)
    private String description;
}
