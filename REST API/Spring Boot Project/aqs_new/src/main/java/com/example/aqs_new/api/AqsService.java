package com.example.aqs_new.api;

import com.example.aqs_new.Response.Response;
import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.admin.AdminRepository;
import com.example.aqs_new.admin.adminsignin.Adminsignin;
import com.example.aqs_new.admin.adminsignin.AdminsigninRepository;
import com.example.aqs_new.codeversion.Codeversion;
import com.example.aqs_new.codeversion.CodeversionRepository;
import com.example.aqs_new.datapacketrecord.Packetrecord;
import com.example.aqs_new.datapacketrecord.PacketrecordRepository;
import com.example.aqs_new.devices.Devices;
import com.example.aqs_new.devices.DevicesRepository;
import com.example.aqs_new.error.Error;
import com.example.aqs_new.error.ErrorRepository;
import com.example.aqs_new.error.errorlog.Errorlog;
import com.example.aqs_new.error.errorlog.ErrorlogRepository;
import com.example.aqs_new.location.Location;
import com.example.aqs_new.location.LocationRepository;
import com.example.aqs_new.partner.Partner;
import com.example.aqs_new.partner.PartnerRepository;
import com.example.aqs_new.partnerpreviousaqi.Partnerpreviousaqi;
import com.example.aqs_new.partnerpreviousaqi.PartnerpreviousaqiRepository;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25Repesitory;
import com.example.aqs_new.partner.partnersignin.Partnersignin;
import com.example.aqs_new.partner.partnersignin.PartnersigninRepository;
import com.example.aqs_new.previouslocation.Previouslocation;
import com.example.aqs_new.previouslocation.PreviouslocationRepository;
import com.example.aqs_new.publicside.Publicside;
import com.example.aqs_new.publicside.PublicsideRepository;
import com.example.aqs_new.sensor.Sensor;
import com.example.aqs_new.sensor.SensorRepository;
import com.example.aqs_new.sensorcombination.Sensorcombination;
import com.example.aqs_new.sensorcombination.SensorcombinationRepository;
import com.example.aqs_new.sensorparameter.Sensorparameter;
import com.example.aqs_new.sensorparameter.SensorparameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class AqsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminsigninRepository adminsigninRepository;
    //AdminloginRepository adminloginRepository;


    @Autowired
    DevicesRepository deviceRepository;

    @Autowired
    SensorparameterRepository sensorparameterRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    CodeversionRepository codeversionRepository;

    @Autowired
    PacketrecordRepository datapacketrecordRepository;

    @Autowired
    ErrorRepository errorRepository;

    @Autowired
    ErrorlogRepository errorlogRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PreviouslocationRepository previouslocationRepository;

    @Autowired
    PartnerpreviousaqiRepository partnerpreviousaqiRepository;

    @Autowired
    Partnerpreviouspm25Repesitory partnerpreviouspm25Repository;

    @Autowired
    PublicsideRepository publicsideRepository;
    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    PartnersigninRepository partnersigninRepository;
    @Autowired
    SensorcombinationRepository sensorcombinationRepository;


    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    //////////////////////////////FIND METHODS///////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    //To check if sensor combination exists when a new device is being registered...
    public Sensorcombination FIND_DEVICE_SENSOR_COMBINATION_EXIST(long sensorcombination) {

        Sensorcombination S = sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(sensorcombination);
        return S;
    }


    // To retrieve all previoue data values of PM2.5 for a particular device
    public Iterable<Partnerpreviouspm25> FIND_ALL_PARTNER_PREVIOUS_PM25(Long deviceid) {
        return partnerpreviouspm25Repository.findAllByDeviceid(deviceid);
    }

    //To check if partner device id is already registered...
    public boolean partner_Deviceid_Already_Exist(Long deviceid)
    {
        Partner partner=partnerRepository.findByDeviceid(deviceid);
        if(partner!=null)
        return  true;
        else
        return false;
    }

    //To delete a particular device by device id...
    public Response FIND_DELETE_DEVICE(Long deviceid) {

        Response response = new Response();
        Devices device = deviceRepository.findDeviceByDeviceid(deviceid);
        if (device != null) {
            deviceRepository.delete(device);
            response.setResponse("DEVICE DELETED!");
        } else {
            response.setResponse("UNKNOWN DEVICE ID");
        }

        return response;

    }

    //To get list of all devices from DB
    public Iterable<Devices> FIND_DEVICES_LIST() {
        return deviceRepository.findAll();

    }

    //To check if code version exists when a new device is registered for a particular codeversion...
    public Codeversion FIND_DEVICE_CODE_VERSION_EXIST(Long codeversion) {
        Codeversion CV = codeversionRepository.findCodeversionByVersionid(codeversion);
        return CV;
    }

    //To find admin by admin id...
    public Admin FIND_ADMIN(Long adminid) {
        Admin A = adminRepository.findAdminById(adminid);
        return A;
    }

    //To find admin by email...
    public Admin FIND_ADMIN_BY_EMAIL(String email) {
        Admin admin = adminRepository.findByEmail(email);
        return admin;
    }

    // To find Sensor by sensor name...
    public Sensor FIND_SENSOR(String sensorname) {
        Sensor S = sensorRepository.findSensorByName(sensorname);
        return S;
    }

    //To find a device by device id...
    public Devices FindDevice(Long deviceid) {
        Devices device = deviceRepository.findDeviceByDeviceid(deviceid);
        return device;
    }


    //To Find an SensorCombination record from DB based on sensorcombinationcode...
    public Sensorcombination FIND_SENSOR_COMBINATION(Long sensorcombinationcode) {
        Sensorcombination SC = sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(sensorcombinationcode);
        return SC;
    }

    // To Find an error record from DB based on errorcode...
    public Error FindError(Long errorcode) {
        return errorRepository.findErrorByErrorcode(errorcode);
    }


    //  To check if partner has a sign up record against device id....
    public Partner FINDPARTNER(Long deviceid) {
        Partner partner = partnerRepository.findByDeviceid(deviceid);
        return partner;
    }


    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    ////////////////////////EXISTENCE CHECK METHODS//////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////


    //to check if admin email exists already
    public boolean adminEmailExist(String email) {
        Admin admin1 = adminRepository.findByEmail(email);
        if (admin1 != null) {
            return true;
        } else {
            return false;
        }
    }


    //to check if partner email exists already
    public boolean partnerEmailExist(String email) {
        Partner partner = partnerRepository.findByEmail(email);
        if (partner != null)
            return true;
        else
            return false;

    }

    //to check if a error recod already exists
    public boolean errorCodeExist(Long errorcode) {
        Error error = errorRepository.findErrorByErrorcode(errorcode);
        if (error != null)
            return true;

        return false;

    }

    //to check if device exists already
    public boolean deviceIdExist(Long deviceid) {
        Devices device1 = deviceRepository.findDeviceByDeviceid(deviceid);
        if (device1 != null)
            return true;

        return false;

    }

    //To check if sensor combination exists  by sensorcombinationcode...
    public boolean sensorCombinationExist(Sensorcombination sensorcombination) {
        Long code = sensorcombination.getSensorcombinationcode();
        Sensorcombination S1 = sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(code);
        if (S1 != null)
            return true;

        return false;
    }


    // To check if device exist in location table...if yes...then store its location in previous location table and update its location...
    public void deviceExistInLocationTable(Long deviceid) {

        Location previousLocation = locationRepository.findLocationByDeviceid(deviceid);
        if (previousLocation != null) {

            Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
            Previouslocation P_location = new Previouslocation();
            P_location.setDeviceid(deviceid);
            P_location.setLocationtime(timestamp);
            P_location.setLatitude(previousLocation.getLatitude());
            P_location.setLongitude(previousLocation.getLongitude());
            previouslocationRepository.save(P_location);
            locationRepository.delete(previousLocation);

        }

    }

    // To check if device is registered...if yes then update its location...if not exist then store in public side...
    public void deviceExistInPublicside(Location location, Long deviceid) {
        Publicside publicside = publicsideRepository.findPublicsideByDeviceid(deviceid);
        Publicside publicside1 = new Publicside();


        if (publicside == null) {
            publicside1.setDeviceid(deviceid);
            publicside1.setLatitude(location.getLatitude());
            publicside1.setLongitude(location.getLongitude());
            publicsideRepository.save(publicside1);
        } else {
            Publicside publicside2 = publicside;
            publicside2.setDeviceid(deviceid);
            publicside2.setLongitude(location.getLongitude());
            publicside2.setLatitude(location.getLatitude());
            publicsideRepository.delete(publicside);
            publicsideRepository.save(publicside2);
        }

    }

    // To check if Password and email are entered correctly while partner signin
    public boolean partnerPasswordEmailMatchCorrectly(Partnersignin partnersignin) {
        Partner partner = partnerRepository.findByDeviceid(partnersignin.getDeviceid());

        if (partnersignin.getPassword().equals(partner.getPassword()))

            return true;

        else
            return false;
    }

    // To check if Password and email are entered correctly while admin signin
    public boolean adminPasswordEmailMatchCorrectly(Adminsignin adminsignin)

    {
        Admin admin = adminRepository.findByEmail(adminsignin.getEmail());

        if (adminsignin.getPassword().equals(admin.getPassword()))
            return true;
        else
            return false;
    }


    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////HTTP METHODS////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    //to store admin login
    public Admin setAdmin(Admin admin) {
        adminRepository.save(admin);
        return admin;
    }

    //to store partner login

    public void setPartnersignin(Partnersignin partnersignin)

    {
        partnersigninRepository.save(partnersignin);
    }

    //to store device
    public Devices setDevice(Devices device) {
        deviceRepository.save(device);
        return device;
    }

    //to store admin signin
    public void setAdminsignin(Adminsignin adminsignin) {

        adminsigninRepository.save(adminsignin);

    }

    //to store new partner
    public void setPartner(Partner partner) {
        partnerRepository.save(partner);
    }

    //to check if a sensor recod already exists
    public boolean sensorNameExist(String name) {
        Sensor sensor = sensorRepository.findSensorByName(name);
        if (sensor != null)
            return true;

        return false;
    }


    //to store sensor
    public Sensor setSensor(Sensor sensor) {
        sensorRepository.save(sensor);
        return sensor;
    }

    //to store errorcode
    public Error setError(Error error) {
        errorRepository.save(error);
        return error;
    }

    //  to store error log
    public Response setErrorlog(Errorlog errorlog) {
        errorlogRepository.save(errorlog);
        Response response = new Response();
        response.setResponse("ERRORLOG RECORDED");
        return response;

    }

    //to store codeveersion
    public Codeversion setCodeversion(Codeversion codeversion) {
        codeversionRepository.save(codeversion);
        return codeversion;
    }

    //to store sensor parameter

    public Sensorparameter setSensorparameter(Sensorparameter sensorparameter) {
        sensorparameterRepository.save(sensorparameter);
        return sensorparameter;
    }

    //to store sensorcombination
    public Sensorcombination setSensorcombination(Sensorcombination sensorcombination) {
        sensorcombinationRepository.save(sensorcombination);
        return sensorcombination;
    }

    //To store a data record for a particular parameter e.g. PM2.5
    public void setDatapacketRecord(Packetrecord record) {
        datapacketrecordRepository.save(record);
    }

    //to store location
    public Location setLocation(Location location) {
        locationRepository.save(location);
        return location;
    }

    // Update PM2.5 AND AQI in (partner previous PM2.5) AND (partner previous AQI)
    public void UPDATE_PUBLICSIDE_PARTNERPREVIOUS_PM25_AQI(Long PmValue, Long deviceid) {

        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        Partnerpreviousaqi partnerpreviousaqi = new Partnerpreviousaqi();
        Partnerpreviouspm25 partnerpreviouspm25 = new Partnerpreviouspm25();
        partnerpreviousaqi.setDeviceid(deviceid);
        partnerpreviousaqi.setAqi(PmValue);
        partnerpreviousaqi.setTimestamp(timestamp);
        partnerpreviousaqiRepository.save(partnerpreviousaqi);
        partnerpreviouspm25.setDeviceid(deviceid);
        partnerpreviouspm25.setPM25(PmValue);
        partnerpreviouspm25.setTimestamp(timestamp);
        partnerpreviouspm25Repository.save(partnerpreviouspm25);
        Publicside publicside = publicsideRepository.findPublicsideByDeviceid(deviceid);
        Publicside publicside1 = new Publicside();
        publicside1.setDeviceid(deviceid);
        publicside1.setLatitude(publicside.getLatitude());
        publicside1.setLongitude(publicside.getLongitude());
        publicside1.setAqi(PmValue);
        publicsideRepository.delete(publicside);
        publicsideRepository.save(publicside1);

    }


    //  Update the current location of a device
    public void updateLocation(Location location) {
        locationRepository.save(location);
    }

    //to retrieve error log
    public Iterable<Errorlog> getErrorlog(Long deviceid) {
        return errorlogRepository.findAllByDeviceid(deviceid);
    }

    // check for upgrade of firmware
    public Response CheckForUpgrade(Long id, Long sensor_combination_code, Long code_version) {
        Codeversion latest_code_version_record = codeversionRepository.findTopByOrderByVersionidDesc();
        Long latest_code_version = latest_code_version_record.getVersionid();

        String URL = " UP TO DATE";
        if (latest_code_version > code_version) {
            URL = latest_code_version_record.getFirmware_url();

        }
        Response response = new Response();
        response.setResponse(URL);
        return response;

    }

    //upgrade code version in device record
    public Response UpgradeDeviceRecord(Long deviceid, Long sensor_combination_code, Long code_version) {
        Devices device = deviceRepository.findDeviceByDeviceid(deviceid);
        device.setCode_version(code_version);
        deviceRepository.save(device);

        Response response = new Response();
        response.setResponse("FIRMWARE UPGRADED!");
        return response;
    }

    // To get locations of all devices
    public Iterable<Location> getlocations() {
        return locationRepository.findAll();
    }

    // To get all records in public side table
    public Iterable<Publicside> getAllPublicside() {
        return publicsideRepository.findAll();
    }

    // To retrieve all previoue data values of AQI for a particular device
    public Iterable<Partnerpreviousaqi> FIND_ALL_PARTNER_PREVIOUS_AQI(Long deviceid) {
        return partnerpreviousaqiRepository.findAllByDeviceid(deviceid);
    }

}
