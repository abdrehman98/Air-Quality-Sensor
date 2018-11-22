package com.example.aqs.api;

import com.example.aqs.admin.Admin;
import com.example.aqs.codeversion.Codeversion;
import com.example.aqs.datapacket.Datapacket;
import com.example.aqs.datapacketrecord.Datapacketrecord;
import com.example.aqs.devices.Devices;
import com.example.aqs.error.Error;
import com.example.aqs.error.ErrorRepository;
import com.example.aqs.errorlog.Errorlog;
import com.example.aqs.location.Location;
import com.example.aqs.login.Login;
import com.example.aqs.sensor.Sensor;
import com.example.aqs.sensorcombination.Sensorcombination;
import com.example.aqs.sensorparameter.Sensorparameter;
import com.example.aqs.values.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
@RequestMapping(value = {"/","aqs"})

public class AqsController {

    @Autowired
    AqsService aqsService;

    //Login new user
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public @ResponseBody
    Login SetLogin(@RequestBody Login login) {
        String email = login.getEmail();
        if (aqsService.loginEmailExist(email)) {
            //do nothing
            return null;
        } else {
            //create a new user
            //entry.setLoginAs("user");
            //login.setId(login.getId());
            //login.setEmail(login.getEmail());
            //login.setPassword(login.getPassword());
            return aqsService.setLogin(login);
            //return crudService.setUser(user);
        }
    }

    //Login New Admin
    @RequestMapping(value = {"/admin"}, method = RequestMethod.POST)
    public @ResponseBody
    Admin SetAdmin(@RequestBody Admin admin) {
        //Entry entry = user.getEntry();


        //Login login1=login;
        String email = admin.getEmail();
        /*if (aqsService.adminEmailExist(email)) {
            //do nothing
            return null;
        } else */{
            //create a new user
            //entry.setLoginAs("user");
            //admin.setId(admin.getId());
            //admin.setEmail(admin.getEmail());
            //admin.setPassword(admin.getPassword());

            //return admin;
            //return crudService.setUser(user);
            return aqsService.setAdmin(admin);
        }
    }

    //Register a new device
    @RequestMapping(value = {"/devices"}, method = RequestMethod.POST)
    public @ResponseBody
    Devices SetDevice(@RequestBody Devices device) {
        //Entry entry = user.getEntry();


        //Login login1=login;
        Long id1 = device.getId();
        if (aqsService.deviceIdExist(id1)) {
            //do nothing
            return null;
        } else {
            //create a new user
            //entry.setLoginAs("user");
            //login.setId(login.getId());
            //login.setEmail(login.getEmail());
            //login.setPassword(login.getPassword());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            device.setCreated_at(time);
            device.setUpdated_at(time);

            return aqsService.setDevice(device);
            //return crudService.setUser(user);
        }
    }

    //Add a new sensor
    @RequestMapping(value = {"/sensor"}, method = RequestMethod.POST)
    public @ResponseBody
    Sensor SetSensor(@RequestBody Sensor sensor) {

        String name = sensor.getName();
        if (aqsService.sensorNameExist(name)) {
            //do nothing
            return null;
        } else {

            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            sensor.setCreatedat(time);
            sensor.setUpdatedat(time);

            return aqsService.setSensor(sensor);
        }
    }


    //Add a new sensor parameter
    @RequestMapping(value = {"/sensorparameter"}, method = RequestMethod.POST)
    public @ResponseBody
    Sensorparameter SetSensorparameter(@RequestBody Sensorparameter sensorparameter) {

        String parametername = sensorparameter.getParameter();
        String sensorname = sensorparameter.getSensorname();
        //to check if a combination of sensor name and sensor parameter already exists
        if (aqsService.sensorParameterNameExist(parametername,sensorname))
        {
            //do nothing
            return null;
        }
        else
        {
            return aqsService.setSensorparameter(sensorparameter);
        }
    }



    //Add a new error
    @RequestMapping(value = {"/error"}, method = RequestMethod.POST)
    public @ResponseBody
    Error SetError(@RequestBody Error error) {
        //Entry entry = user.getEntry();


        //Login login1=login;
        Long errorcode = error.getErrorcode();
        if (aqsService.errorCodeExist(errorcode)) {
            //do nothing
            return null;
        } else {
            //create a new user
            //entry.setLoginAs("user");
            //login.setId(login.getId());
            //login.setEmail(login.getEmail());
            //login.setPassword(login.getPassword());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            error.setCreatedat(time);
            error.setUpdatedat(time);
            Admin admin=error.getAdmin();
            error.setAdmin(admin);
            return aqsService.setError(error);
            //return crudService.setUser(user);
        }
    }
/*
    //Add a new errorlog
    @RequestMapping(value = {"/errorlog"}, method = RequestMethod.POST)
    public @ResponseBody
    Errorlog SetErrorlog(@RequestBody Errorlog errorlog) {
        //Entry entry = user.getEntry();


        //Login login1=login;
        Long errorcode = error.getErrorcode();
        if (aqsService.errorCodeExist(errorcode)) {
            //do nothing
            return null;
        } else {
            //create a new user
            //entry.setLoginAs("user");
            //login.setId(login.getId());
            //login.setEmail(login.getEmail());
            //login.setPassword(login.getPassword());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            error.setCreatedat(time);
            error.setUpdatedat(time);

            return aqsService.setError(error);
            //return crudService.setUser(user);
        }
    }
*/
    //Add a new codeversion
    @RequestMapping(value = {"/codeversion"}, method = RequestMethod.POST)
    public @ResponseBody
    Codeversion SetCodeversion(@RequestBody Codeversion codeversion) {
        //Entry entry = user.getEntry();


        //Login login1=login;
        String version = codeversion.getVersion();
        /*if (aqsService.codeVersionExist(version)) {
            //do nothing
            return null;
        } else*/ {
            //create a new user
            //entry.setLoginAs("user");
            //login.setId(login.getId());
            //login.setEmail(login.getEmail());
            //login.setPassword(login.getPassword());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            codeversion.setCreatedat(time);
            codeversion.setUpdatedat(time);

            return aqsService.setCodeversion(codeversion);
            //return crudService.setUser(user);
        }
    }

    //Add a new Location
    @RequestMapping(value = {"/location"}, method = RequestMethod.POST)
    public @ResponseBody
    Location SetLocation(@RequestBody Location location) {

        aqsService.deviceExistInLocationTable(location);

        java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
        location.setLocationtime(time);

        aqsService.updateLocation(location);
        
        return location;
    }

    //Add a new Errorlog
    @RequestMapping(value = {"/errorlog"}, method = RequestMethod.POST)
    public @ResponseBody
    Errorlog SetErrorlog(@RequestBody Errorlog errorlog) {


        java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
        errorlog.setOccurtime(time);

        aqsService.setErrorlog(errorlog);

        return errorlog;
    }

    //Retrieve errorlogs fro a device
    @RequestMapping(value = {"/errorlog/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Errorlog> GetDeviceErrorlogs(@PathVariable("deviceid") Long deviceid) {



        return aqsService.getErrorlog(deviceid);


    }


    //Add a new sensorcombination
    @RequestMapping(value = {"/sensorcombination"}, method = RequestMethod.POST)
    public @ResponseBody
    Sensorcombination SetSensorcombination(@RequestBody Sensorcombination sensorcombination) {
        //Entry entry = user.getEntry();


        //Login login1=login;
        //Long sensorCombinationCode= sensorcombination.getSensorcombinationcode();
        if (aqsService.sensorCombinationExist(sensorcombination))
        {
            //do nothing
            return null;
        }
        else
        {
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            sensorcombination.setCreatedat(time);
            sensorcombination.setUpdatedat(time);
            return aqsService.setSensorcombination(sensorcombination);

        }
    }


    //Add a new datapacket
    @RequestMapping(value = {"/datapacket"}, method = RequestMethod.POST)
    public @ResponseBody
    void/*ArrayList<Datapacketrecord>*/ SetDatapacket(@RequestBody Datapacket datapacket) {
                java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
        datapacket.setReceivetime(time);

        Values[] vals=datapacket.getValues();
        int i=vals.length;  //number of values in data packet.
        Datapacketrecord[] records=new Datapacketrecord[i];
        for( int j=0;j<i;j++)
        {
            Datapacketrecord record=new Datapacketrecord();
            record.setDeviceid(datapacket.getDeviceid());
            record.setSensorname(vals[j].getSensor_name());
            record.setReceivetime(time);
            record.setValue(vals[j].getValue());
            records[j]=record;
            /*return*/ aqsService.setDatapacketRecord(record);

        }

        }


    //Get Login
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Login> GetLogin()

    {
        return aqsService.getLogin();
    }


}

