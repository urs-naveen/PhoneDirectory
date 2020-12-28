package com.phonedirectory.service;

import com.phonedirectory.model.DataInconsistentException;
import com.phonedirectory.model.PhoneDirectory;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class TelephoneDirectoryService {

    public List<PhoneDirectory> phoneDirectories = new ArrayList<>();

    public PhoneDirectory getUserDetails(long phoneNumber) {
        isValidPhoneNumber(phoneNumber);
        Optional<PhoneDirectory> phoneDirectory = getPhoneDirectory(phoneNumber);
        if (phoneDirectory.isPresent()) {
            return phoneDirectory.get();
        } else {
            log.warn(phoneNumber + " is not exist, please check");
            throw new DataInconsistentException(phoneNumber + " is not present, please check once");
        }
    }

    public List<PhoneDirectory> getAllUsers() {
        return phoneDirectories;
    }

    public void addUserDetails(long phoneNumber, String firstName, String lastName) {
        PhoneDirectory phoneDirectory;
        isValidPhoneNumber(phoneNumber);
        try {
            phoneDirectory = getUserDetails(phoneNumber);
        } catch (Exception ex) {
            phoneDirectory = new PhoneDirectory();

            phoneDirectory.setPhoneNumber(phoneNumber);
            phoneDirectory.setFirstName(firstName);
            phoneDirectory.setLastName(lastName);

            phoneDirectories.add(phoneDirectory);

            return;
        }

        if (phoneDirectory != null) {
            log.warn(phoneNumber + " already exists, please add another one");
            throw new DataInconsistentException(phoneNumber + " already exists, please add another one");
        }
    }

    public void deleteUserDetails(long phoneNumber) {
        isValidPhoneNumber(phoneNumber);
        Optional<PhoneDirectory> phoneDirectory = getPhoneDirectory(phoneNumber);

        if (phoneDirectory.isPresent()) {
            phoneDirectories.remove(phoneDirectory.get());
        } else {
            log.error(phoneNumber + " not exists, please check once");
            throw new DataInconsistentException(phoneNumber + " not exist, please check once");
        }
    }

    public void deleteAllEntries() {
        if (phoneDirectories.size() > 0) {
            phoneDirectories.clear();
        } else {
            log.error("no entries present in the list");
            throw new DataInconsistentException("no entries present in the list");
        }
    }

    private void isValidPhoneNumber(long phoneNumber) {
        String validPhoneNo = String.valueOf(phoneNumber);

        if (validPhoneNo.length() != 10) {
            log.error(phoneNumber + " is not valid as number of digits is not equal to 10");
            throw new DataInconsistentException(phoneNumber + " is not valid, please check");
        }
    }

    private Optional<PhoneDirectory> getPhoneDirectory(long phoneNumber) {
        return phoneDirectories.parallelStream().filter(telePhoneDir ->
                phoneNumber == telePhoneDir.getPhoneNumber()).findFirst();
    }
}