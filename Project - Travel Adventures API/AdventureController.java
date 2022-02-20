package com.codecademy.plants.controllers;

import com.codecademy.plants.entities.Adventure;
import com.codecademy.plants.repositories.AdventureRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//put method
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;
//
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {
  private final AdventureRepository adventureRepository;

  public TravelAdventuresController(AdventureRepository adventureRepo) {
    this.adventureRepository = adventureRepo;
  }

// Adventure es la clase del objeto Java
// curl localhost:4001/traveladventures
@GetMapping()
public Iterable<Adventure> getAdventures() {
  Iterable<Adventure> adventures = this.adventureRepository.findAll();
  return adventures;
}

// curl localhost:4001/traveladventures/bycountry/Greece
@GetMapping(path = "/bycountry/{country}")
  public Iterable<Adventure> getByCountry(
@PathVariable String country
    ) {
  Iterable<Adventure> adventuresCountry = this.adventureRepository.findByCountry(country);
  return adventuresCountry;
}

// curl localhost:4001/traveladventures/bystate?state=Lisboa
@GetMapping(path = "/bystate")
  public Iterable<Adventure> getByState(
@RequestParam String state
    ) {
    Iterable<Adventure> adventuresState = this.adventureRepository.findByState(state);
    return adventuresState;
  }

/*
curl -d '{  "id": "351",  "date": "05/05/2022", "country": "Korea", "city": "Jeju City", "state": "Jeju Province", "numPhotos": "96", "blogCompleted": "false" }'  -H "Content-Type: application/json" localhost:4001/traveladventures 
*/
@PostMapping()
  public Adventure newAdventure(
@RequestBody Adventure adventure){
    Adventure newAdventure = this.adventureRepository.save(adventure);
    return newAdventure;
  }

/*  
curl localhost:4001/traveladventures/bycountry/Panama

curl -X PUT -d '{  "id": "5",  "date": "14/30/2022", "country": "Peru", "city": "Lima", "state": "San Martin de Porres", "numPhotos": "101", "blogCompleted": "true" }' -H "Content-Type: application/json" localhost:4001/traveladventures/5
*/
@PutMapping("/{id}")
  public Adventure updateAdventure(
@PathVariable("id") Integer id, 
@RequestBody Adventure adventure){
  Optional<Adventure> optionalUpdateAdventure = this.adventureRepository.findById(id);  

  if(!optionalUpdateAdventure.isPresent()){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  Adventure adventureToUpdate = optionalUpdateAdventure.get();

  adventureToUpdate.set(adventure);

  Adventure updatedAdventure = this.adventureRepository.save(adventureToUpdate);

  return updatedAdventure;
}

/*
curl localhost:4001/traveladventures/bystate?state=Georgia

curl -X DELETE localhost:4001/traveladventures/335

curl localhost:4001/traveladventures/bystate?state=Georgia
*/
@DeleteMapping("/{id}")
public void deleteAdventure(
@PathVariable("id") Integer id){
  Optional<Adventure> optionalDeleteAdventure = this.adventureRepository.findById(id);

  if(optionalDeleteAdventure.isPresent()){
    Adventure deleteAdventure = optionalDeleteAdventure.get();
    this.adventureRepository.delete(deleteAdventure);
  }
  
}

}
