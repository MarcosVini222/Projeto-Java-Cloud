package br.com.fiap.gsjava.service;

import br.com.fiap.gsjava.model.LugarSeguro;
import br.com.fiap.gsjava.repository.LugarSeguroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LugarSeguroService {

    private final LugarSeguroRepository lugarSeguroRepository;

    public LugarSeguroService(LugarSeguroRepository lugarSeguroRepository) {
        this.lugarSeguroRepository = lugarSeguroRepository;
    }

    public List<LugarSeguro> listarTodos() {
        return lugarSeguroRepository.findAll();
    }

    public Optional<LugarSeguro> buscarPorId(Long id) {
        return lugarSeguroRepository.findById(id);
    }

    public LugarSeguro salvar(LugarSeguro lugarSeguro) {
        return lugarSeguroRepository.save(lugarSeguro);
    }

    public void deletar(Long id) {
        lugarSeguroRepository.deleteById(id);
    }
}

