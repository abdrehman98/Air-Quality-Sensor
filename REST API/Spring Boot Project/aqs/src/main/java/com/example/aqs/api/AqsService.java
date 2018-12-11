package com.example.aqs.api;


import com.example.aqs.admin.Admin;
import com.example.aqs.admin.AdminRepository;
import com.example.aqs.codeversion.Codeversion;
import com.example.aqs.codeversion.CodeversionRepository;
import com.example.aqs.datapacket.Datapacket;
import com.example.aqs.datapacket.DatapacketRepository;
import com.example.aqs.datapacketrecord.Datapacketrecord;
import com.example.aqs.datapacketrecord.DatapacketrecordRepository;
import com.example.aqs.devices.Devices;
import com.example.aqs.devices.DevicesRepository;
import com.example.aqs.error.Error;
import com.example.aqs.error.ErrorRepository;
import com.example.aqs.errorlog.Errorlog;
import com.example.aqs.errorlog.ErrorlogRepository;
import com.example.aqs.location.Location;
import com.example.aqs.location.LocationRepository;
import com.example.aqs.login.Login;
import com.example.aqs.login.LoginRepository;
import com.example.aqs.previouslocation.Previouslocation;
import com.example.aqs.previouslocation.PreviouslocationRepository;
import com.example.aqs.sensor.Sensor;
import com.example.aqs.sensor.SensorRepository;
import com.example.aqs.sensorcombination.Sensorcombination;
import com.example.aqs.sensorcombination.SensorcombinationRepository;
import com.example.aqs.sensorparameter.Sensorparameter;
import com.example.aqs.sensorparameter.SensorparameterRepository;
import com.example.aqs.values.Values;
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

    @Autowired
    DevicesRepository deviceRepository;

    @Autowired
    SensorparameterRepository sensorparameterRepository;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    CodeversionRepository codeversionRepository;

    @Autowired
    DatapacketRepository datapacketRepository;

    @Autowired
    DatapacketrecordRepository datapacketrecordRepository;

    @Autowired
    ErrorRepository errorRepository;

    @Autowired
    ErrorlogRepository errorlogRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PreviouslocationRepository previouslocationRepository;


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
    //to store admin login
    public Admin setAdmin(Admin admin){
        adminRepository.save(admin);
        return admin;
    }









    //to check if device exists already
        public boolean deviceIdExist(Long deviceid){
        Devices device1 = deviceRepository.findDeviceByDeviceid(deviceid);
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






    //to check if a sensor recod already exists
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





    //to check if a error recod already exists
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

    //to store errorcode
    public Error setError(Error error){
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
    public Codeversion setCodeversion(Codeversion codeversion){
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

    public Sensorparameter setSensorparameter(Sensorparameter sensorparameter)
    {
        sensorparameterRepository.save(sensorparameter);
        return  sensorparameter;
    }






    public boolean sensorCombinationExist(Sensorcombination sensorcombination)
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

    public Boolean deviceExistInLocationTable(Location location)
    {
     Long deviceid=location.getDeviceid();
     Location previousLocation = locationRepository.findLocationByDeviceid(deviceid);
     if(previousLocation!=null)
     {
         //java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
         Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));


         Previouslocation P_location=new Previouslocation();
         P_location.setDeviceid(previousLocation.getDeviceid());
         P_location.setLocationtime(timestamp);
         P_location.setLatitude(previousLocation.getLatitude());
         P_location.setLongitude(previousLocation.getLongitude());
         previouslocationRepository.save(P_location);
         locationRepository.delete(previousLocation);
         //return P_location;
         return true;
     }
     else
         { return false; }

    }

    public Location updateLocation(Location location)
    {

        locationRepository.save(location);

        return location;
    }

//to store error log
    public Errorlog setErrorlog(Errorlog errorlog)
    {
        errorlogRepository.save(errorlog);
        return errorlog;
    }

    //to retrieve error log
    public Iterable<Errorlog> getErrorlog(Long deviceid)
    {
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
    public Sensorcombination setSensorcombination(Sensorcombination sensorcombination){
        sensorcombinationRepository.save(sensorcombination);
        return sensorcombination;
    }

    /*//to store datapacket
    public Datapacket setDatapacket(Datapacket datapacket){
        datapacketRepository.save(datapacket);
        return datapacket;
    }*/

    //to get Login List
    public Iterable<Login> getLogin(){

        return loginRepository.findAll();
    }

    // check for upgrade of firmware

    public String CheckForUpgrade(Long id,Long sensor_combination_code,Long code_version )

    {
        //Devices device=  deviceRepository.findDeviceById(id);
        //String version =device.getCode_version();
        //Codeversion
        //Codeversion device_code_version=  codeversionRepository.findCodeversionByVersion(code_version);
        //Long latest_code_version= codeversionRepository.findOneByMaxVersion();
        //Codeversion latest_code_version_record=codeversionRepository.findCodeversionByVersion(latest_code_version);
        //Long latest_code_version_version=latest_code_version_record.getVersion();

        Codeversion latest_code_version_record=codeversionRepository.findTopByOrderByVersionidDesc();
        Long latest_code_version = latest_code_version_record.getVersionid();

        String URL=" This firmware is up to date.";
        if(latest_code_version> code_version)
        {
         URL = latest_code_version_record.getFirmware_url();

        }
        //codeversionRepository.findByOrderVersionGreaterThan(device_code_version);
        return  URL;

    }

//upgrade code version in device record
public Devices UpgradeDeviceRecord(Long deviceid,Long sensor_combination_code, Long code_version)

{

    Devices  device=deviceRepository.findDeviceByDeviceid(deviceid);
    device.setCode_version(code_version);
    deviceRepository.save(device);
    return device;

}
    //to store location
    public Location setLocation(Location location){
        locationRepository.save(location);
        return location;
    }



    //

     public Iterable<Location>    getlocations()
     {
         return locationRepository.findAll();
     }


    //



    public Datapacketrecord setDatapacketRecord(Datapacketrecord record)
    {
            datapacketrecordRepository.save(record);
            return record;
    }

//

public Boolean FIND_DELETE_DEVICE(Long id)
    {

    Devices device = deviceRepository.findDeviceByDeviceid(id);
    Boolean B=true;
    if(device!= null)
    { deviceRepository.delete(device); }
    else
    { B=false; }

    return B;

    }

  public Iterable<Devices>  FIND_DEVICES_LIST()
  {
     return deviceRepository.findAll();

  }


}
