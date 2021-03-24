package com.example.jetty_jersey.ws;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.datanucleus.dao.ActionContainer;
import com.example.datanucleus.dao.DAO;

@Path("/example")
public class ActionResource {

	public static class ExampleClass {
		public String field;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/action/{id}")
	public ActionContainer getActionContainer(@PathParam("id") long id) {
		ActionContainer container = DAO.getActionDao().getActionContainer(id);

		if (container == null) {
			throw new NotFoundException("Invalid container id");
		}

		return container;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/action")
	public long addActionContainer(ActionContainer container) {
		if (container == null) {
			throw new BadRequestException("Missing payload");
		}

		if (container.getActions() == null) {
			throw new BadRequestException("Missing actions in the container");
		}

		return DAO.getActionDao().addActionContainer(container);
	}

}
