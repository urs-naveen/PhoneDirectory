package com.phonedirectory.controllers;

import com.phonedirectory.model.PhoneDirectory;
import com.phonedirectory.service.TelephoneDirectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Controller
@RequestMapping("/directory")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TelephoneDirectoryController {

    private final TelephoneDirectoryService telephoneDirectoryService;

    @PutMapping(value = "/phoneno/{phoneNumber}/fname/{firstName}/lname/{lastName}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addUserDetails(@PathVariable(value = "phoneNumber") Long phoneNumber,
                                                 @PathVariable(value = "firstName") String firstName,
                                                 @PathVariable(value = "lastName") String lastName) {
        String response;
        try {
            telephoneDirectoryService.addUserDetails(phoneNumber, firstName, lastName);
            response = phoneNumber + " added successfully";
            log.info(response);
        } catch (Exception ex) {
            response = phoneNumber + " already exists, please add another number";
            log.warn(response);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/aPhoneNo/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneDirectory> getUserDetails(@PathVariable(value = "phoneNumber") Long phoneNumber) {
        PhoneDirectory phoneDirectory;
        try {
            phoneDirectory = telephoneDirectoryService.getUserDetails(phoneNumber);
            return new ResponseEntity<>(phoneDirectory, HttpStatus.OK);
        } catch (Exception ex) {
            phoneDirectory = new PhoneDirectory();
            return new ResponseEntity<>(phoneDirectory, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/allPhoneNos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneDirectory>> getAllUsers() {
        List<PhoneDirectory> phoneDirectories;
        try {
            phoneDirectories = telephoneDirectoryService.getAllUsers();
            return new ResponseEntity<>(phoneDirectories, HttpStatus.OK);
        } catch (Exception ex) {
            phoneDirectories = new ArrayList<>();
            return new ResponseEntity<>(phoneDirectories, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{phoneNumber}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteUserDetail(@PathVariable(value = "phoneNumber") Long phoneNumber) {
        String response;
        try {
            telephoneDirectoryService.deleteUserDetails(phoneNumber);
            response = "successfully removed the " + phoneNumber + " from list";
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response = phoneNumber + " could not be found, please check once";
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteAll", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteAllEntries() {
        String response;
        try {
            telephoneDirectoryService.deleteAllEntries();
            response = "successfully removed all the entries from list";
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response = "No entries present in the list";
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}