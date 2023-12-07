package si.damjanh.sensorbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddMeasurement {
    @JsonProperty("sensor_id")
    private Long sensorId;
    private String stamp;
    private String type;
    private Long value;

    public AddMeasurement() {
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
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
}
