package edu.ifrs.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ifrs.model.Project;

@Path("/v1")
@Stateless
public class Service {

	@PersistenceContext(unitName = "Projester")
	private EntityManager em;

	/**
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Project create(@FormParam("numFuncionarios") int numFuncionarios, @FormParam("nome") String nome, @FormParam("gerente") String gerente, 
			@FormParam("dataInicial") LocalDate dataInicial, @FormParam("dataFinal") LocalDate dataFinal) {
		Project project = new Project();
		project.setNumFuncionarios(numFuncionarios); 
		project.setNome(nome);
		project.setGerente(gerente);
		project.setDataInicial(dataInicial);
		project.setDataFinal(dataFinal);
		em.persist(project);
		return project;
	}
	*/

	@GET
	@Path("/read")
	@Produces(MediaType.APPLICATION_JSON)
	public String read() {
		// https://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/htm
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
		Root<Project> project = criteria.from(Project.class);
		criteria.select(project);
		List<Project> c = em.createQuery(criteria).getResultList();
		return generateJson(c);
	}
	/**
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("id") long id) {
		Project project = em.find(Project.class, id);
		em.remove(project);
		StringBuilder json = new StringBuilder();
		json.append("{\"result\":\"true\", \"id\":\"" + id + "\"}");
		return json.toString();
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Project update(@FormParam("id") long id, @FormParam("numFuncionarios") int numFuncionarios, @FormParam("nome") String nome, @FormParam("gerente") String gerente, 
			@FormParam("dataInicial") LocalDate dataInicial, @FormParam("dataFinal") LocalDate dataFinal) {
		Project project = em.find(Project.class, id);
		project.setNumFuncionarios(numFuncionarios); 
		project.setNome(nome);
		project.setGerente(gerente);
		project.setDataInicial(dataInicial);
		project.setDataFinal(dataFinal);
		return em.merge(project);
	}
	**/
	private <T> String generateJson(List<T> pojo) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(pojo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
