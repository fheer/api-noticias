package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Data
@Entity
@Table(name="news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnews")
    private Integer idnews;

    @Column(name = "title")
    private String title;

    @Column(name = "news")
    private String news;

    @Column(name = "date")
    private String date;

    @Column(name = "hour")
    private String hour;

    @Column(name = "image")
    private String image;

    @Column(name = "idcategory")
    private int idcategory;

    @Column(name = "iduser")
    private int iduser;

    public NewsEntity mapperNewsEntityDto(Optional<NewsEntity> newsEntity) {

        NewsEntity newsEntityDto = new NewsEntity();

        newsEntityDto.setTitle(newsEntity.get().getTitle());
        newsEntityDto.setNews(newsEntity.get().getNews());
        newsEntityDto.setDate(newsEntity.get().getDate());
        newsEntityDto.setHour(newsEntity.get().getHour());
        newsEntityDto.setIdcategory(newsEntity.get().getIdcategory());
        newsEntityDto.setIduser(newsEntity.get().getIduser());
        newsEntityDto.setImage(newsEntity.get().getImage());

        return newsEntityDto;
    }

    public NewsEntity saveNews(NewsEntity newsEntity) {

        NewsEntity newsEntityDto = new NewsEntity();

        newsEntityDto.setTitle(newsEntity.getTitle());
        newsEntityDto.setNews(newsEntity.getNews());
        newsEntityDto.setDate(newsEntity.getDate());
        newsEntityDto.setHour(newsEntity.getHour());
        newsEntityDto.setIdcategory(newsEntity.getIdcategory());
        newsEntityDto.setIduser(newsEntity.getIduser());
        newsEntityDto.setImage(newsEntity.getImage());

        return newsEntityDto;
    }
}
