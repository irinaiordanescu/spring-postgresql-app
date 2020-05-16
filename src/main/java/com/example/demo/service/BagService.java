package com.example.demo.service;

import com.example.demo.dao.BagDao;
import com.example.demo.model.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BagService {
    private final BagDao bagDao;

    @Autowired
    public BagService(@Qualifier("bag") BagDao bagDao) {
        this.bagDao = bagDao;
    }

    public int addBag(Bag bag, UUID idCart) {
        return  bagDao.insertBag(bag, idCart);
    }

    public List<Bag> getAllBags() {
        return bagDao.selectAllBags();
    }

    public Optional<Bag> getBagById(UUID id) {
        return  bagDao.selectBagById(id);
    }

    public int deleteBagById(UUID id) {
        return bagDao.deleteBagById(id);
    }

    public int updateBagById(UUID id, double newPrice) {
        return bagDao.updateBagByID(id, newPrice);
    }
}

