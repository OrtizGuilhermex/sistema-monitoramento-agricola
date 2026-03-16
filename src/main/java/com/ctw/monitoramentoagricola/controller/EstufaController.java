package com.ctw.monitoramentoagricola.controller;

import com.ctw.monitoramentoagricola.dto.EstufaDTO;
import com.ctw.monitoramentoagricola.service.EstufaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( name = "Estufa", description = "busca o histórico de dados atuais e passados")
@RestController
@RequestMapping("/api/estufa")
@CrossOrigin(origins = "*")
public class EstufaController {

    @Autowired
    private EstufaService service;

    @Operation(summary = "Recuperar leitura específica por ID.", description = "Retorna os detalhes de uma medição específica (temperatura, umidade, etc) através do seu identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitura encontrada e retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma leitura encontrada para o ID informado."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os parâmetros de entrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar os dados dos sensores.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstufaDTO> getById(
            @Parameter(description = "ID da medição a ser pesquisada", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Obter telemetria em tempo real", description = "Fornece o último snapshot de dados enviado pela estufa, ideal para dashboards de monitoramento ao vivo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitura encontrada e retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma leitura encontrada persistida no banco."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os parâmetros de entrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar os dados dos sensores.")
    })
    @GetMapping("/atual")
    public ResponseEntity<EstufaDTO> getAtual() {
        EstufaDTO dados = service.getUltimaLeitura();
        return ResponseEntity.ok(dados);
    }

    @Operation(summary = "Listar histórico completo de monitoramento.", description = "Retorna uma lista cronológica de todas as medições. Nota: pode haver latência dependendo do volume de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitura encontrada e retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhuma leitura encontrada persistida no banco."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os parâmetros de entrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar os dados dos sensores.")
    })
    @GetMapping("/historico")
    public ResponseEntity<List<EstufaDTO>> getHistorico() {
        List<EstufaDTO> historico = service.getHistorico();
        return ResponseEntity.ok(historico);
    }
}
