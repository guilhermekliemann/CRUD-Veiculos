package br.kliemann.flashcar.service;

import br.kliemann.flashcar.exception.DescricaoInvalidaException;
import br.kliemann.flashcar.model.Marca;
import br.kliemann.flashcar.repository.MarcaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaService {
    
    private void validaMarca(Marca marca) throws DescricaoInvalidaException {
        
        if (marca.getDescricao() == null) {
            throw new DescricaoInvalidaException();
        }
        
        if (marca.getDescricao().trim().length() == 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (marca.getDescricao().trim().length() > 60) {
            throw new DescricaoInvalidaException();
        }
        
    }
    
     public void insert(Marca marca) throws DescricaoInvalidaException, SQLException {
        
        validaMarca(marca);
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.insert(marca);
        
    }
    
    public ArrayList<Marca> findAll() throws SQLException {
        
        MarcaRepository marcaRepository = new MarcaRepository();
        return marcaRepository.findAll();
        
    }
    
    public void delete(Marca marca) throws SQLException {
        
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.delete(marca);
        
    }
    
    public void update(Marca marca) throws SQLException, DescricaoInvalidaException {
        
        validaMarca(marca);
        MarcaRepository marcaRepository = new MarcaRepository();
        marcaRepository.update(marca);
        
    }
    
}
