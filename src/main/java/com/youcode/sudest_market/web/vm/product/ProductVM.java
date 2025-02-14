package com.youcode.sudest_market.web.vm.product;

import com.youcode.sudest_market.domain.enums.ProductStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
    public class ProductVM {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @Size(max = 100, message = "Slug cannot exceed 100 characters")
    private String slug;

    @NotBlank(message = "Summary is required")
    @Size(min = 10, max = 255, message = "Summary must be between 10 and 255 characters")
    private String summary;

    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 1000, message = "Description must be between 20 and 1000 characters")
    private String description;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price cannot be negative!")
    private Double price;

    @NotNull(message = "Category is required")
    private String categoryName;

    private ProductStatus status;
}