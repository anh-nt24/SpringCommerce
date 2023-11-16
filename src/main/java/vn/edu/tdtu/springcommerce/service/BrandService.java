package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.dto.BrandDTO;
import vn.edu.tdtu.springcommerce.entity.Brand;
import vn.edu.tdtu.springcommerce.repository.BrandRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public Integer addBrand(BrandDTO brandDto) {
        Brand brand = mapDTOtoBrand(brandDto);
        brand.setIsActive(true);
        brandRepository.save(brand);
        return brand.getId();
    }

    public void updateBrand(Integer brandId, BrandDTO brandDto) {
        Optional<Brand> optionalBrand = brandRepository.findByIdAndIsActiveTrue(brandId);

        if (optionalBrand.isPresent()) {
            Brand existingBrand = optionalBrand.get();

            // Update the brand details
            existingBrand.setName(brandDto.getName());
            brandRepository.save(existingBrand);
        }
    }


    public Boolean deleteBrand(Integer brandId) {
        try {
            Brand existingBrand = brandRepository.findById(brandId).orElse(null);
            if (existingBrand != null) {
                existingBrand.setIsActive(false);
                brandRepository.save(existingBrand);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<BrandDTO> getAllCategories() {
        List<Brand> activeCategories = brandRepository.findByIsActiveTrue();
        List<BrandDTO> brandDTOs = new ArrayList<>();

        for (Brand brand : activeCategories) {
            BrandDTO brandDTO = mapBrandToDTO(brand);
            brandDTOs.add(brandDTO);
        }

        return brandDTOs;
    }

    private BrandDTO mapBrandToDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }

    private Brand mapDTOtoBrand(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        return brand;
    }
}
