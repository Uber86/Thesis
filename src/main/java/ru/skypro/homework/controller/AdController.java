package ru.skypro.homework.controller;

import com.sun.security.auth.UserPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;

@RestController
@Tag(name = "Объявления")
@RequiredArgsConstructor
public class AdController {

    @GetMapping("/ads")
    public ResponseEntity<Ads> getAllAds(){

        return ResponseEntity.ok().build();
    }

    @PostMapping("/ads")
    public ResponseEntity<Ad> addAd(@RequestBody Ad ad){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<Ad> getAd (@PathVariable Integer id){

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Ad> deleteAd (@PathVariable Integer id){

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable("id") Integer id,
            @RequestBody CreateOrUpdateAd createOrUpdateAd){

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ads/me")
    public ResponseEntity<Ads> getAdsMe(@AuthenticationPrincipal UserPrincipal userPrincipal){

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<byte[]> updateImage(
            @PathVariable("id") Integer id,
            @RequestParam("image") MultipartFile image){

        return ResponseEntity.ok().build();
    }

}
