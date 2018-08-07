package banque.entite;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livret_A")
public class LivretA extends Compte {
	private Double taux;

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "LivretA [taux=" + taux + "]";
	}

}
