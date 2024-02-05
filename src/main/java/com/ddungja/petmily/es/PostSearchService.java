package com.ddungja.petmily.es;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostSearchService {
    private final PostElasticSearchRepository postRepository;

    public Iterable<Product> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    public Product insertProduct(Product product) {
        return postRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Product product, int id) {
        Product product1 = postRepository.findById(id).get();
        product1.setDescription(product.getDescription());
        product1.setName(product.getName());
        product1.setQuantity(product.getQuantity());
        postRepository.save(product1);
        return product1;
    }

    @Transactional
    public void deleteProduct(int id) {
        postRepository.deleteById(id);
    }
}
