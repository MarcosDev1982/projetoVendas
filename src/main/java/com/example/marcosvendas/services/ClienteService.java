package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Cidade;
import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.domain.Endereco;
import com.example.marcosvendas.domain.enums.TipoCliente;
import com.example.marcosvendas.dto.ClienteDTO;
import com.example.marcosvendas.dto.ClienteNewDTO;
import com.example.marcosvendas.repository.ClienteRepositories;
import com.example.marcosvendas.repository.EnderecoRepositories;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositories clienteRepositories;

    @Autowired
    private EnderecoRepositories enderecoRepositories;

    public List<Cliente> findAll() {
        return clienteRepositories.findAll();


    }

    public Cliente finById(Integer id) {
        Optional<Cliente> obj = clienteRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cli) {
        cli.setId(null);
        cli = clienteRepositories.save(cli);
        enderecoRepositories.saveAll(cli.getEnderecos());
        return cli;

    }

    public Cliente update(Cliente obj) {
        Cliente newObj = finById(obj.getId());
        updateData(newObj, obj);
        return clienteRepositories.save(newObj);
    }

    public void delete(Integer id) {
        finById(id);
        clienteRepositories.deleteById(id);
    }

    public Page<Cliente> findPage(Integer page, Integer lisnesPage, String orderBy, String decretion) {
        PageRequest pageRequest = PageRequest.of(page, lisnesPage, Sort.Direction.valueOf(decretion), orderBy);
        return clienteRepositories.findAll(pageRequest);
    }


    public Cliente fromDTO(ClienteDTO objDto) {

        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente newCleinte = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), newCleinte, cid);
        newCleinte.getEnderecos().add(end);
        newCleinte.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            newCleinte.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            newCleinte.getTelefones().add(objDto.getTelefone3());
        }
        return newCleinte;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setNome(obj.getEmail());
    }


}
