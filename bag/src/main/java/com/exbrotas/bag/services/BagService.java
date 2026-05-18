package com.exbrotas.bag.services;

import com.exbrotas.bag.repositories.BagRepository;
import org.springframework.stereotype.Service;

@Service
public class BagService {

  private final BagRepository bagRepository;

  public BagService(BagRepository bagRepository) {
    this.bagRepository = bagRepository;
  }
}
