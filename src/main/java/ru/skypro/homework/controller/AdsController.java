package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ResponseWrapperAdsTo;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.impl.AdsServiceImpl;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")


public class AdsController {



    @GetMapping
    public  ResponseEntity<String> getAllAds() {
        return new ResponseEntity<>("все обьявления", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addAds () {
        return  new ResponseEntity<>("обьявление добавлено", HttpStatus.OK);
    }

    @GetMapping("/me")
    public  ResponseEntity<String> getAdsMe() {
        return  new ResponseEntity<>("показаны Ваши объявления", HttpStatus.OK);
    }

    @GetMapping("/comment")
    public  ResponseEntity<String> getAdsComments() {
        return  new ResponseEntity<>("показаны комментарии к товару", HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<String> addAdsComments () {
        return  new ResponseEntity<>("добавлен комментарий к товару", HttpStatus.OK);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteAdsComment () {
        return  new ResponseEntity<>("комментарий к товару удален", HttpStatus.OK);
    }

    @GetMapping("/comment{id}")
    public  ResponseEntity<String> getAdsComment(@PathVariable int id) {
        return  new ResponseEntity<>("показан комментарий № {id} к товару", HttpStatus.OK);
    }

    @PutMapping("/comment{id}")
    public  ResponseEntity<String> updateAdsComment(@PathVariable int id) {
        return  new ResponseEntity<>("изменен комментарий № {id} к товару", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removeAds (@PathVariable int id) {
        return  new ResponseEntity<>("товар удален!", HttpStatus.OK);
    }

    @GetMapping({"id"})
    public  ResponseEntity<String> getAds(@PathVariable int id) {
        return new ResponseEntity<>("получено объявление", HttpStatus.OK);
    }

    @PutMapping({"id"})
    public  ResponseEntity<String> updateAds(@PathVariable int id) {
        return new ResponseEntity<>("объявление обновлено", HttpStatus.OK);
    }













}
