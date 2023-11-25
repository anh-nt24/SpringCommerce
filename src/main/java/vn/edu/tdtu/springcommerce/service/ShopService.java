package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.dto.ShopDTO;
import vn.edu.tdtu.springcommerce.entity.Shop;
import vn.edu.tdtu.springcommerce.repository.AccountRepository;
import vn.edu.tdtu.springcommerce.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private AccountRepository accountRepository;
    public Integer addShop(ShopDTO shopDto) {
        Shop shop = mapDTOtoShop(shopDto);
        shop.setIsActive(true);
        shopRepository.save(shop);
        return shop.getId();
    }

    public void updateShop(Integer shopId, ShopDTO shopDto) {
        Optional<Shop> optionalShop = shopRepository.findById(shopId);

        if (optionalShop.isPresent()) {
            Shop existingShop = optionalShop.get();

            // Update the shop details
            existingShop.setShopName(shopDto.getShopName());
            if (shopDto.getDescription() == null) {
                existingShop.setDescription("");
            } else {
                existingShop.setDescription(shopDto.getDescription());
            }
            shopRepository.save(existingShop);
        }
    }

    public Boolean deleteShop(Integer shopId) {
        try {
            Shop existingShop = shopRepository.findById(shopId).orElse(null);
            if (existingShop != null) {
                existingShop.setIsActive(false);
                shopRepository.save(existingShop);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<ShopDTO> getAllShops() {
        List<Shop> activeShops = shopRepository.findAll();
        List<ShopDTO> shopDTOs = new ArrayList<>();

        for (Shop shop : activeShops) {
            ShopDTO shopDTO = mapShopToDTO(shop);
            shopDTOs.add(shopDTO);
        }

        return shopDTOs;
    }

    public ShopDTO getShopById(Integer shopId) {
        Optional<Shop> optionalShop = shopRepository.findByIdAndIsActiveTrue(shopId);

        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            return mapShopToDTO(shop);
        } else {
            return null;
        }
    }

    private ShopDTO mapShopToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName(shop.getShopName());
        shopDTO.setDescription(shop.getDescription());
        shopDTO.setOwner(shop.getOwner().getId());
        return shopDTO;
    }

    private Shop mapDTOtoShop(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setShopName(shopDTO.getShopName());
        shop.setOwner(accountRepository.findById(shopDTO.getOwner()).orElse(null));
        if (shopDTO.getDescription() == null) {
            shop.setDescription("");
        } else {
            shop.setDescription(shopDTO.getDescription());
        }
        return shop;
    }
}