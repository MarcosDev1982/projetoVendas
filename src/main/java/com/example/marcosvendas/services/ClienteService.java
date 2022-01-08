package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.dto.ClienteDTO;
import com.example.marcosvendas.repository.ClienteRepositories;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepositories clienteRepositories;

    public List<Cliente> findAll() {
        return clienteRepositories.findAll();


    }

    public Cliente finById(Integer id) {
        Optional<Cliente> obj = clienteRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Cliente insert(Cliente cli) {
        cli.setId(null);
        return clienteRepositories.save(cli);
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

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setNome(obj.getEmail());
    }


}
