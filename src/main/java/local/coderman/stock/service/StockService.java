package local.coderman.stock.service;

import jakarta.annotation.PostConstruct;
import local.coderman.stock.domain.StockRecord;
import local.coderman.stock.repository.StockRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StockService {
    private final StockRecordRepository repository;
    private ConcurrentHashMap<Long, Integer> stock;

    public StockService(StockRecordRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadRepository() {
        List<StockRecord> records = repository.findAll();
        for (StockRecord record : records) {
            stock.put(record.getId(), record.getQuantity());
        }
    }

    public boolean reserve(Long productId, Integer amount) {
        if (productId == null || amount <= 0) {
            return false;
        }

        Integer currentAmount = stock.get(productId);
        if (currentAmount == null) {
            return false;
        }

        stock.compute(productId, (key, current) -> current < amount ? current : current - amount);
        return !currentAmount.equals(stock.get(productId));
    }

}
