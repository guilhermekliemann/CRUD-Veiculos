package br.kliemann.flashcar.service;

import br.kliemann.flashcar.exception.DescricaoInvalidaException;
import br.kliemann.flashcar.model.Modelo;
import br.kliemann.flashcar.repository.ModeloRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloService {
    
    private void validaModelo(Modelo modelo) throws DescricaoInvalidaException {
        
        if (modelo.getDescricao() == null) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getDescricao().trim().length() == 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (modelo.getDescricao().trim().length() > 60) {
            throw new DescricaoInvalidaException();
        }
        
    }
    
     public void insert(Modelo modelo) throws DescricaoInvalidaException, SQLException {
        
        validaModelo(modelo);
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.insert(modelo);
        
    }
    
    public ArrayList<Modelo> findAll() throws SQLException {
        
        ModeloRepository modeloRepository = new ModeloRepository();
        return modeloRepository.findAll();
        
    }
    
    public void delete(Modelo modelo) throws SQLException {
        
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.delete(modelo);
        
    }
    
    public void update(Modelo modelo) throws SQLException, DescricaoInvalidaException {
        
        validaModelo(modelo);
        ModeloRepository modeloRepository = new ModeloRepository();
        modeloRepository.update(modelo);
        
    }
    
}
