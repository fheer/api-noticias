package com.example.demo.controllers;

import com.example.demo.entities.NewsEntity;
import com.example.demo.exceptions.StorageException;
import com.example.demo.services.FileSystemStorageService;
import com.example.demo.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/news")
@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @RequestMapping(value = "/getAll")
    public ResponseEntity<List<NewsEntity>> getAllNews() {
        List<NewsEntity> reponse = newsService.getAllNews();
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @GetMapping(value="/getNews/{id}")
    public ResponseEntity<NewsEntity> listById(@PathVariable("id") Integer id) {

        Optional<NewsEntity> newsEntity = newsService.getNewsById(id);
        NewsEntity newsEntityEntityDtoMapper = new NewsEntity();

        if(newsEntity == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // convert entity to DTO
        NewsEntity newsEntityEntityDto = newsEntityEntityDtoMapper.mapperNewsEntityDto(newsEntity);

        return ResponseEntity.ok().body(newsEntityEntityDto);
    }

    @PostMapping(value = "/add")
    public void add(@RequestParam("title") String title, @RequestParam("news") String news,
                    @RequestPart("image") MultipartFile image, @RequestParam("idcategory") int idcategory,
                    @RequestParam("iduser") int iduser){

        Date date = new Date();
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterHour = new SimpleDateFormat("HH:mm:ss");
        NewsEntity newsEntity = new NewsEntity();

        if (!image.isEmpty()) {
            String fileName= fileSystemStorageService.storage(image);
            newsEntity.setTitle(title);
            newsEntity.setNews(news);
            newsEntity.setImage(fileName);
            newsEntity.setIdcategory(idcategory);
            newsEntity.setIduser(iduser);
            newsEntity.setDate(formatterDate.format(date));
            newsEntity.setHour(formatterHour.format(date));
            newsService.register(newsEntity);
        }else {
            throw new StorageException("Can not storage a empty file.");
        }


    }

    @PutMapping(value = "/update")
    public void updateNews(@RequestParam("idnews") int idnews,@RequestParam("title") String title, @RequestParam("news") String news,
                           @RequestPart("image") MultipartFile image, @RequestParam("idcategory") int idcategory,
                           @RequestParam("iduser") int iduser) {
        //newsService.delete(id);

        Date date = new Date();
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterHour = new SimpleDateFormat("HH:mm:ss");
        NewsEntity newsEntity = new NewsEntity();

        if (!image.isEmpty()) {
            String fileName= fileSystemStorageService.storage(image);
            newsEntity.setIdnews(idnews);
            newsEntity.setTitle(title);
            newsEntity.setNews(news);
            newsEntity.setImage(fileName);
            newsEntity.setIdcategory(idcategory);
            newsEntity.setIduser(iduser);
            newsEntity.setDate(formatterDate.format(date));
            newsEntity.setHour(formatterHour.format(date));
            newsService.update(newsEntity);
        }else {
            throw new StorageException("Can not storage a empty file.");
        }
    }

    /**
     * Delete method
     * @return Response Entity
     */
    @DeleteMapping(value="delete/{id}")
    public void delete(@PathVariable("id") Integer id)
    {
        newsService.delete(id);
    }
}
