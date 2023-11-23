//package com.ddungja.petmily.es.service;
//
//import com.ddungja.petmily.es.domain.Product;
//import com.ddungja.petmily.es.repository.PostElasticSearchRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class PostSearchService {
//    private final PostElasticSearchRepository postRepository;
//
//    public Iterable<Product> findAll() {
//        return postRepository.findAll();
//    }
//
//    public Product insertProduct(Product product) {
//        return postRepository.save(product);
//    }
//
//    public Product updateProduct(Product product, int id) {
//        Product product1 = postRepository.findById(id).get();
//        product1.setDescription(product.getDescription());
//        product1.setName(product.getName());
//        product1.setQuantity(product.getQuantity());
//        return product1;
//    }
//
//    public void deleteProduct(int id) {
//        postRepository.deleteById(id);
//    }
//}
