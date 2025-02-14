package com.youcode.sudest_market.web.vm.mapper;

import com.youcode.sudest_market.domain.Product;
import com.youcode.sudest_market.web.vm.product.ProductVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductVmMapper {
    ProductVM toVM(Product product);
    Product toProduct(ProductVM productVM);
}
