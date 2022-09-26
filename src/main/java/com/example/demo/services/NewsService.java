package com.example.demo.services;

import com.example.demo.entities.NewsEntity;
import com.example.demo.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    public Optional<NewsEntity> getNewsById(Integer id) {
        return newsRepository.findById(id);
    }

    public NewsEntity register(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    /**
     *
     * @param newsEntity
     * @return News Entity data save
     */
    public NewsEntity update(@RequestBody NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    /**
     * Delete by id Method
     * @param id
     */
    public void delete(Integer id) {
        newsRepository.deleteById(id);
    }

}
