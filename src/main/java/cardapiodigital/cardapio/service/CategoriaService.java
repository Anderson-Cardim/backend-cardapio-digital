package cardapiodigital.cardapio.service;

import cardapiodigital.cardapio.dto.CategoriaRequestDTO;
import cardapiodigital.cardapio.dto.CategoriaResponseDTO;
import cardapiodigital.cardapio.model.Categoria;
import cardapiodigital.cardapio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria cadastrarCategoria(CategoriaRequestDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setAtivo(categoriaDTO.getAtivo());

        return categoriaRepository.save(categoria);
    }

    public List<CategoriaResponseDTO> listarCategoria() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(CategoriaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO listarPorCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaResponseDTO(categoria);
    }

    public Categoria atualizarCategoria(Long id, CategoriaRequestDTO categoriaDTO) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        categoriaExistente.setNome(categoriaDTO.getNome());
        categoriaExistente.setAtivo(categoriaDTO.getAtivo());

        return categoriaRepository.save(categoriaExistente);
    }

    public void deletarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }

}
