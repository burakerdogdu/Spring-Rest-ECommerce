package com.works.restcontrollers;

import com.works.entities.Images;
import com.works.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageRestController {
    final ImageService imageService;
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Images images){
        return imageService.save(images);
    }
    @PostMapping("/image")
    public ResponseEntity getid(@RequestBody Images images ){
        return imageService.getimage(images.getPath());
    }
}
