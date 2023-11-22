package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.CartDTO;
import vn.edu.tdtu.springcommerce.entity.CartItem;
import vn.edu.tdtu.springcommerce.service.CartService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<?> addCartItem(@RequestBody CartDTO cartDto, @RequestParam(name = "id") Integer id) {
        try {
            Integer cartItemId = cartService.addCart(cartDto, id);
            return ResponseEntity.ok(new ApiResponse("Cart item added successfully", cartItemId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error adding cart item", null));
        }
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<?> updateCartItemQuantity(@PathVariable Integer cartItemId, @RequestBody Map<String, Integer> requestBody) {
        try {
            Integer quantity = requestBody.get("quantity");
            cartService.updateCartItemQuantity(cartItemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Cart item quantity updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error updating cart item quantity", null));
        }
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Integer cartItemId) {
        try {
            cartService.deleteCartItem(cartItemId);
            return ResponseEntity.ok(new ApiResponse("Cart item deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error deleting cart item", null));
        }
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCartItems(@RequestParam(name="id") Integer accountId) {
        List<CartDTO> cartItems = cartService.getAllCartItems(accountId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
}
