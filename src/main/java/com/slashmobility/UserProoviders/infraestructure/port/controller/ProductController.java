package com.slashmobility.UserProoviders.infraestructure.port.controller;

import com.slashmobility.UserProoviders.application.command.CmdCreateProduct;
import com.slashmobility.UserProoviders.application.command.CmdUpdateProduct;
import com.slashmobility.UserProoviders.application.query.QueryProduct;
import com.slashmobility.UserProoviders.application.query.QueryProducts;
import com.slashmobility.UserProoviders.application.query.QueryProductsByCity;
import com.slashmobility.UserProoviders.application.query.QueryProductsByType;
import com.slashmobility.UserProoviders.domain.model.Product;
import com.slashmobility.UserProoviders.domain.model.Provider;
import com.slashmobility.UserProoviders.infraestructure.mapper.ProductMapper;
import com.slashmobility.UserProoviders.infraestructure.type.DefaultResponse;
import com.slashmobility.UserProoviders.infraestructure.type.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private QueryProduct queryProduct;

    @Autowired
    private QueryProducts queryProducts;

    @Autowired
    private CmdCreateProduct cmdCreateProduct;

    @Autowired
    private CmdUpdateProduct cmdUpdateProduct;

    @Autowired
    private QueryProductsByType queryProductsByType;

    @Autowired
    private QueryProductsByCity queryProductsByCity;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
        return this.queryProduct.handle(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        return this.queryProducts.handle()
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/types/{type}")
    public ResponseEntity<List<Product>> getProductsByType(@PathVariable("type") String type) {
        return this.queryProductsByType.handle(type)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/cities/{city}")
    public ResponseEntity<List<Product>> getProductsByCity(@PathVariable("city") String city) {
        return this.queryProductsByCity.handle(city)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/")
    public ResponseEntity createProduct(@RequestBody Product product) {

        return new ResponseEntity<>(this.cmdCreateProduct.handle(product), HttpStatus.OK);

    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId,
                                        @RequestBody ProductUpdateRequest productUpdateRequest) {
        Product product = this.productMapper.toDomainOfUpdateRequest(productUpdateRequest);
        product.setId(productId);
        return new ResponseEntity<>(this.cmdUpdateProduct.handle(product) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);

    }
}
