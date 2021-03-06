package it.prova.gestionemunicipiospringjpa.web.servlet.municipio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringjpa.model.Municipio;
import it.prova.gestionemunicipiospringjpa.service.municipio.MunicipioService;



/**
 * Servlet implementation class ExecuteInsertMunicipioServlet
 * @author dariocamp
 */
@WebServlet("/ExecuteInsertMunicipioServlet")
public class ExecuteInsertMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertMunicipioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String destinazione = null;
		String descrizioneInseritaNelForm = request.getParameter("descrizioneInput");
		String codiceInseritoNelForm = request.getParameter("codiceInput");
		String ubicazioneInseritaNelForm = request.getParameter("ubicazioneInput");
		
		Municipio municipioInput = new Municipio();
		municipioInput.setDescrizione(descrizioneInseritaNelForm);
		municipioInput.setCodice(codiceInseritoNelForm);
		municipioInput.setUbicazione(ubicazioneInseritaNelForm);
		
		try {
			municipioService.inserisciNuovo(municipioInput);
			request.setAttribute("listaMunicipiAttributeName", municipioService.listAllMunicipi());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		destinazione = "municipio/result.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(destinazione);
		rd.forward(request, response);

	}

}
