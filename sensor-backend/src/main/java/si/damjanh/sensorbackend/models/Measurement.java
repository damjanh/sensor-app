package si.damjanh.sensorbackend.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stamp;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Measurement() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
        if (sensor.getMeasurements() != null && !sensor.getMeasurements().contains(this)) {
            sensor.getMeasurements().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(stamp, that.stamp) && Objects.equals(type, that.type) && Objects.equals(value, that.value) && Objects.equals(sensor, that.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stamp, type, value, sensor);
    }
}
