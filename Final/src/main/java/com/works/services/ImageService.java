package com.works.services;

import com.works.configs.Rest;
import com.works.entities.Images;
import com.works.repositories.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageService {
final ImagesRepository imagesRepository;
public ResponseEntity save(Images images){
   Optional<Images> ls = imagesRepository.findByPathEquals(images.getPath());
   if (ls.isPresent()){
       return new ResponseEntity<>("kayıtlı resim",HttpStatus.OK);
   }else {
       imagesRepository.save(images);
       Rest rest = new Rest(true,images);
       return new ResponseEntity(rest, HttpStatus.OK);
   }

}

public ResponseEntity getimage(String path){
  Optional<Images> image= imagesRepository.findByPathEquals(path);
  Rest rest = new Rest(true,image);
  return new ResponseEntity(rest,HttpStatus.OK);

}
}
