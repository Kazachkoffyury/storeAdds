package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")


public class AdsController {

private final AdsService adsService;
private final CommentService commentService;

    public AdsController(AdsService adsService, CommentService commentService) {
        this.adsService = adsService;
        this.commentService = commentService;
    }

    /** метод получения всех объявлений
     *
     * @return
     */
    @GetMapping
    public  ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    /** создание объявления
     *
     * @param ads
     * @return
     */
    @PostMapping()
    public ResponseEntity<AdsDto> createAds (@RequestBody CreateAdsDto ads) {

        return ResponseEntity.ok(adsService.createAds(ads));
    }

    //TODO!!!!!

    @GetMapping("/me")
    public  ResponseEntity<String> getAdsMe() {
        return  new ResponseEntity<>("показаны Ваши объявления", HttpStatus.OK);
    }



    @GetMapping("{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments( @PathVariable Integer ad_pk) {
        return ResponseEntity.ok(commentService.getAdsAllComments(ad_pk));
    }


    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComment (@PathVariable ("ad_pk") String adPk, @RequestBody AdsCommentDto adsCommentDto) {
      int pk = Integer.parseInt(adPk);
        AdsCommentDto adsComment = commentService.createComment(pk, adsCommentDto);
        if (adsComment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsComment);
    }


    @DeleteMapping("/comment{id}")
    public ResponseEntity<String> deleteAdsComment (@PathVariable("id") String id) {

        int adsPk = Integer.parseInt(id);
        commentService.deleteAdsComment(adsPk);
        return  ResponseEntity.ok().build();
    }


    @GetMapping("/comment{id}")
    public  ResponseEntity<AdsCommentDto> getAdsComment(@PathVariable int id) {

        return   ResponseEntity.ok(commentService.getAdsComment(id));
    }

    @PutMapping("/comment{id}")
    public  ResponseEntity<AdsCommentDto> updateAdsComment(@PathVariable int id,@RequestBody AdsCommentDto adsCommentDto) {
        commentService.updateAdsComment(id, adsCommentDto);
        return   ResponseEntity.ok(adsCommentDto);
    }

    /** Метод по удаленияю объявления по id
     *
     * @param id
     * @return статус
     */
    @DeleteMapping("{id}")
    public ResponseEntity <Ads> removeAds (@PathVariable int id) {
        adsService.removeAds(id);
        return  ResponseEntity.ok().build();
    }

    /** получение объявления по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public  ResponseEntity<Ads> getAds(@PathVariable int id) {
        Ads ads = adsService.findAds(id);
        if (ads == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ads);
    }

    /** метод редактирования обьявления
     *
     * @param id
     * @param adsDto
     * @return
     */
    @PatchMapping("/{id}")
    public  ResponseEntity<AdsDto> updateAds(@PathVariable int id, @RequestBody AdsDto adsDto) {
        AdsDto ads = adsService.updateAds(id, adsDto);
        if (ads == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsDto);
    }













}
