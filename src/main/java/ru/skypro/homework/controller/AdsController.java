package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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

    /** поиск обьявления
     *
     * @param search
     * @return
     */
    @GetMapping(params = {"search"})
    public ResponseEntity<ResponseWrapperAdsDto> findAds(@RequestParam(required = false)String search) {
        return ResponseEntity.ok(adsService.findAds(search));
    }
    /** создание объявления
     *
     * @param ads
     * @return
     */
    @Operation(
            tags = "Объявления (AdsController)",
            summary = "Добавление объявления (addAds)"
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping()
    public ResponseEntity<AdsDto> createAds(@RequestPart("properties") @Valid CreateAdsDto ads) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(adsService.createAds(ads,authentication));
    }



    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public  ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(adsService.getAdsMe(authentication.getName()));
    }



    @GetMapping("{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments( @PathVariable Integer ad_pk) {
        return ResponseEntity.ok(commentService.getAdsAllComments(ad_pk));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<AdsCommentDto> addAdsComment (@Positive @PathVariable int ad_pk, @Valid @RequestBody AdsCommentDto adsCommentDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(commentService.createComment(ad_pk, adsCommentDto, authentication));
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("{ad_pk}/comments{id}")
    public ResponseEntity<Void> deleteAdsComment (@Positive @PathVariable int ad_pk,
                                                  @Positive @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        commentService.deleteAdsComment(ad_pk, id, authentication.getName(), userDetails);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/comment{id}")
    public  ResponseEntity<AdsCommentDto> getAdsComment(@PathVariable int id) {

        return   ResponseEntity.ok(commentService.getAdsComment(id));
    }

    @PutMapping("{ad_pk}/comments/{id}")
    public  ResponseEntity<AdsCommentDto> updateAdsComment(@Positive @PathVariable int ad_pk,
                                                           @Positive@PathVariable int id,
                                                           @Valid @RequestBody AdsCommentDto comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(commentService.updateAdsComment(ad_pk,id, comment, authentication.getName(), userDetails));
    }

    /** Метод по удалению объявления по id
     *
     * @param id
     * @return статус
     */
    @DeleteMapping("{id}")
    public ResponseEntity <Ads> removeAds (@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        adsService.removeAds(id, authentication.getName(), userDetails);
        return ResponseEntity.ok().build();

    }

    /** получение объявления по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public  ResponseEntity<Ads> getAds(@PathVariable int id) {
        Ads ads = adsService.getAds(id);
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
    public  ResponseEntity<AdsDto> updateAds(@PathVariable int id,
                                             @RequestPart("properties") @Valid AdsDto adsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(adsService.updateAds(id, adsDto, authentication.getName(), userDetails));
    }













}