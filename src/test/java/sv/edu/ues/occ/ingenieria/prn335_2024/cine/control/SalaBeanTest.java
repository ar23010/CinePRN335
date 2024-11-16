package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.util.List;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SalaBeanTest {

/*
   @Test
    void findById() {
        System.out.println("SalaBeanTest.findById");
        List<Sala> buscados = Arrays.asList(new Sala[]{new Sala(1), new Sala(2), new Sala(3), new Sala(4), new Sala(5)});
        Query mockQuery = Mockito.mock(Query.class);
        Mockito.when(mockQuery.getResultList()).thenReturn(buscados);

        EntityManager mockEM = Mockito.mock(EntityManager.class);
        Mockito.when(mockEM.createNamedQuery("Sala.findAll")).thenReturn(mockQuery);

        SalaBean cut = new SalaBean();
        cut.em = mockEM;

        List<Sala> encontrados = cut.findAll(0,100);

        assertEquals(buscados.size(), encontrados.size());

        //fail("Esto no funciona");


    }


 */

}