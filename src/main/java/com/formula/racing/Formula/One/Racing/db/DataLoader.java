package com.formula.racing.Formula.One.Racing.db;


import com.formula.racing.Formula.One.Racing.domain.*;
import com.formula.racing.Formula.One.Racing.services.*;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    PersonService personService;

    TeamService teamService;


    ProfileService profileService;

    PartnerService partnerService;

    ProgressService progressService;

    public DataLoader(PersonService personService, TeamService teamService, ProfileService profileService, PartnerService partnerService, ProgressService progressService) {
        this.personService = personService;
        this.teamService = teamService;
        this.profileService = profileService;
        this.partnerService = partnerService;
        this.progressService = progressService;
    }


    @Override
    public void run(String... args) throws Exception {
        initPartners();
        initProfiles();
        initPersons();
        initTeams();
        initTeamPartners();
        initProgress();
    }


    void initPartners(){
        createPartner("DHL");
        createPartner("FEDEX");
        createPartner("AMD");
        createPartner("Petronas");
        createPartner("INEOS");
        createPartner("BOSE");
        createPartner("Pirelli");
        createPartner("Monster Energy");
    }

    private void createPartner(String partner) {
        if(partnerService.findByName(partner) == null){
            Partner p = new Partner(partner);
            partnerService.save(p);

        }
    }

    void initProfiles(){
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


    void initPersons(){
        testCreatePersons("Lewis","Hamilton","Mercedes", "Mercedes", 7,44);
        testCreatePersons("Valtteri","Bottas","Mercedes", "Mercedes", 7,77);
        testCreatePersons("Daniel","Riciarrdo","McLaren", "Mercedes", 8,3);
        testCreatePersons("Nikita","Mazepin","HAAS", "Ferrari", 0,9);
    }



    void testCreatePersons(String first_name, String last_name, String team_name, String power_unit, int world_championships,int car_number){
       Person person = new Person(first_name,last_name);


        if(teamService.findTeamIfExists(team_name) == null){
            teamService.save(new Team(team_name,power_unit,world_championships));
        }

        Profile profile = profileService.findByCarNumber(car_number);
        Team fetched_team = teamService.findTeamIfExists(team_name);


        person.setTeam(fetched_team);
        person.setProfile(profile);

        profileService.save(profile);
        teamService.save(fetched_team);
        personService.save(person);

        System.out.println(person.getTeam().getId());

    }


    void initTeams(){
        createTeam("Mercedes", "Mercedes",7);
        createTeam("McLaren","Mercedes",8);
        createTeam("Red Bull", "Honda", 4);
        createTeam("Ferarri", "Ferrari",16);
        createTeam("Alpine","Renault",2);
        createTeam("AlphaTauri", "Honda",0);
        createTeam("Aston Martin","Mercedes", 0);
        createTeam("Alpha Romeo", "Ferrari",0);
        createTeam("HAAS", "Ferrari",0);
        createTeam("Williams", "Mercedes", 9);
    }

    private void createTeam(String team_name,String power_unit, int world_championships){

        if(teamService.findTeamIfExists(team_name) == null){
            teamService.save(new Team(team_name, power_unit, world_championships));
        }
    }

    void initTeamPartners(){
        createTeamPartner("Williams","DHL");
        createTeamPartner("HAAS","DHL");
        createTeamPartner("HAAS","FEDEX");
        createTeamPartner("Mercedes", "POLICE Eyewear");
        createTeamPartner("Mercedes", "AMD");
        createTeamPartner("Red Bull", "Pirelli");
        createTeamPartner("Red Bull","AT&T");
    }

    private void createTeamPartner(String team_name, String partner){

        Team team = teamService.findTeamIfExists(team_name);
        if (team != null){
            Partner p = partnerService.findByName(partner);
            if(p!=null){
                team.addPartner(p);
                p.addTeam(team);

            }
            else{
                Partner partner1 = new Partner(partner);
                team.addPartner(partner1);
                partner1.addTeam(team);
            }

            teamService.save(team);
        }
    }


    void initProgress(){
        createProgress("OneToOne", true,true,true,true);
        createProgress("OneToMany", true, true, true, true);
        createProgress("ManyToOne", true, true, true, true);
        createProgress("ManyToMany",true, true, true, true);
        createProgress("JUnit Tests",true, true, true, true);
    }

    void createProgress(String task, boolean create, boolean read, boolean update, boolean delete){
        Progress progress = new Progress(task,create,read,update,delete);

        progressService.save(progress);
    }

}
