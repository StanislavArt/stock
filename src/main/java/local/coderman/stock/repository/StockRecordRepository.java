package local.coderman.stock.repository;

import local.coderman.stock.domain.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
}
