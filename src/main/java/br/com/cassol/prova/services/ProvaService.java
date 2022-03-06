package br.com.cassol.prova.services;

import br.com.cassol.prova.model.Prova;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvaService {

    @Autowired private ProvaRepository repository;

    public List<Prova> listAll(){
        return (List<Prova>) repository.findAll();
    }

    public long getcount(){
        return repository.count();
    }

    public Integer somaDasNotas(){
        List<Prova> provas = listAll();
        Integer sum = provas.stream()
                .map(Prova::getNOTA)
                .reduce(0,(a,b) -> {
                    return  a+b;
                });
        return sum;
    }

    public Prova maiorNota(){
        List<Prova> provas = listAll();
        //List<Prova> maior = provas.stream().sorted(Comparator.comparing(Prova::getNOTA)).collect(Collectors.toList());
         Prova maior = provas.stream().sorted(Comparator.comparing(Prova::getNOTA)).findFirst().orElse(null);
        return maior;
    }
    public Prova menorNota(){
        List<Prova> provas = listAll();
        Prova menor = provas.stream().sorted(Collections.reverseOrder(Comparator.comparing(Prova::getNOTA))).findFirst().orElse(null);
        return menor;
    }

    public String media(){
        Double notasD = Double.valueOf(somaDasNotas());
        Double countD = Double.valueOf(getcount());
        String resultadoToString = String.format("%.2f",notasD/countD);

        return resultadoToString;
    }


    public void save(Prova prova) {
        repository.save(prova);
    }


    public Prova get(Integer id) throws Exception {
        Optional<Prova> resultado = repository.findById(id);
        if (resultado.isPresent()){
            return resultado.get();
        }
        throw new Exception("Usuário com o ID " + id + " não foi encontrado");
    }

    public void delete(Integer id) throws Exception {
        Optional<Prova> resultado = repository.findById(id);
        if (resultado.isPresent()){
            repository.deleteById(id);
        }
        throw new Exception("Usuário com o ID " + id + " não foi encontrado");
    }
}

