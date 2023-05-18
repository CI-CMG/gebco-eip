package gebcoeip.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataPointRepository extends JpaRepository<DataPointEntity, Integer> {
}
