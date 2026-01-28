package cardapiodigital.cardapio.controller;
import cardapiodigital.cardapio.dto.PedidoRequestDTO;
import cardapiodigital.cardapio.dto.PedidoResponseDTO;
import cardapiodigital.cardapio.dto.ProdutoResponseDTO;
import cardapiodigital.cardapio.model.Pedido;
import cardapiodigital.cardapio.model.enums.StatusPedido;
import cardapiodigital.cardapio.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody @Valid PedidoRequestDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<PedidoResponseDTO> pedidos;
        pedidos = pedidoService.listarTodosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/cozinha")
    public List<PedidoResponseDTO> listarParaCozinha() {
        return pedidoService.listarParaCozinha();
    }

    @GetMapping
    public List<PedidoResponseDTO> listarPorMesa(Integer mesa) {
        return pedidoService.listarPorMesa(mesa);
    }

    @GetMapping("/status")
    public List<PedidoResponseDTO> listarPorStatus(@RequestParam StatusPedido Status) {
        return pedidoService.listarPorStatus(Status);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido novoStatus) {

        PedidoResponseDTO pedidoAtualizado = pedidoService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(pedidoAtualizado);
    }
}
