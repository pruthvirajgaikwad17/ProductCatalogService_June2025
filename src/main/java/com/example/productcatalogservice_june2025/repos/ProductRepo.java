package com.example.productcatalogservice_june2025.repos;

import com.example.productcatalogservice_june2025.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    List<Product> findProductByPriceBetween(Double price1, Double price2);

    List<Product> findAllByOrderByPrice();

//   @Query("select p.name from Product p where p.id = ?1") positional way    @Query("select p.name from Product p where p.id = ?1") // positional way
    @Query("SELECT p.name FROM Product p WHERE p.id=:id") // name association
    String getMeNameOfMyFavouriteProductById(Long id);
}
