package jingzhou.repository;

import jingzhou.POJO.Author;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ElasticsearchRepository<Author, Long> {

    Author findByName(String name);

    Author findByAuthorid(String id);

}
