package it.prova.gestionemunicipiospringjpa.web.servlet.abitante;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringjpa.model.Abitante;
import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;

/**
 * Servlet implementation class PrepareUpdateAbitanteServlet
 * @author MohamedMohamedAli
 */
@WebServlet("/PrepareUpdateAbitanteServlet")
public class PrepareUpdateAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private AbitanteService abitanteService;
	@Autowired
	private MunicipioService municipioService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public PrepareUpdateAbitanteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idAbitante"));
		Abitante abitante = abitanteService.caricaSingoloAbitanteEager(id);
		List<Municipio> municipi = municipioService.listAllMunicipi();
		request.setAttribute("abitanteAttr", abitante);
		request.setAttribute("listaMunicipiAttributeName", municipi);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/abitante/updateAbitante.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
