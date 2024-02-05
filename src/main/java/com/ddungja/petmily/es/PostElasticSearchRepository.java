package com.ddungja.petmily.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostElasticSearchRepository extends ElasticsearchRepository<Product, Integer> {


}
