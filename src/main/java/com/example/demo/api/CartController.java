package com.example.demo.api;

import com.example.demo.dao.PersonDataAccessService;
import com.example.demo.model.Cart;
import com.example.demo.model.Person;
import com.example.demo.service.CartService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/cart")
@RestController
public class CartController {
    private final CartService cartService;
    private final PersonService personService;

    @Autowired
    public CartController(CartService cartService, PersonService personService) {
        this.cartService = cartService;
        this.personService = personService;
    }

    @PostMapping(path = "{idPerson}")
    public void addCart(@Valid @NotNull @RequestBody Cart cart, @PathVariable("idPerson")UUID idPerson) {
        Person person = personService.getPersonById(idPerson).get();
        cartService.addCart(cart, person);
    }

    @GetMapping
    public List<Cart> selectAllCarts(){
        return cartService.selectAllCarts();
    }

    @GetMapping(path = "{idCart}")
    public Cart selectAllProducts(@PathVariable("idCart") UUID idCart) {
        return cartService.selectAllProducts(idCart);
    }

    @DeleteMapping(path = "{idCart}")
    public void deleteCartById(@PathVariable("idCart") UUID idCart) {
        cartService.deleteCartById(idCart);
    }

    @DeleteMapping(path = "{idCart, idBag}")
    public void deleteProductFromCartById(@PathVariable("idCart")UUID idCart, UUID idBag) {
        cartService.deleteProductFromCartById(idCart, idBag);
    }

    @PutMapping(path = "{idCart, idBag}")
    public void updateProductFromCartById(
            @PathVariable("idCart") UUID idCart,
            UUID idBag,
            @Valid @NotNull @RequestBody double priceToUpdate) {
        cartService.updateProductFromCartById(idCart, idBag, priceToUpdate);
    }
}
