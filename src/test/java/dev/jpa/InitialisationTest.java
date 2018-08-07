package dev.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banque.entite.Adresse;
import banque.entite.Banque;
import banque.entite.Client;
import banque.entite.Compte;
import banque.entite.Operation;

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
	public void test_insertion_client() {
		EntityTransaction tx = this.em.getTransaction();
		Compte compte1 = new Compte();
		compte1.setNumero("hguifhdgius");
		compte1.setSolde(1500.00);

		Banque banque = new Banque();
		banque.setNom("Banque des Iles");

		Adresse adresse = new Adresse();
		adresse.setCodePostal(44000);
		adresse.setNumero(15);
		adresse.setRue("Rue du chocolat");
		adresse.setVille("CastorLand");

		Client client = new Client();
		client.setDateNaissance(LocalDate.of(1990, 8, 10));
		client.setNom("Le pirate");
		client.setPrenom("Hector");
		client.setBanque(banque);
		client.setComptes(Arrays.asList(compte1));
		client.setAdresse(adresse);

		Operation operation = new Operation();
		operation.setCompte(compte1);
		operation.setDate(LocalDateTime.now());
		operation.setMontant(1500.00);
		operation.setMotif("Ajout de 1500.00€");

		tx.begin();
		this.em.persist(compte1);
		this.em.persist(banque);
		this.em.persist(client);
		this.em.persist(operation);
		tx.commit();

		TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);

		for (Client test : query.getResultList()) {
			System.out.println(test);
		}
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

}
