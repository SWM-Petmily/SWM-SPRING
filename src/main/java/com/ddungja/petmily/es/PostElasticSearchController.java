package com.ddungja.petmily.es;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/posts")
public class PostElasticSearchController {
    private final PostSearchService postSearchService;


    @GetMapping("/findall")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(postSearchService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Product product) {
        return ResponseEntity.ok(postSearchService.insertProduct(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable int id) {
        return ResponseEntity.ok(postSearchService.updateProduct(product, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        postSearchService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
