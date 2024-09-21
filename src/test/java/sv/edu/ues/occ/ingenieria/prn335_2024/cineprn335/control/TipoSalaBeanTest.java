package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.TipoSala;

import static org.junit.jupiter.api.Assertions.*;
class TipoSalaBeanTest {



 @Test
 void create() throws IllegalAccessException {
     System.out.println("TipoSalaBeanTest create");
     TipoSala nuevo = new TipoSala();
     EntityManager mockEM = Mockito.mock(EntityManager.class);


     TipoSalaBean cut = new TipoSalaBean();
     assertThrows(IllegalArgumentException.class, () -> {
         cut.create(null);
     });



     assertThrows(IllegalStateException.class, () -> {
         cut.create(nuevo);
     });

     cut.em=mockEM;


     cut.create(nuevo);

 }


    @Test
    void findById(){
        System.out.println("TipoSalaBeanTest.findById");
        final Integer idEsperado = 1;
        TipoSala esperado = new TipoSala(idEsperado);
        TipoSalaBean cut = new TipoSalaBean();


        assertThrows(IllegalStateException.class, () -> {
            cut.findById(idEsperado);
        });


        EntityManager mock = Mockito.mock(EntityManager.class);
        Mockito.when(mock.find(TipoSala.class, idEsperado)).thenReturn(esperado);
        cut.em = mock;

        TipoSala resultado = cut.findById(idEsperado);


        assertNotNull(resultado);
        assertEquals(esperado, resultado);


        assertThrows(IllegalArgumentException.class, ()->{
            cut.findById(null);
        });




        //fail("Not yet implemented");
    }






    @Test
    void getEntityManager() {
    }
}