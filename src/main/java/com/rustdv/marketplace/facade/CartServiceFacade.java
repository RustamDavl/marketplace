package com.rustdv.marketplace.facade;



import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;

import java.util.List;

public interface CartServiceFacade {

    ReadCartDto addGoods(CreateUpdateCartDto object);

    ReadCartDto buyGoods(CreateUpdateCartDto object);

    List<ReadCartDto> findAllByBuyerId(Long buyerId);
}
