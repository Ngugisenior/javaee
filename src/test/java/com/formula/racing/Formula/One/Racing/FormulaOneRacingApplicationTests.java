package com.formula.racing.Formula.One.Racing;


import com.formula.racing.Formula.One.Racing.domain.Partner;
import com.formula.racing.Formula.One.Racing.domain.Profile;


import com.formula.racing.Formula.One.Racing.repository.PartnerRepository;
import com.formula.racing.Formula.One.Racing.repository.PersonRepository;

import com.formula.racing.Formula.One.Racing.repository.TeamRepository;

import com.formula.racing.Formula.One.Racing.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class FormulaOneRacingApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	PartnerRepository partnerRepository;

	@Autowired
	ProfileService profileService;

	@Test
	void contextLoads() throws Exception{
	}


	@Test
	public void TestCreatePartners(){
		testCreatePartner("DHL");
		testCreatePartner("FEDEX");
		testCreatePartner("AMD");
		testCreatePartner("Petronas");
		testCreatePartner("INEOS");
		testCreatePartner("BOSE");
		testCreatePartner("Pirelli");
		testCreatePartner("Monster Energy");
	}


	@Test
	public void TestReadPartners(){
		String partner_name = "Petronas";
		if(!partner_name.isBlank()){
			Partner partner = partnerRepository.findByName(partner_name);
		}

	}


	@Test
	public void TestUpdatePartners(){
		String partner_name = "Petronas";
		if(!partner_name.isBlank()){
			Partner partner = partnerRepository.findByName(partner_name);
			partner.setName("Petronas Inc");
			System.out.println("Partner name changed to: "+partner.getName());
		}
	}

	@Test
	public void TestDeletePartners(){
		String partner_name = "Petronas";
		if(!partner_name.isBlank()){
			Partner partner = partnerRepository.findByName(partner_name);
			partnerRepository.deleteById(partner.getId());
			System.out.println("Partner name changed to: "+partner.getName());
		}

	}


	 void testCreatePartner(String partner) {
		if(partnerRepository.findByName(partner) == null){
			Partner p = new Partner(partner);
			partnerRepository.save(p);

		}
	}


	@Test
	public void initProfiles(){
		createProfile(44, "Driver",3872,270,169,7, "United Kingdom");
		createProfile(77, "Driver",1559,161,59,0, "FinLand");
		createProfile(3, "Driver",1183,192,31,0, "Australia");
		createProfile(4, "Driver",187,42,2,0, "United Kingdom");
		createProfile(47, "Driver",0,4,0,0, "Germany");
		createProfile(9, "Driver",0,4,0,0, "Russian Federation");

	}

	void createProfile(int car_number, String job_type, int points, int grand_prix_entered, int podiums, int world_championships, String nationality){
		if(profileService.findByCarNumber(car_number)==null){
			Profile profile = new Profile(car_number, job_type, points, grand_prix_entered, podiums, world_championships, nationality);
			profileService.save(profile);
		}
	}

}
