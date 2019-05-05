package org.javatraining.thymeleafonetomanycrud.controller;

import org.javatraining.thymeleafonetomanycrud.entity.Product;
import org.javatraining.thymeleafonetomanycrud.repository.ProductCategoryRepository;
import org.javatraining.thymeleafonetomanycrud.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    private final int PAGE_SIZE = 4;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductCategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public ProductController(ProductCategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/edit-product/{id}")
    public String addProduct(@PathVariable("id") int id, Model model) {
        logger.info("Edit product, id = {}", id);
        if (id == 0) {
            Product product = new Product();
            product.setId(0);
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryRepository.findAll());
        } else {
            Product product = productRepository
                    .findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryRepository.findAll());

        }
        return "edit-product";
    }

    @GetMapping("/")
    public String getProductList(@RequestParam("page") Optional<Integer> page, Model model) {
        logger.info("Root page");
        int currentPage = page.orElse(1);
        PageRequest pageable = PageRequest.of(currentPage - 1, PAGE_SIZE,
                new Sort(Sort.Direction.ASC, "category_id", "price"));
        List<Product> productList = productRepository
                .findAll(pageable)
                .get()
                .collect(Collectors.toList());
        model.addAttribute("products", productList);
        model.addAttribute("pageCount",
                Math.ceil(productRepository.count() / (double) PAGE_SIZE));
        model.addAttribute("currentPage", currentPage);
        return "index";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model) {
        logger.info("Delete product, id = {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productRepository.delete(product);
        return "redirect:/";
    }

    //@PostMapping("/product/save/{id}")
    @PostMapping("/{id}")
    public String saveProduct(@PathVariable("id") int id, Product product, Model model) {
        logger.info("Save product, id = {}", id);
        productRepository.save(product);
        return "redirect:/";
    }


}
