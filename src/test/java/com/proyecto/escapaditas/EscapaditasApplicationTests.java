package com.proyecto.escapaditas;

import com.proyecto.escapaditas.entidades.Cliente;
import com.proyecto.escapaditas.entidades.Promocion;
import com.proyecto.escapaditas.negocio.Negocio;
import com.proyecto.escapaditas.repositorios.ClienteRepositorio;
import com.proyecto.escapaditas.repositorios.PromocionRepositorio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EscapaditasApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private Negocio negocio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private PromocionRepositorio promocionRepositorio;


	@Test
	public void probarCliente() {
		Cliente cliente = new Cliente();
		cliente.setDni("11111112");
		cliente.setNombre("Carlos");
		cliente.setApaterno("Gomez");
		cliente.setAmaterno("Vidal");
		cliente.setDireccion("San Miguel");
		cliente.setCelular("444444444");
		cliente.setFnacimiento("02/12/85");
		cliente.setCelular("444444444");
		cliente.setSexo("M");
		cliente.setDireccion("Mirafles");
		cliente.setCorreo("cgomez@gmail.com");
		cliente.setContrasena("continuar");
		Cliente c;
		c = negocio.registrar(cliente);
		assertNotNull(c);
	}
	@Test
	public void probarPromocion1(){
		Promocion promocion= new Promocion();
		promocion.setNombrepromo("CUS01");
		promocion.setFinicio("01/05/19");
		promocion.setFfin("31/05/19");
		promocion.setPrecio(499.99);
		promocion.setCapacidad(10);
		promocion.setDescripcion("Destino a Cusco Historico");
		promocion.setDestino("CUZCO");
		Promocion p;
		p = negocio.registrarPromocion("11111111", promocion);
		assertNotNull(p);
	}

	@Test
	public void probarPromocion2(){
		Promocion promocion= new Promocion();
		promocion.setNombrepromo("ARE01");
		promocion.setFinicio("01/06/19");
		promocion.setFfin("15/06/19");
		promocion.setPrecio(399.99);
		promocion.setCapacidad(5);
		promocion.setDescripcion("Destino a Arequipa Clasica");
		promocion.setDestino("AREQUIPA");
		Promocion p;
		p = negocio.registrarPromocion("11111111", promocion);
		assertNotNull(p);
	}

	@Test
	public void probarListarPromociones() {
		List<Promocion> promociones = negocio.obtenerPromociones();
		for(Promocion p:promociones){
			if(p.getDestino().contentEquals("CUZCO")) {
				System.out.println("Descripcion: "+p.getDescripcion()+" F.Inicio: " + p.getFinicio() + " F.Fin: " + p.getFfin() + " Precio: " + p.getPrecio() );
			}
		}
		assertNotNull(promociones);
	}


}
