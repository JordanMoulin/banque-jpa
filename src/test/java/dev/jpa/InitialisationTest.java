package dev.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banque.entite.Adresse;
import banque.entite.AssuranceVie;
import banque.entite.Banque;
import banque.entite.Client;
import banque.entite.Compte;
import banque.entite.LivretA;
import banque.entite.Operation;
import banque.entite.Virement;

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
		EntityTransaction tx = this.em.getTransaction();
		Compte compte1 = new Compte();
		compte1.setNumero("hguifhdgius");
		compte1.setSolde(1500.00);

		Banque banque = new Banque();
		banque.setNom("Banque des Iles");

		Adresse adresse1 = new Adresse();
		adresse1.setCodePostal(44000);
		adresse1.setNumero(1);
		adresse1.setRue("Rue du ponton");
		adresse1.setVille("L'ile de la Tortue");

		Client client1 = new Client();
		client1.setDateNaissance(LocalDate.of(1990, 8, 10));
		client1.setNom("MacBernik");
		client1.setPrenom("Victor");
		client1.setBanque(banque);
		client1.setComptes(Arrays.asList(compte1));
		client1.setAdresse(adresse1);

		Adresse adresse2 = new Adresse();
		adresse2.setCodePostal(44000);
		adresse2.setNumero(2);
		adresse2.setRue("Rue du ponton");
		adresse2.setVille("L'ile de la Tortue");

		Client client2 = new Client();
		client2.setDateNaissance(LocalDate.of(1990, 8, 10));
		client2.setNom("Irvin");
		client2.setPrenom("Lerequin");
		client2.setBanque(banque);
		client2.setComptes(Arrays.asList(compte1));
		client2.setAdresse(adresse2);

		Operation operation = new Operation();
		operation.setCompte(compte1);
		operation.setDate(LocalDateTime.now());
		operation.setMontant(1500.00);
		operation.setMotif("Ajout de 1500.00€");

		tx.begin();
		this.em.persist(compte1);
		this.em.persist(banque);
		this.em.persist(client1);
		this.em.persist(client2);
		this.em.persist(operation);
		tx.commit();
	}

	@Test
	public void insertion_type_compte() {

		EntityTransaction tx = this.em.getTransaction();
		AssuranceVie compte1 = new AssuranceVie();
		compte1.setNumero("hguifhdgius");
		compte1.setSolde(1500.00);
		compte1.setTaux(1.5);
		compte1.setDateFin(LocalDate.of(2018, 11, 29));

		LivretA compte2 = new LivretA();
		compte2.setNumero("hguerhiguhd");
		compte2.setSolde(1500.00);
		compte2.setTaux(1.9);

		Banque banque = new Banque();
		banque.setNom("Banque des Iles");

		Adresse adresse1 = new Adresse();
		adresse1.setCodePostal(44000);
		adresse1.setNumero(1);
		adresse1.setRue("Rue du ponton");
		adresse1.setVille("L'ile de la Tortue");

		Client client1 = new Client();
		client1.setDateNaissance(LocalDate.of(1990, 8, 10));
		client1.setNom("MacBernik");
		client1.setPrenom("Victor");
		client1.setBanque(banque);
		client1.setComptes(Arrays.asList(compte1, compte2));
		client1.setAdresse(adresse1);

		tx.begin();
		this.em.persist(compte1);
		this.em.persist(compte2);
		this.em.persist(banque);
		this.em.persist(client1);
		tx.commit();
	}

	@Test
	public void insertion_virements() {
		EntityTransaction tx = this.em.getTransaction();
		Compte compte1 = new Compte();
		compte1.setNumero("hguifhdgius");
		compte1.setSolde(1500.00);

		Banque banque = new Banque();
		banque.setNom("Banque des Iles");

		Adresse adresse1 = new Adresse();
		adresse1.setCodePostal(44000);
		adresse1.setNumero(1);
		adresse1.setRue("Rue du ponton");
		adresse1.setVille("L'ile de la Tortue");

		Client client1 = new Client();
		client1.setDateNaissance(LocalDate.of(1990, 8, 10));
		client1.setNom("MacBernik");
		client1.setPrenom("Victor");
		client1.setBanque(banque);
		client1.setComptes(Arrays.asList(compte1));
		client1.setAdresse(adresse1);

		Virement operation = new Virement();
		operation.setCompte(compte1);
		operation.setDate(LocalDateTime.now());
		operation.setMontant(1500.00);
		operation.setMotif("Ajout de 1500.00€");
		operation.setBeneficiaire("Irvin Lerequin");

		tx.begin();
		this.em.persist(compte1);
		this.em.persist(banque);
		this.em.persist(client1);
		this.em.persist(operation);
		tx.commit();
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

}
