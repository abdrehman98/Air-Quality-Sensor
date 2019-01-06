package com.example.aqs_new.api;


import com.example.aqs_new.Response.Response;
import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.admin.AdminRepository;
import com.example.aqs_new.codeversion.Codeversion;
import com.example.aqs_new.codeversion.CodeversionRepository;
import com.example.aqs_new.datapacketrecord.Packetrecord;
import com.example.aqs_new.datapacketrecord.PacketrecordRepository;
import com.example.aqs_new.devices.Devices;
import com.example.aqs_new.devices.DevicesRepository;
import com.example.aqs_new.error.Error;
import com.example.aqs_new.error.ErrorRepository;
import com.example.aqs_new.errorlog.Errorlog;
import com.example.aqs_new.errorlog.ErrorlogRepository;
import com.example.aqs_new.location.Location;
import com.example.aqs_new.location.LocationRepository;
import com.example.aqs_new.login.Login;
import com.example.aqs_new.login.LoginRepository;
import com.example.aqs_new.partnerpreviousaqi.Partnerpreviousaqi;
import com.example.aqs_new.partnerpreviousaqi.PartnerpreviousaqiRepository;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25Repesitory;
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
    LoginRepository loginRepository;

    @Autowired
    AdminRepository adminRepository;

    // @Autowired
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
    SensorcombinationRepository sensorcombinationRepository;


    //to check if a login recod already exists
    public boolean loginEmailExist(String email) {
        Login login1 = loginRepository.findLoginByEmail(email);
        if (login1 != null) {
            return true;
        } else {
            return false;
        }
    }

    //to store user login
    public Login setLogin(Login login) {
        loginRepository.save(login);
        return login;
    }


    //to check if admin record exists already
    public boolean adminEmailExist(String email) {
        Admin admin1 = adminRepository.findAdminByEmail(email);
        if (admin1 != null) {
            return true;
        } else {
            return false;
        }
    }

    //to store admin login
    public Admin setAdmin(Admin admin) {
        adminRepository.save(admin);
        return admin;
    }


    //
    public Devices FindDevice(Long deviceid) {
        Devices device = deviceRepository.findDeviceByDeviceid(deviceid);
        return device;
    }

    public Sensorcombination FIND_SENSOR_COMBINATION(Long sensorcombinationcode)
    {
        Sensorcombination SC=sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(sensorcombinationcode);
        return SC;
    }

    public Sensorcombination FIND_DEVICE_SENSOR_COMBINATION_EXIST(long sensorcombination) {

        Sensorcombination S = sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(sensorcombination);
        return S;
    }

    public Codeversion FIND_DEVICE_CODE_VERSION_EXIST(Long codeversion) {
        Codeversion CV = codeversionRepository.findCodeversionByVersionid(codeversion);
        return CV;
    }

    public Admin FIND_ADMIN(Long adminid)
    {
       Admin A= adminRepository.findAdminById(adminid);
       return A;
    }


    public Sensor FIND_SENSOR(String sensorname)
    {
        Sensor S= sensorRepository.findSensorByName(sensorname);
        return S;
    }




    //to check if device exists already
    public boolean deviceIdExist(Long deviceid) {
        Devices device1 = deviceRepository.findDeviceByDeviceid(deviceid);
        if (device1 != null)
            return true;

            return false;

    }

    //to store device
    public Devices setDevice(Devices device) {
        deviceRepository.save(device);
        return device;
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


    //to check if a error recod already exists
    public boolean errorCodeExist(Long errorcode) {
        Error error = errorRepository.findErrorByErrorcode(errorcode);
        if (error != null)
            return true;

            return false;
        
    }


/*
    public Adminlogin setAdminlogin(Adminlogin adminlogin)
    {
    return adminloginRepository.save(adminlogin);
    }
*/

    //to store errorcode
    public Error setError(Error error) {
        errorRepository.save(error);
        return error;
    }


    /*
    //to check if a codeversion already exists
    public boolean codeVersionExist(String version){
        Codeversion codeversion= codeversionRepository.findCodeversionByVersion(description);
        if(codeversion != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
*/
    //to store codeveersion
    public Codeversion setCodeversion(Codeversion codeversion) {
        codeversionRepository.save(codeversion);
        return codeversion;
    }


//now handled by relatonship
    /*
    //to check if a combination of sensor name and sensor parameter already exists
    public boolean sensorParameterNameExist(String parametername, String sensorname)
    {
        Sensorparameter sensorparameter=sensorparameterRepository.findSensorparameterBySensornameAndParameter(parametername,sensorname);
        if ((sensorparameter!=null))
            return true;
        else
            return false;
    }
    */
    //to store sensor parameter

    public Sensorparameter setSensorparameter(Sensorparameter sensorparameter) {
        sensorparameterRepository.save(sensorparameter);
        return sensorparameter;
    }


    public boolean sensorCombinationExist(Sensorcombination sensorcombination)
        {
        Long code = sensorcombination.getSensorcombinationcode();
        Sensorcombination S1 = sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(code);
        if (S1 != null)
            return true;

            return false;
        }


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

    public void deviceExistInPublicside(Location location,Long deviceid)
    {
        Publicside publicside=publicsideRepository.findPublicsideByDeviceid(deviceid);
        Publicside publicside1=new Publicside();


        if(publicside==null)
        {
            publicside1.setDeviceid(deviceid);
            publicside1.setLatitude(location.getLatitude());
            publicside1.setLongitude(location.getLongitude());
            publicsideRepository.save(publicside1);
        }
        else
        {
            Publicside publicside2=publicside;
            publicside2.setDeviceid(deviceid);
            publicside2.setLongitude(location.getLongitude());
            publicside2.setLatitude(location.getLatitude());
            publicsideRepository.delete(publicside);
            publicsideRepository.save(publicside2);
        }

    }



  public void UPDATE_PUBLICSIDE_PARTNERPREVIOUS_PM25_AQI(Long PmValue,Long deviceid)
  {

      Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
      Partnerpreviousaqi partnerpreviousaqi=new Partnerpreviousaqi();
      Partnerpreviouspm25 partnerpreviouspm25=new Partnerpreviouspm25();
      partnerpreviousaqi.setDeviceid(deviceid);
      partnerpreviousaqi.setAqi(PmValue);
      partnerpreviousaqi.setTimestamp(timestamp);
      partnerpreviousaqiRepository.save(partnerpreviousaqi);

      partnerpreviouspm25.setDeviceid(deviceid);
      partnerpreviouspm25.setPM25(PmValue);
      partnerpreviouspm25.setTimestamp(timestamp);
      partnerpreviouspm25Repository.save(partnerpreviouspm25);

      Publicside publicside=publicsideRepository.findPublicsideByDeviceid(deviceid);
      Publicside publicside1=new Publicside();
      publicside1.setDeviceid(deviceid);
      publicside1.setLatitude(publicside.getLatitude());
      publicside1.setLongitude(publicside.getLongitude());
      publicside1.setAqi(PmValue);
      publicsideRepository.delete(publicside);
      publicsideRepository.save(publicside1);
      //return;
  }






    public Error FindError(Long errorcode) {
        return errorRepository.findErrorByErrorcode(errorcode);
    }

    public void updateLocation(Location location) {

        locationRepository.save(location);
        }

    //to store error log
    public Response setErrorlog(Errorlog errorlog) {
        errorlogRepository.save(errorlog);
        Response response = new Response();
        response.setResponse("ERRORLOG RECORDED");
        return response;

    }

    //to retrieve error log
    public Iterable<Errorlog> getErrorlog(Long deviceid) {
        return errorlogRepository.findAllByDeviceid(deviceid);
    }


    /*
        public boolean locationExist(Location location)
        {
            Long code=sensorcombination.getSensorcombinationcode();
            Sensorcombination S1=sensorcombinationRepository.findSensorcombinationBySensorcombinationcode(code);
            if(S1 != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    */
    //to store sensorcombination
    public Sensorcombination setSensorcombination(Sensorcombination sensorcombination) {
        sensorcombinationRepository.save(sensorcombination);
        return sensorcombination;
    }

    /*//to store datapacket
    public Datapacket setDatapacket(Datapacket datapacket){
        datapacketRepository.save(datapacket);
        return datapacket;
    }*/

    //to get Login List
    public Iterable<Login> getLogin() {

        return loginRepository.findAll();
    }

    // check for upgrade of firmware

    public Response CheckForUpgrade(Long id, Long sensor_combination_code, Long code_version)

    {
        //Devices device=  deviceRepository.findDeviceById(id);
        //String version =device.getCode_version();
        //Codeversion
        //Codeversion device_code_version=  codeversionRepository.findCodeversionByVersion(code_version);
        //Long latest_code_version= codeversionRepository.findOneByMaxVersion();
        //Codeversion latest_code_version_record=codeversionRepository.findCodeversionByVersion(latest_code_version);
        //Long latest_code_version_version=latest_code_version_record.getVersion();

        Codeversion latest_code_version_record = codeversionRepository.findTopByOrderByVersionidDesc();
        Long latest_code_version = latest_code_version_record.getVersionid();

        String URL = " UP TO DATE";
        if (latest_code_version > code_version) {
            URL = latest_code_version_record.getFirmware_url();

        }
        //codeversionRepository.findByOrderVersionGreaterThan(device_code_version);
        Response response = new Response();
        response.setResponse(URL);
        return response;

    }


    //upgrade code version in device record
    public Response UpgradeDeviceRecord(Long deviceid, Long sensor_combination_code, Long code_version)

    {

        Devices device = deviceRepository.findDeviceByDeviceid(deviceid);
        device.setCode_version(code_version);
        deviceRepository.save(device);

        Response response = new Response();
        response.setResponse("FIRMWARE UPGRADED!");
        return response;


    }

    //to store location
    public Location setLocation(Location location) {
        locationRepository.save(location);
        return location;
    }


    //

    public Iterable<Location> getlocations() {
        return locationRepository.findAll();
    }


    //


    public void setDatapacketRecord(Packetrecord record)
    {

        datapacketrecordRepository.save(record);

    }



    public Iterable<Publicside> getAllPublicside()
    {
        return publicsideRepository.findAll();
    }


    public Iterable<Partnerpreviousaqi> FIND_ALL_PARTNER_PREVIOUS_AQI(Long deviceid)
    {
        return partnerpreviousaqiRepository.findAllByDeviceid(deviceid);
    }



    public Iterable<Partnerpreviouspm25> FIND_ALL_PARTNER_PREVIOUS_PM25(Long deviceid)
    {
        return partnerpreviouspm25Repository.findAllByDeviceid(deviceid);
    }



    public Response FIND_DELETE_DEVICE(Long deviceid) {

        Response response=new Response();
        Devices device = deviceRepository.findDeviceByDeviceid(deviceid);
        if (device != null) {
            deviceRepository.delete(device);
            response.setResponse("DEVICE DELETED!");
        }
        else
        {
            response.setResponse("UNKNOWN DEVICE ID");
        }

        return response;

    }

    public Iterable<Devices> FIND_DEVICES_LIST() {
        return deviceRepository.findAll();

    }


}
