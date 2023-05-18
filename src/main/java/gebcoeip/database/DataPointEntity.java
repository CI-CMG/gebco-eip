package gebcoeip.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "data_point")
public class DataPointEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_point_seq")
    @GenericGenerator(
            name = "data_point_seq",
            strategy = "edu.colorado.cires.cmg.jpa.util.AssignedSequenceGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "data_point_seq")
    )
    private Integer id;

    @Column(name = "file_id", nullable = false, length = 100)
    private String fileId;

    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "depth", nullable = false)
    private Double depth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }
}
