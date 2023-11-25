package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.CategoryDTO;
import vn.edu.tdtu.springcommerce.dto.OrderDTO;
import vn.edu.tdtu.springcommerce.dto.OrderRequestDTO;
import vn.edu.tdtu.springcommerce.entity.Order;
import vn.edu.tdtu.springcommerce.entity.OrderDetails;
import vn.edu.tdtu.springcommerce.service.CategoryService;
import vn.edu.tdtu.springcommerce.service.OrderService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            Integer orderId = orderService.placeOrder(orderRequestDTO);
            return ResponseEntity.ok(new ApiResponse("Order placed successfully", orderId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error placing order", null));
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getOrderInfo(@RequestParam(name = "oid") Integer orderId) {
        try {
            OrderRequestDTO order = orderService.getOrderInfo(orderId);

            if (order != null) {
//                List<OrderDetails> orderDetails = orderService.getOrderDetails(orderId);
//                OrderDTO orderDTO = orderService.mapOrderToDTO(order, orderDetails);
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.ok(new ApiResponse("Order not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error retrieving order", null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> cancelAnOrder(@RequestParam("oid") Integer orderId) {
        try {
            boolean isCancelled = orderService.cancelOrder(orderId);

            if (isCancelled) {
                return ResponseEntity.ok(new ApiResponse("Order cancelled successfully", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Order cannot be cancelled", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error cancelling order", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrderStatus(@RequestParam("oid") Integer orderId, @RequestBody Map<String, String> requestBody) {
        try {
            String status = requestBody.get("status");
            orderService.updateStatus(orderId, status);
            return ResponseEntity.ok(new ApiResponse("Order status updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error updating order status", null));
        }
    }

}