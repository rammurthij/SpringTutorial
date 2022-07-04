package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ResourceNotFoundException;
import com.app.model.RegisterPage;
import com.app.service.RegisterPageService;

@RestController
public class RegisterController {
	
	@Autowired
	RegisterPageService rp;
    
    @GetMapping("/registerspage")
    public List<RegisterPage> registerspagelist()
    {
    	return rp.registerspagelist();
    }
    
    
    @GetMapping("/registerspage/{id}")
    public ResponseEntity<Optional<RegisterPage>> getRigisterPageById(@PathVariable(value = "id") Long registerpageId)
        throws ResourceNotFoundException {
    	Optional<RegisterPage> registerpage = rp.findById(registerpageId);
        return ResponseEntity.ok().body(registerpage);
    }
    
    @PostMapping("/registerspage")
    public RegisterPage createRegisterPage(@Valid @RequestBody RegisterPage registerpage) {
        return rp.save(registerpage);
    }
    

    @PutMapping("/registerspage/{id}")
    public ResponseEntity<RegisterPage> updateRegisterPage(@PathVariable(value = "id") Long registerspageId,@Valid @RequestBody RegisterPage registerpageDetails) throws ResourceNotFoundException {
    	RegisterPage registerpage = rp.findById(registerspageId)
        .orElseThrow(() -> new ResourceNotFoundException("RegisterPage not found for this id :: " + registerspageId));

        
//    	admin.setId(adminDetails.getId());
    	registerpage.setFirstName(registerpageDetails.getFirstName());
    	registerpage.setLastName(registerpageDetails.getLastName());
    	registerpage.setEmail(registerpageDetails.getEmail());
    	registerpage.setLocation(registerpageDetails.getLocation());
    	registerpage.setMobileNo(registerpageDetails.getMobileNo());
    	registerpage.setQualification(registerpageDetails.getQualification());
    	
    	
        final RegisterPage updateRegisterPage = rp.save(registerpage);
        return ResponseEntity.ok(updateRegisterPage);
    }
    
    @DeleteMapping("/registerspage/{id}")
    public Map<String, Boolean> deleteRegisterPage(@PathVariable(value = "id") Long registerpageId)
         throws ResourceNotFoundException {
    	RegisterPage registerpage = rp.findById(registerpageId)
       .orElseThrow(() -> new ResourceNotFoundException("RegisterPage not found for this id :: " + registerpageId));

    	rp.delete(registerpage);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
