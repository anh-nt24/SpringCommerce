package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.ShopDTO;
import vn.edu.tdtu.springcommerce.service.ShopService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<?> addShop(@RequestBody ShopDTO shopDto) {
        try {
            Integer shopId = shopService.addShop(shopDto);
            return ResponseEntity.ok(new ApiResponse("Shop added successfully", shopId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error adding shop", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@RequestParam(name = "shop") Integer shopId, @RequestBody ShopDTO shopDto) {
        try {
            shopService.updateShop(shopId, shopDto);
            return ResponseEntity.ok(new ApiResponse("Shop updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error updating shop", null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteShop(@RequestParam(name = "shop") Integer shopId) {
        try {
            shopService.deleteShop(shopId);
            return ResponseEntity.ok(new ApiResponse("Shop deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error deleting shop", null));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllShops() {
        try {
            List<ShopDTO> shops = shopService.getAllShops();
            return ResponseEntity.ok(shops);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error retrieving shops", null));
        }
    }

    @GetMapping("/view")
    public ResponseEntity<?> getShopById(@RequestParam(name = "shop") Integer shopId) {
        try {
            ShopDTO shopDTO = shopService.getShopById(shopId);

            if (shopDTO != null) {
                return ResponseEntity.ok(shopDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Shop not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error retrieving shop", null));
        }
    }
}
