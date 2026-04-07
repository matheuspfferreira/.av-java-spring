package com.example.exam.service;

import com.example.exam.entity.ProdutoEntity;
import com.example.exam.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoEntity> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public ProdutoEntity save(ProdutoEntity produtoEntity) {
        return produtoRepository.save(produtoEntity);
    }

    public ProdutoEntity update(Long id, ProdutoEntity produtoEntity) {
        ProdutoEntity response = produtoRepository.findById(id).orElse(null);
        assert response != null;

        response.setNome(produtoEntity.getNome());
        response.setDescricao(produtoEntity.getDescricao());
        response.setPrecoUnitario(produtoEntity.getPrecoUnitario());
        response.setStatus(produtoEntity.getStatus());

        return produtoRepository.save(response);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

}
