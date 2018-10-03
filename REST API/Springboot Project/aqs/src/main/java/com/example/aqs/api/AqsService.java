package com.example.aqs.api;


import com.example.aqs.admin.Admin;
import com.example.aqs.admin.AdminRepository;
import com.example.aqs.codeversion.CodeversionRepository;
import com.example.aqs.datapacket.DatapacketRepository;
import com.example.aqs.devices.Devices;
import com.example.aqs.devices.DevicesRepository;
import com.example.aqs.error.Error;
import com.example.aqs.error.ErrorRepository;
import com.example.aqs.errorlog.ErrorlogRepository;
import com.example.aqs.location.LocationRepository;
import com.example.aqs.login.Login;
import com.example.aqs.login.LoginRepository;
import com.example.aqs.sensor.Sensor;
import com.example.aqs.sensor.SensorRepository;
import com.example.aqs.sensorcombination.SensorcombinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AqsService {
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DevicesRepository deviceRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    CodeversionRepository codeversionRepository;

    @Autowired
    DatapacketRepository datapacketRepository;

    @Autowired
    ErrorRepository errorRepository;

    @Autowired
    ErrorlogRepository errorlogRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    SensorcombinationRepository sensorcombinationRepository;



    //to check if a login recod already exists
    public boolean loginEmailExist(String email){
        Login login1 = loginRepository.findLoginByEmail(email);
        if(login1 != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //to store user login
    public Login setLogin(Login login){
        loginRepository.save(login);
        return login;
    }

    //to check if admin record exists already
        public boolean adminEmailExist(String email){
        Admin admin1 = adminRepository.findAdminByEmail(email);
        if(admin1 != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //to store user login
    public Admin setAdmin(Admin admin){
        adminRepository.save(admin);
        return admin;
    }

    //to check if device exists already
    public boolean deviceIdExist(Long id){
        Devices device1 = deviceRepository.findDeviceById(id);
        if(device1 != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //to store device
    public Devices setDevice(Devices device){
        deviceRepository.save(device);
        return device;
    }

    //to check if a login recod already exists
    public boolean sensorNameExist(String name){
        Sensor sensor= sensorRepository.findSensorByName(name);
        if(sensor != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //to store sensor
    public Sensor setSensor(Sensor sensor){
        sensorRepository.save(sensor);
        return sensor;
    }

    //to check if a login recod already exists
        public boolean errorCodeExist(Long errorcode){
        Error error= errorRepository.findErrorByErrorcode(errorcode);
        if(error != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //to store sensor
    public Error setError(Error error){
        errorRepository.save(error);
        return error;
    }

}
