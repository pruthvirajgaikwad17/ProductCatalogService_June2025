package com.example.productcatalogservice_june2025.services;

import com.example.productcatalogservice_june2025.clients.FakeStoreApiClient;
import com.example.productcatalogservice_june2025.dtos.CategoryDto;
import com.example.productcatalogservice_june2025.dtos.FakeStoreProductDto;
import com.example.productcatalogservice_june2025.dtos.ProductDto;
import com.example.productcatalogservice_june2025.models.Category;
import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// @Primary mean this service will be used
@Service
@Primary
public class FakeStoreProductService implements IProductService{

    @Autowired
    private RestTemplateBuilder  restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient  fakeStoreApiClient;

    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        // Integrate Redis here
        // First check if the product is present in the redis cache or not
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_"+id);

        if (product != null) {
            // Redis cache hit
            return product;
        }

        // Get the Product from DB and store it in the cache

        FakeStoreProductDto output = fakeStoreApiClient.getFakeStoreProduct(id);
        if(output==null) return null;

        product = from(output);

        // Before returning the product we will store it in the redis cache
        // First parameter is the name of the hashmap as there are multiple hashmap in the redis
        // Second parameter is the key we want to insert
        // Third is the value we store and return when we get the value
        // opsForHash - This means all the data will be stored in hashing format
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_"+id, product);

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(product);

        FakeStoreProductDto output = fakeStoreApiClient.createFakeStoreProduct(fakeStoreProductDtoInput);
        if(output==null) return null;
        return from(output);
    }

    @Override
    public Product replaceProduct(Product input, Long id ) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(input);
        FakeStoreProductDto output = fakeStoreApiClient.replaceFakeStoreProduct(fakeStoreProductDtoInput, id);
        if(output == null) return null;
        return from(output);
    } // Refactored

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProducts = fakeStoreApiClient.getFakeStoreProducts();

        if (fakeStoreProducts == null || fakeStoreProducts.isEmpty()) {
            return List.of(); // return empty immutable list if API gives nothing
        }

        return fakeStoreProducts.stream()
                .map(this::from)   // convert each DTO to Product
                .toList();
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto  from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory()!=null){
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}
