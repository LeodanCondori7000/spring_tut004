package com.leodan.springboot.di.app.springbootdi2.services;

import java.util.List;
import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leodan.springboot.di.app.springbootdi2.models.Product;
import com.leodan.springboot.di.app.springbootdi2.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  // one way of injecting(prefered way)
  // @Autowired
  // private ProductRepository repository;
  //-----------

  // another way of injecting a dependency
  // private ProductRepository repository;
  // @Autowired
  // public void setRepository(ProductRepository repository) {
  //   this.repository = repository;
  // }
  //-----------

  // a third way of injecting(this does not require @Autowired keyword)
  private ProductRepository repository;
  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }
  //-----------

  @Override
  public List<Product> findAll() {

    return repository.findAll().stream().map(p -> {
      Double priceTax = p.getPrice() * 1.25d;
      Product newProd = (Product) p.clone();
      newProd.setPrice(priceTax.longValue());
      return newProd;
    }).collect(Collectors.toList());
  }

  @Override
  public Product findById(Long id) {
    return repository.findById(id);
  }
}
