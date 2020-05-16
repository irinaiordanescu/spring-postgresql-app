package com.example.demo.api;

import com.example.demo.model.Bag;
import com.example.demo.model.Cart;
import com.example.demo.model.Person;
import com.example.demo.service.BagService;
import com.example.demo.service.CartService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/bag")
@RestController
public class BagController {
    private final BagService bagService;

    @Autowired
    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @PostMapping(path = "{idCart}")
    public void addBag(@Valid @NotNull @RequestBody Bag bag, @PathVariable("idCart") UUID idCart) {
        bagService.addBag(bag, idCart);
    }

    @GetMapping
    public List<Bag> getAllBags() {
        return bagService.getAllBags();
    }

    @GetMapping(path = "{idBag}")
    public Bag getBagById(@PathVariable("idBag") UUID id) {
        return bagService.getBagById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{idBag}")
    public void deletePersonById(@PathVariable("idBag") UUID id) {
        bagService.deleteBagById(id);
    }

    @PutMapping(path = "{idBag}")
    public void updateBagById(@PathVariable("idBag") UUID id, @Valid @NotNull @RequestBody String priceToUpdate) {
        double finalPrice = Double.parseDouble(priceToUpdate);
        bagService.updateBagById(id, finalPrice);
    }
}
