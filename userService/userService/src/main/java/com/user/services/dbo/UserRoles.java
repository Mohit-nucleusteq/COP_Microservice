package com.user.services.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user_roles")
public class UserRoles {

	@Id
	private Integer id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_role")
	private String userRole;
}
