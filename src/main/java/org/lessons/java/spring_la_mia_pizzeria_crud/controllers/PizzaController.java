package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaRepository repository;

  @GetMapping
  public String index(Model model) {
    List<Pizza> pizzas = repository.findAll();
    model.addAttribute("pizzas", pizzas);
    return "pizzas/index";
  }

  @GetMapping("/search")
  public String searchByName(@RequestParam(name = "name") String name, Model model) {
    List<Pizza> pizzas = repository.findByNameContaining(name);
    model.addAttribute("pizzas", pizzas);
    return "pizzas/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") Integer id, Model model) {
    Pizza pizza = repository.findById(id).get();
    model.addAttribute("pizza", pizza);
    return "pizzas/show";
  }

}
