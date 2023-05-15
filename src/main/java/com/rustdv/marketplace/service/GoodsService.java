package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsService {

    private final GoodsRepository goodsRepository;

    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    public List<ReadGoodsDto> findAll() {
        return goodsRepository.findAll()
                .stream()
                .map(readGoodsDtoMapper::map)
                .toList();
    }
}
