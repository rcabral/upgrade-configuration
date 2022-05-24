package br.puc.rio.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.persistence.EntityManager;

public class RunSQLFileAction implements Action{
	private String path;

	public RunSQLFileAction(String path) {
		this.path = path;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.RUNSQLFILE;
	}

	@Override
	public void execute(EntityManager entityManager) throws Exception  {
		try {
			Path filePath = Path.of(path);
			String sql = Files.readString(filePath);
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery(sql).executeUpdate();
			entityManager.getTransaction().commit();
		} catch (RuntimeException | IOException e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
}
