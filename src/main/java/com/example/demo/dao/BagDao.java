package com.example.demo.dao;

import com.example.demo.model.Bag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BagDao {
    int insertBag(UUID idBag, Bag bag, UUID idCart);

    default int insertBag(Bag bag, UUID idCart) {
        UUID id = UUID.randomUUID();
        return insertBag(id, bag, idCart);
    }

    List<Bag> selectAllBags();

    Optional<Bag> selectBagById(UUID id);

    int deleteBagById(UUID id);

    int updateBagByID(UUID id, double price);
}
