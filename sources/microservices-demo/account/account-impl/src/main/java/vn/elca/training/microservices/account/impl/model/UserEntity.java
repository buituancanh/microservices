package vn.elca.training.microservices.account.impl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author tcb
 */
@Entity
@Table(name = "t_user")
@Getter @Setter
public class UserEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	protected Long id;

	@Column(name = "number")
	private String number;

	@Column(name = "name")
	protected String name;

	@Column(name = "email")
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<AddressEntity> addresses;

	@Transient
	public AddressEntity getUserAddress() {
		return this.addresses.stream().findAny().orElse(null);
	}
}
