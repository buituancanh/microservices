package vn.elca.training.microservices.account.impl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tcb
 */
@Entity
@Table(name = "t_address")
@Getter @Setter
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	protected Long id;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	protected String city;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
