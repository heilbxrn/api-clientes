package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequest;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public String post(@RequestBody ClienteRequest request) {
        try{
            var cliente = new Cliente();
            cliente.setNome(request.nome());
            cliente.setEmail(request.email());
            cliente.setTelefone(request.telefone());

            clienteRepository.inserir(cliente);

            return "Cliente cadastrado com sucesso!";
        }
        catch (Exception e){
            return "Falha ao inserir cliente: " + e.getMessage();
        }
    }

    @PutMapping("{id}")
    public String put(@PathVariable Integer id, @RequestBody ClienteRequest request) {
        try{

            var cliente = new Cliente();
            cliente.setId(id);
            cliente.setNome(request.nome());
            cliente.setEmail(request.email());
            cliente.setTelefone(request.telefone());

            if(clienteRepository.atualizar(cliente)) {
                return "Cliente atualizado com sucesso.";
            }
            else{
                return "O cliente não foi encontrado para edição.";
            }

        } catch (Exception e) {
            return "Falha ao atualizar cliente: " + e.getMessage();
        }
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        try{

            if(clienteRepository.excluir(id)){
                return "Cliente excluído com sucesso";
            }
            else{
                return "O cliente não foi encontrado para exclusão";
            }

        } catch (Exception e) {
            return "Falha ao excluir cliente: " + e.getMessage();
        }
    }

    @GetMapping("{nome}")
    public List<Cliente> get(@PathVariable String nome) {

        try{

            return clienteRepository.obterPorNome(nome);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
