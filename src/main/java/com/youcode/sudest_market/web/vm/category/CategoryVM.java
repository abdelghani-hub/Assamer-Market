package com.youcode.sudest_market.web.vm.category;

import com.youcode.sudest_market.annotation.UniqueField;
import com.youcode.sudest_market.repository.CategoryRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryVM {

    @NotBlank
    @Size(min = 3, max = 50)
    @UniqueField(
            fieldName = "name",
            repository = CategoryRepository.class,
            message = "Category already exists"
    )
    private String name;
}
