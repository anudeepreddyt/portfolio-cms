package com.portfolio.cms.controller;
import com.portfolio.cms.entity.Skill; import com.portfolio.cms.repository.SkillRepository; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/skills") @RequiredArgsConstructor
public class SkillController { private final SkillRepository repo; @GetMapping public List<Skill> all(){return repo.findAll();} @PostMapping public Skill create(@Valid @RequestBody Skill x){return repo.save(x);} @PutMapping("/{id}") public Skill update(@PathVariable Long id,@Valid @RequestBody Skill x){x.setId(id);return repo.save(x);} @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);} }
