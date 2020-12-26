package jingzhou.repository;


import jingzhou.POJO.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends ElasticsearchRepository<Paper, String> {

    Page<Paper> findAllByTitleLike(String title, Pageable page);

    Page<Paper> findAllByKeywordsLikeAndKeywordsLike(String keyword1, String keyword2, Pageable page);

    Page<Paper> findAllByKeywordsLike(String keyword, Pageable page);

    Page<Paper> findAllByAuthorsLike(String author, Pageable page);

    Page<Paper> findAllByAuthors(String keyword, Pageable page);

    Paper findByTitle(String title);

    Paper findByPaperid(String id);
}
