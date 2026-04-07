package com.example.exam.controller;

import com.example.exam.entity.ProdutoEntity;
import com.example.exam.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> findAll() {
        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoEntity>> findById(@PathVariable Long id) {
        Optional<ProdutoEntity> response = produtoService.findById(id);

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> save(@RequestBody ProdutoEntity produtoEntity) {
        produtoService.save(produtoEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> update(@PathVariable Long id, @RequestBody ProdutoEntity produtoEntity) {
        Optional<ProdutoEntity> response = produtoService.findById(id);

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(produtoService.update(id, produtoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<ProdutoEntity> response = produtoService.findById(id);

        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
