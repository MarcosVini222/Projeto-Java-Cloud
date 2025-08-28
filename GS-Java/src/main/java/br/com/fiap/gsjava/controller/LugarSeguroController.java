package br.com.fiap.gsjava.controller;

import br.com.fiap.gsjava.model.LugarSeguro;
import br.com.fiap.gsjava.service.LugarSeguroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares-seguros")
public class LugarSeguroController {

    private final LugarSeguroService lugarSeguroService;

    public LugarSeguroController(LugarSeguroService lugarSeguroService) {
        this.lugarSeguroService = lugarSeguroService;
    }

    // GET: Listar todos os lugares seguros
    @GetMapping
    public ResponseEntity<List<LugarSeguro>> listarTodos() {
        List<LugarSeguro> lugares = lugarSeguroService.listarTodos();
        return ResponseEntity.ok(lugares);
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<LugarSeguro> buscarPorId(@PathVariable Long id) {
        return lugarSeguroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar novo lugar seguro
    @PostMapping
    public ResponseEntity<LugarSeguro> salvar(@RequestBody LugarSeguro lugarSeguro) {
        LugarSeguro salvo = lugarSeguroService.salvar(lugarSeguro);
        return ResponseEntity.ok(salvo);
    }

    // DELETE: Deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lugarSeguroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
