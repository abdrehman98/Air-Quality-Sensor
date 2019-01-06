package com.example.aqs_new.api;

import com.example.aqs_new.Response.Response;
import com.example.aqs_new.admin.Admin;
import com.example.aqs_new.codeversion.Codeversion;
import com.example.aqs_new.datapacket.Datapacket;
import com.example.aqs_new.datapacketrecord.Packetrecord;
import com.example.aqs_new.devices.Devices;
import com.example.aqs_new.error.Error;
import com.example.aqs_new.errorlog.Errorlog;
import com.example.aqs_new.location.Location;
import com.example.aqs_new.login.Login;
import com.example.aqs_new.partnerpreviousaqi.Partnerpreviousaqi;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25;
import com.example.aqs_new.publicside.Publicside;
import com.example.aqs_new.sensor.Sensor;
import com.example.aqs_new.sensorcombination.Sensorcombination;
import com.example.aqs_new.sensorparameter.Sensorparameter;
import com.example.aqs_new.values.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;

@Controller
@RequestMapping(value = {"/", "aqs"})

public class AqsController {

    @Autowired
    AqsService aqsService;


    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    ////////////////////POST REQUEST METHODS/////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////


    //Login new user
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public @ResponseBody
    Login SetLogin(@RequestBody Login login) {
        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        login.setTimestamp(timestamp);
        return aqsService.setLogin(login);

    }


    //Login New Admin

    @RequestMapping(value = {"/admin/{createdby}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetAdmin(@RequestBody Admin admin, @PathVariable Long createdby) {


        String email = admin.getEmail();
        Response response = new Response();
        Admin admin1 = aqsService.FIND_ADMIN(createdby);
        if (admin1 == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else if (aqsService.adminEmailExist(email)) {
            response.setResponse("ENTER OTHER EMAIL");
        } else {
            admin.setCreatedby(createdby);
            aqsService.setAdmin(admin);
            response.setResponse("ADMIN REGISTERED");
        }
        return response;
    }


/*
    //Adminloginsession
    @RequestMapping(value = {"/adminlogin"}, method = RequestMethod.POST)
    public @ResponseBody

    Adminlogin SetAdminlogin(@RequestBody Adminlogin adminlogin) {
        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        adminlogin.setTimestamp(timestamp);
        return aqsService.setAdminlogin(adminlogin);

    }
*/


    //Register a new device
    @RequestMapping(value = {"/admin/devices/{id}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetDevice(@RequestBody Devices device, @PathVariable Long id) {

        Admin admin = aqsService.FIND_ADMIN(id);
        Response response = new Response();
        Long deviceid = device.getDeviceid();
        Sensorcombination sensorcombinationObject = aqsService.FIND_DEVICE_SENSOR_COMBINATION_EXIST(device.getSensor_combination_code());
        Codeversion codeversion = aqsService.FIND_DEVICE_CODE_VERSION_EXIST(device.getCode_version());

        if (admin == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else if (aqsService.deviceIdExist(deviceid)) {
            response.setResponse("DEVICE ID ALREADY EXIST");
            //do nothing
        } else if (sensorcombinationObject == null) {
            response.setResponse("UNKNOWN SENSORCOMBINATION");

        } else if (codeversion == null) {
            response.setResponse("UNKNOWN CODE VERSION");
        } else {
            Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
            device.setCreated_at(timestamp);
            device.setCreated_by_admin_id(admin.getId());
            device.setAdmin(admin);
            device.setSensorcombination(sensorcombinationObject);
            device.setCodeversion(codeversion);
            aqsService.setDevice(device);
            response.setResponse("DEVICE REGISTERED");
        }
        return response;
    }


    //Add a new sensor
    @RequestMapping(value = {"/admin/sensor/{id}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetSensor(@RequestBody Sensor sensor, @PathVariable Long id) {

        Response response = new Response();
        Admin admin = aqsService.FIND_ADMIN(id);

        String name = sensor.getName();
        if (aqsService.sensorNameExist(name)) {
            response.setResponse("SENSOR NAME EXISTS ALREADY");
        } else if (admin == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else {
            Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));

            sensor.setCreatedat(timestamp);
            //sensor.setUpdatedat(timestamp);
            sensor.setCtrated_by_admin_id(admin.getId());
            sensor.setAdmin(admin);
            aqsService.setSensor(sensor);
            response.setResponse("SENSOR REGISTERED");
        }
        return response;
    }


    //Add a new sensorcombination
    @RequestMapping(value = {"/admin/sensorcombination/{id}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetSensorcombination(@RequestBody Sensorcombination sensorcombination, @PathVariable Long id) {
        Response response = new Response();
        Admin admin = aqsService.FIND_ADMIN(id);
        if (aqsService.sensorCombinationExist(sensorcombination)) {
            //do nothing
            return null;
        } else if (admin == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else {    //java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());

            Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));

            sensorcombination.setCreatedat(timestamp);
            sensorcombination.setUpdatedat(timestamp);
            sensorcombination.setCreated_by_admin_id(admin.getId());
            sensorcombination.setAdmin(admin);
            aqsService.setSensorcombination(sensorcombination);
            response.setResponse("SENSOR COMBINATION REGISTERED");
        }
        return response;
    }


    //Add a new sensor parameter
    @RequestMapping(value = {"admin/sensor/sensorparameter/{id}/{sensorname}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetSensorparameter(@RequestBody Sensorparameter sensorparameter, @PathVariable Long id, @PathVariable String sensorname) {

        Response response = new Response();
        Admin admin = aqsService.FIND_ADMIN(id);
        Sensor sensor = aqsService.FIND_SENSOR(sensorname);
        if (admin == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else if (sensor == null) {
            response.setResponse("SENSOR NAME DO NOT EXIST");
        } else {
            sensorparameter.setSensorname(sensor.getName());
            sensorparameter.setAdmin(admin);
            sensorparameter.setSensor(sensor);
            aqsService.setSensorparameter(sensorparameter);
            response.setResponse("SENSOR PARAMETER REGISTERED");
        }
        return response;
    }


    //Add a new error
    @RequestMapping(value = {"/admin/error/{id}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetError(@RequestBody Error error, @PathVariable Long id) {
        Response response = new Response();
        Admin admin = aqsService.FIND_ADMIN(id);
        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        //Long errorcode = error.getErrorcode();
        /*if (aqsService.errorCodeExist(errorcode)) {
            //do nothing

        }*/
        if (admin == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else {


            error.setCreatedat(timestamp);
            //error.setUpdatedat(timestamp);
            //Admin admin = error.getAdmin();
            error.setCreated_by_admin_id(admin.getId());
            error.setAdmin(admin);
            aqsService.setError(error);
            response.setResponse("ERROR REGISTERED");
        }

        return response;
    }


    //Add a new codeversion
    @RequestMapping(value = {"/admin/codeversion/{id}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetCodeversion(@RequestBody Codeversion codeversion, @PathVariable Long id) {
        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        Response response = new Response();
        Long sensorcombinationcode = codeversion.getSensorcombinationcode();
        Sensorcombination sensorcombination = aqsService.FIND_SENSOR_COMBINATION(sensorcombinationcode);
        Admin admin = aqsService.FIND_ADMIN(id);
        if (admin == null) {
            response.setResponse("YOU ARE NOT ADMIN");
        } else if (sensorcombination == null) {
            response.setResponse("INVALID SENSOR COMBINATION");
        } else {
            codeversion.setCreated_by_admin_id(admin.getId());
            codeversion.setCreatedat(timestamp);
            codeversion.setAdmin(admin);
            response.setResponse("CODE VERSION REGISTERED");
            aqsService.setCodeversion(codeversion);
        }
        return response;

    }


    //Add a new Location
    @RequestMapping(value = {"/devices/location/{deviceid}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetLocation(@RequestBody Location location, @PathVariable Long deviceid) {
        Response response = new Response();
        Devices device = aqsService.FindDevice(deviceid);

        if (device == null) {
            response.setResponse("DEVICE NOT EXIST");
        } else {
            aqsService.deviceExistInLocationTable(deviceid); //to check if the request is made by a brand new device or an old device
            aqsService.deviceExistInPublicside(location, deviceid); // to update location of device in public side table

            Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
            location.setLocationtime(timestamp);
            location.setDeviceid(deviceid);
            location.setDevice(device);
            aqsService.updateLocation(location); // add or update location of device location in location table
            response.setResponse("DEVICE LOCATION UPDATED");
        }
        return response;
    }


    //Add a new Errorlog
    @RequestMapping(value = {"devices/errorlog/{deviceid}/{errorcode}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetErrorlog(@RequestBody Errorlog errorlog, @PathVariable Long deviceid, @PathVariable Long errorcode) {
        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        Error error = aqsService.FindError(errorcode);
        Devices device = aqsService.FindDevice(deviceid);

        Response response = new Response();
        if (device == null) {
            response.setResponse("DEVICE NOT EXIST");
            return response;
        } else if (error == null) {
            response.setResponse("UNKNOWN ERROR OCCURRED");
            return response;
        } else {
            errorlog.setErroroccurtime(timestamp);
            errorlog.setDeviceid(device.getDeviceid());
            errorlog.setErrorcode(error.getErrorcode());
            errorlog.setError(error);
            errorlog.setDevice(device);
            response = aqsService.setErrorlog(errorlog);
        }
        return response;
    }


    //Add new packet records
    @RequestMapping(value = {"devices/packetrecord/{deviceid}"}, method = RequestMethod.POST)
    public @ResponseBody
    Response SetDatapacket(@RequestBody Datapacket datapacket, @PathVariable Long deviceid) {

        Timestamp timestamp = new Timestamp((Calendar.getInstance().getTime().getTime()));
        Boolean status = datapacket.getStatus();
        Values[] vals = datapacket.getValues();
        int No_Of_Values = vals.length;  //number of values in data packet.
        int count = 0;
        Long PmValue = deviceid;
        Response response = new Response();
        Devices device = aqsService.FindDevice(deviceid);
        if (device == null) {
            response.setResponse("DEVICE NOT EXIST");
        } else {

            for (int j = 0; j < No_Of_Values; j++) {

                Packetrecord record = new Packetrecord();
                record.setDeviceid(device.getDeviceid());
                record.setReceivetime(timestamp);
                record.setDevice(device);
                record.setSensorname(vals[j].getSensor_name());
                record.setParametername(vals[j].getParameter_name());
                record.setParametervalue(vals[j].getValue());
                record.setStatus(status);
                if (vals[j].getParameter_name().equals("PM2.5")) {
                    PmValue = vals[j].getValue();
                }

                aqsService.setDatapacketRecord(record);
                count++;
            }
        }
        aqsService.UPDATE_PUBLICSIDE_PARTNERPREVIOUS_PM25_AQI(PmValue, deviceid);

        if (count > 0) {
            response.setResponse("DATA SAVED");
        } else if (count == 0) {
            response.setResponse("PACKET WAS EMPTY");
        }
        return response;
    }


    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////GET REQUEST METHODS/////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////


    //Retrieve errorlogs for a device
    @RequestMapping(value = {"/admin/errorlog/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Errorlog> GetDeviceErrorlogs(@PathVariable("deviceid") Long deviceid) {


        return aqsService.getErrorlog(deviceid);

    }


    //Retrieve all Publicside records
    @RequestMapping(value = {"/publicside"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Publicside> GetPublicside() {

        return aqsService.getAllPublicside();

    }


    //Retrieve all prvious AQI values of a device
    @RequestMapping(value = {"/devices/partnerpreviousaqi/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Partnerpreviousaqi> getPartnerPreviousAqi(@PathVariable("deviceid") Long deviceid) {

        return aqsService.FIND_ALL_PARTNER_PREVIOUS_AQI(deviceid);

    }

    //Retrieve all prvious PM2.5 values of a device
    @RequestMapping(value = {"/devices/partnerpreviouspm25/{deviceid}"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Partnerpreviouspm25> getPartnerPreviousPm25(@PathVariable("deviceid") Long deviceid) {

        return aqsService.FIND_ALL_PARTNER_PREVIOUS_PM25(deviceid);

    }


    //Retrieve locations of all devices
    @RequestMapping(value = {"/location"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Location> GetLocations() {


        return aqsService.getlocations();

    }


    //Get Login
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Login> GetLogin()

    {
        return aqsService.getLogin();
    }


    //Check for firmware upgrade
    @RequestMapping(value = {"/devices/{id}/{sensor_combination_code}/{code_version}"}, method = RequestMethod.GET)
    public @ResponseBody
    Response CheckCodeVersionUpgradeAvailabilty(@PathVariable("id") Long id, @PathVariable("sensor_combination_code") Long sensor_combination_code, @PathVariable("code_version") Long code_version)

    {

        return aqsService.CheckForUpgrade(id, sensor_combination_code, code_version);

    }


    //Find list of Devices
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Devices> FindListOfDevices()

    {

        return aqsService.FIND_DEVICES_LIST();

    }


    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////PUT REQUEST METHODS/////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////


    //firmware upgrade Confirmation Acknowledgement
    @RequestMapping(value = {"/devices/{id}/{sensor_combination_code}/{code_version}"}, method = RequestMethod.PUT)
    public @ResponseBody
    Response VersionUpgradeRecord(@PathVariable("id") Long id, @PathVariable("sensor_combination_code") Long sensor_combination_code, @PathVariable("code_version") Long code_version)

    {
        return aqsService.UpgradeDeviceRecord(id, sensor_combination_code, code_version);

    }


    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////DELETE REQUEST METHODS//////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////


    //Delete Device
    @RequestMapping(value = {"/admin/{deviceid}"}, method = RequestMethod.DELETE)
    public @ResponseBody
    Response DeleteDevice(@PathVariable("deviceid") Long deviceid)

    {
        Response response = aqsService.FIND_DELETE_DEVICE(deviceid);
        return response;
    }


}