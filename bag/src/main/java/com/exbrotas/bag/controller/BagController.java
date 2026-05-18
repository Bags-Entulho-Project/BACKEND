package com.exbrotas.bag.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bag")
public class BagController {

  @GetMapping
  public ResponseEntity<?> getAllBag() {

    return ResponseEntity.ok().build();
  }
}
