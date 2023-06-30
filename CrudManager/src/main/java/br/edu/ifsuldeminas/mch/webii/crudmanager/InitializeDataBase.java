package br.edu.ifsuldeminas.mch.webii.crudmanager;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.ConsultaRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.PacienteRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Paciente;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Consulta;
@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner{
    
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Override
    public void run(String... args) throws Exception {
        Paciente emerson = new Paciente();
        emerson.setNome("Emerson");
        emerson.setSus("124587E");
        emerson.setTelefone("(35)99999-9999");
        emerson.setCpf("123.456.789-89");
        
        Paciente fernanda = new Paciente();
        fernanda.setNome("Fernanda");
        fernanda.setSus("987654P");
        fernanda.setTelefone("(35)97481-7942");
        fernanda.setCpf("154.678.912-78");
        
        Consulta CON1 = new Consulta();
        CON1.setMedico("Paulo Henrique");
        CON1.setMotivo("Rotina");
        CON1.setData("29/06/2023");
        CON1.setSala("2D");
        CON1.setPaciente(emerson);

        Consulta CON2 = new Consulta();
        CON2.setMedico("Luis Eduardo");
        CON2.setMotivo("Receita");
        CON2.setData("02/06/2023");
        CON2.setSala("9A");
        CON2.setPaciente(fernanda);
        
        
        pacienteRepository.save(emerson);
        pacienteRepository.save(fernanda);
        consultaRepository.save(CON1);
        consultaRepository.save(CON2);
        
        
     }
    
}