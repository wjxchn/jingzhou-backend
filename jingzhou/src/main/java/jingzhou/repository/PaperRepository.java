package jingzhou.repository;


import jingzhou.POJO.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends ElasticsearchRepository<Paper, String> {

    Page<Paper> findAllByTitleLike(String title, Pageable page);

    Page<Paper> findAllByKeywordsLike(String keywords, Pageable page);

    Page<Paper> findAllByAuthorsLike(String author, Pageable page);

    Paper findByTitle(String title);
}
