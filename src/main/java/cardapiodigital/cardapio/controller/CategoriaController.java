package cardapiodigital.cardapio.controller;

import cardapiodigital.cardapio.dto.CategoriaRequestDTO;
import cardapiodigital.cardapio.dto.CategoriaResponseDTO;
import cardapiodigital.cardapio.model.Categoria;
import cardapiodigital.cardapio.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody @Valid CategoriaRequestDTO categoriaDTO) {
       Categoria novaCategoria = categoriaService.cadastrarCategoria(categoriaDTO);
       return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        List<CategoriaResponseDTO> categorias;
        categorias = categoriaService.listarCategoria();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> listarPorId(@PathVariable Long id) {
        CategoriaResponseDTO categoria = categoriaService.listarPorCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaRequestDTO categoriaDTO
    ) {
        Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaDTO);

        return ResponseEntity.ok(new CategoriaResponseDTO(categoriaAtualizada));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
