package com.exbrotas.bag.controller;

import com.exbrotas.bag.services.BagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bag")
public class BagController {

  private final BagService bagService;

  public BagController(BagService bagService) {
    this.bagService = bagService;
  }

  @GetMapping
  public ResponseEntity<?> getAllBag() {

    return ResponseEntity.ok().build();
  }
}
