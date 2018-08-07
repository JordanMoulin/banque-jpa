package dev.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InitialisationTest {
	EntityManagerFactory emf;
	EntityManager em;

	@Before
	public void setUp() {
		// Etape 1 -> Créer l'usine à sessions
		// -> Créer une instance d'EntityManagagerFactory (EMF)
		emf = Persistence.createEntityManagerFactory("banque-jpa");

		// Etape 2 -> Demander à l'usine une session
		// -> L'usine fournit une instance d'EntityManager (session de travail)
		em = emf.createEntityManager();
	}

	@Test
	public void test_insertion() {

	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

}
