package local.coderman.stock.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class StockService {
    private ConcurrentHashMap<Long, Integer> stock;

    public synchronized boolean reserve(Long productId, int amount) {
        Integer currentAmount = stock.get(productId);
        if (currentAmount == null || currentAmount < amount) {
            return false;
        }
        stock.put(productId, currentAmount - amount);
        return false;
    }

}
