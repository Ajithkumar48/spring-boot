package com.example.demo.product.service;

import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(new Product("Test Product", 10.0)));
        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    @Test
    void getProductById() {
        Product product = new Product("Test Product", 10.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> result = productService.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
    }

    @Test
    void createProduct() {
        Product product = new Product("Test Product", 10.0);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product created = productService.createProduct(product);
        assertEquals("Test Product", created.getName());
    }

    @Test
    void updateProduct() {
        Product existingProduct = new Product("Old Name", 10.0);
        Product updatedDetails = new Product("New Name", 20.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedDetails);

        Product updated = productService.updateProduct(1L, updatedDetails);

        assertEquals("New Name", updated.getName());
        assertEquals(20.0, updated.getPrice());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
