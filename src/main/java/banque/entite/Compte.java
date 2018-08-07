package banque.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "compte")
public class Compte {
	@Id // Déclare à JPA quel champ représente la clé primaire
	@Column(name = "NUMERO")
	private String numero;
	@Column(name = "SOLDE")
	private Double solde;
	@OneToMany(mappedBy = "compte")
	private List<Operation> operations;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setClients(List<Operation> operations) {
		this.operations = operations;
	}

}
