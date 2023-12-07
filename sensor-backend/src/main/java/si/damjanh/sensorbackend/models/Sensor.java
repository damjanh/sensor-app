package si.damjanh.sensorbackend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="position", nullable=false)
    private String position;

    @Column(name="comments", nullable=false)
    private String comments;

    @Column(name="type")
    private int type;

    @Column(name="status", nullable=false)
    private String status;

    @OneToMany
    @JoinColumn(name = "sensor_id")
    private List<Measurement> measurements;

    public Sensor() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return type == sensor.type && Objects.equals(id, sensor.id) && Objects.equals(description, sensor.description) && Objects.equals(position, sensor.position) && Objects.equals(comments, sensor.comments) && Objects.equals(status, sensor.status) && Objects.equals(measurements, sensor.measurements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, position, comments, type, status, measurements);
    }
}
