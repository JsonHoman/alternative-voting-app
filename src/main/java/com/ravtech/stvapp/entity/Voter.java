package com.ravtech.stvapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "voter")
@Data
@NoArgsConstructor
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
						 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "person_info_id")
	private PersonInfo personInfo;

	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
						   CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "election_voter",
			joinColumns = @JoinColumn(name = "voter_id"),
			inverseJoinColumns = @JoinColumn(name = "election_id"))
	private Set<Election> elections;

	public Voter(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Voter(String firstName, String lastName, String email, String phoneNumber, String address, String dateOfBirth, PersonInfo personInfo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.personInfo = personInfo;
	}
}
