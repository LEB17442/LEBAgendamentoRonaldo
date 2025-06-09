package br.unifor.fazeragendamento.controller;

import br.unifor.fazeragendamento.AgendamentoRequestDTO;
import br.unifor.fazeragendamento.AgendamentoResponseDTO;
import br.unifor.fazeragendamento.model.Agendamento;
import br.unifor.fazeragendamento.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    // --- MÉTODO DE CONVERSÃO ---
    private AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        AgendamentoResponseDTO dto = new AgendamentoResponseDTO();
        dto.setId(agendamento.getId());
        dto.setValorServico(agendamento.getValorServico());
        return dto;
    }

     @PostMapping
    public ResponseEntity<?> agendar(
        @RequestBody AgendamentoRequestDTO dto,
        @RequestHeader("X-User-ID") Long idUsuarioLogado // Lê o cabeçalho injetado pelo Gateway
    ) {
        try {
            // Agora você pode usar o idUsuarioLogado na sua lógica de negócio!
            System.out.println("O usuário com ID " + idUsuarioLogado + " está criando um agendamento.");

            // Valida se o ID do cliente no DTO é o mesmo do usuário logado (Regra de Segurança)
            if (!dto.getIdCliente().equals(idUsuarioLogado)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não pode agendar para outro cliente.");
            }
            return new ResponseEntity<>(service.criarAgendamento(dto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Agendamento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> pegarDetalhes(@PathVariable Long id) {
        return ResponseEntity.ok(service.pegarDetalhes(id));
    }

    @GetMapping("/cliente/{clienteId}/pendentes")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPendentes(@PathVariable Long clienteId) {
        List<Agendamento> agendamentos = service.listarAgendamentosPendentesPorCliente(clienteId);
        
        List<AgendamentoResponseDTO> agendamentosDTO = agendamentos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(agendamentosDTO);
    }
}