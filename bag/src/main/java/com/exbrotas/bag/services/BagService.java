package com.exbrotas.bag.services;

import com.exbrotas.bag.config.exceptionHandler.exceptions.MyBadRequestException;
import com.exbrotas.bag.config.exceptionHandler.exceptions.NotFoundException;
import com.exbrotas.bag.dtos.request.bag.BagAtualizarDto;
import com.exbrotas.bag.dtos.request.bag.BagCriarDto;
import com.exbrotas.bag.dtos.response.bag.BagGetResponseDto;
import com.exbrotas.bag.dtos.security.SystemUser;
import com.exbrotas.bag.entities.Bag;
import com.exbrotas.bag.mappers.bag.BagMapper;
import com.exbrotas.bag.repositories.BagRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BagService {

  private final BagRepository bagRepository;

  public BagService(BagRepository bagRepository) {
    this.bagRepository = bagRepository;
  }

  public List<BagGetResponseDto> listar() {
    return bagRepository.findAllProjectedBy().stream().map(BagMapper::toBagGetResponseDto).toList();
  }

  public void criar(BagCriarDto dto){
    if(bagRepository.existsByNumero(dto.getNumero())){
      throw new MyBadRequestException("Não é possível criar duas bags com o mesmo numero de identificação");
    }
    Bag bag = BagMapper.fromDto(dto);

    bagRepository.save(bag);
  }

  public void atualizar(BagAtualizarDto dto){
    Bag bag = bagRepository.findById(dto.getId()).orElseThrow(()-> new NotFoundException("Bag não encontrada"));
    if(!dto.getNumero().equals(bag.getNumero()) && bagRepository.existsByNumero(dto.getNumero())){
      throw new MyBadRequestException("Não é possível atualizar bag para o numero de identificação ja existente");
    }
    BagMapper.updateBag(dto, bag);

    bagRepository.save(bag);
  }

  public void atualizarDispo(Integer id){
    Bag bag = bagRepository.findById(id).orElseThrow(()-> new NotFoundException("Bag não encontrada"));
    bag.setDisponivel(!bag.getDisponivel());
    bagRepository.save(bag);
  }

  public void changeStatus(Integer id, SystemUser user){
    Bag bag = bagRepository.findById(id).orElseThrow(()-> new NotFoundException("Bag não encontrada"));
    bag.setIsCancel(!bag.getIsCancel());
    bag.setDeletedBy(user.id());

    bagRepository.save(bag);
  }
}