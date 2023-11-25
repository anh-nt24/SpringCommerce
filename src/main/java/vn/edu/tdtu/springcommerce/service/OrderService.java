package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.dto.OrderDetailDTO;
import vn.edu.tdtu.springcommerce.dto.OrderRequestDTO;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.entity.Order;
import vn.edu.tdtu.springcommerce.entity.OrderDetails;
import vn.edu.tdtu.springcommerce.entity.Product;
import vn.edu.tdtu.springcommerce.repository.OrderDetailRepository;
import vn.edu.tdtu.springcommerce.repository.OrderRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Integer placeOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();
        Account customer = accountService.findById(orderRequest.getCustomerId());
        order.setCustomer(customer);
        Double total = 0.0;
        for (OrderDetailDTO orderDetailDTO : orderRequest.getOrderDetails()) {
            Product product = productService.findProductById(orderDetailDTO.getProductId());
            Double productPrice = product.getPrice();
            total += productPrice*orderDetailDTO.getQuantity();
        }
        order.setTotalAmount(orderRequest.getOrderDetails().size());
        order.setTotalPrice(total);
        order.setIsCanceled(false);
        order.setStatus("preparing");
        order.setDate(new Date());

        // Save the order entity
        orderRepository.save(order);

        // Save order details
        for (OrderDetailDTO orderDetailDTO : orderRequest.getOrderDetails()) {
            Product product = productService.findProductById(orderDetailDTO.getProductId());
            productService.decreaseQuantity(product.getId(), orderDetailDTO.getQuantity());
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            orderDetail.setProductPrice(product.getPrice());
            orderDetailRepository.save(orderDetail);
        }
        return order.getId();
    }

    public OrderRequestDTO getOrderInfo(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            List<OrderDetails> orderDetails = orderDetailRepository.findByOrderId(orderId);
            return mapOrderToDTO(order, orderDetails);
        } else {
            return null;
        }
    }

    private OrderRequestDTO mapOrderToDTO(Order order, List<OrderDetails> orderDetails) {
        OrderRequestDTO orderDTO = new OrderRequestDTO();

        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());

        // Mapping order details
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        orderDetails.forEach(orderDetail ->
                orderDetailDTOs.add(mapOrderDetailToDTO(orderDetail))
        );
        orderDTO.setOrderDetails(orderDetailDTOs);
        return orderDTO;
    }

    private OrderDetailDTO mapOrderDetailToDTO(OrderDetails orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setPrice(orderDetail.getProductPrice());
        orderDetailDTO.setProductId(orderDetail.getProduct().getId());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        return orderDetailDTO;
    }

    public boolean cancelOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (!order.getIsCanceled()) {
                LocalDateTime orderPlacementTime = order.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime currentTime = LocalDateTime.now();
                Duration duration = Duration.between(orderPlacementTime, currentTime);

                if (duration.toDays() <= 1) {
                    order.setIsCanceled(true);
                    orderRepository.save(order);
                    return true;
                }
            }
        }

        return false;
    }

    public void updateStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
        }
    }
}