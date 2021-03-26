package com.example.orderingsystem.product;

import com.example.orderingsystem.product.dto.ProductDto;
import com.example.orderingsystem.product.model.Product;
import com.example.orderingsystem.product_type.dto.ProductTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Validated ProductDto productDto) {
        ProductTypeDto productTypeDto = productDto.getProductTypeDto();
        Assert.notNull(productTypeDto, "productTypeDto can't be null");

        Product product = productService.createProduct(productDto.getName(), productDto.getDescription(), productDto.getStock(),
                productTypeDto.getName(), productTypeDto.getDescription());
        //todo convert to dto and return
        return new ResponseEntity(product, OK);
    }

    @GetMapping("/{productId}/stock") // todo review
    public ResponseEntity getStockForProduct(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);

        return new ResponseEntity(product.getStock(), OK);
    }

    @PostMapping("/{productId}/stock") // todo review
    public ResponseEntity stockProduct(@PathVariable("productId") Long productId, @RequestBody Long stock) {
        Long newProductStock = productService.stockProduct(productId, stock);

        return new ResponseEntity(newProductStock, OK);
    }

    // todo extract to AttributeValueController and make it accept JSON with fields productId and attributeValueId
    @PostMapping("/{productId}/attribute-values/{attributeValueId}")
    public ResponseEntity addAttributeValueToProduct(@PathVariable("productId") Long productId,
                                                     @PathVariable("attributeValueId") Long attributeValueId) {
        productService.addAttributeValuesToProduct(productId, attributeValueId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
