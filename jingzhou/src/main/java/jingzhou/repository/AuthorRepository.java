package jingzhou.repository;

import jingzhou.POJO.Author;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends ElasticsearchRepository<Author, String> {

    Author findByName(String name);

}
