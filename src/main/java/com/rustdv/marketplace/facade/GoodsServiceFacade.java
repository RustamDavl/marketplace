package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;

public interface GoodsServiceFacade {

    ReadGoodsDto addGoods(Long shopId, CreateUpdateGoodsDto object);

}
