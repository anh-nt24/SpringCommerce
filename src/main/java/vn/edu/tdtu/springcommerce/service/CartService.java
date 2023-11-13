package vn.edu.tdtu.springcommerce.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.tdtu.springcommerce.dto.CartDTO;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.entity.Cart;
import vn.edu.tdtu.springcommerce.entity.CartItem;
import vn.edu.tdtu.springcommerce.entity.Product;
import vn.edu.tdtu.springcommerce.repository.AccountRepository;
import vn.edu.tdtu.springcommerce.repository.CartItemRepository;
import vn.edu.tdtu.springcommerce.repository.CartRepository;
import vn.edu.tdtu.springcommerce.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;

    public Integer addCart(CartDTO cartDto, Integer id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            Cart cart = cartRepository.findByAccountId(account.getId());

            if (cart == null) {
                cart = new Cart();
                cart.setAccount(account);
                cartRepository.save(cart);
            }

            Product product = productRepository.findById(cartDto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartDto.getQuantity());
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartDto.getQuantity());
                cartItemRepository.save(cartItem);
            }

            return cartItem.getId();
        }

        throw new EntityNotFoundException("Account not found");
    }

    public void updateCartItemQuantity(Integer cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    public List<CartDTO> getAllCartItems(Integer id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            Cart cart = cartRepository.findByAccountId(account.getId());

            if (cart != null) {
                List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
                return mapCartItemsToDTOs(cartItems);
            }
        }
        return List.of();
    }

    private List<CartDTO> mapCartItemsToDTOs(List<CartItem> cartItems) {
        List<CartDTO> cartDTOs = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            cartDTOs.add(mapCartItemToDTO(cartItem));
        }

        return cartDTOs;
    }

    private CartDTO mapCartItemToDTO(CartItem cartItem) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(cartItem.getProduct().getId());
        cartDTO.setQuantity(cartItem.getQuantity());
        // map other properties as needed
        return cartDTO;
    }
}
