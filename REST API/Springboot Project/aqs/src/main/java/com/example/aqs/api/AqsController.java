package com.example.aqs.api;

import com.example.aqs.admin.Admin;
import com.example.aqs.devices.Devices;
import com.example.aqs.error.Error;
import com.example.aqs.error.ErrorRepository;
import com.example.aqs.login.Login;
import com.example.aqs.sensor.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Calendar;

@Controller
@RequestMapping(value = {"/","aqs"})

public class AqsController {

    @Autowired
    AqsService aqsService;

    //Login new user
    @RequestMapping(value={"/login"},method = RequestMethod.POST)
    public @ResponseBody
    Login SetLogin(@RequestBody Login login){
        //Entry entry = user.getEntry();


        //Login login1=login;
        String email = login.getEmail();
        if(aqsService.loginEmailExist(email))
        {
            //do nothing
            return null;
        }
        else
        {
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
    @RequestMapping(value={"/admin"},method = RequestMethod.POST)
    public @ResponseBody
    Admin SetAdmin(@RequestBody Admin admin){
        //Entry entry = user.getEntry();


        //Login login1=login;
        String email = admin.getEmail();
        if(aqsService.adminEmailExist(email))
        {
            //do nothing
            return null;
        }
        else
        {
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
    @RequestMapping(value={"/devices"},method = RequestMethod.POST)
    public @ResponseBody
    Devices SetDevice(@RequestBody Devices device){
        //Entry entry = user.getEntry();


        //Login login1=login;
        Long id1 = device.getId();
        if(aqsService.deviceIdExist(id1))
        {
            //do nothing
            return null;
        }
        else
        {
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
    @RequestMapping(value={"/sensor"},method = RequestMethod.POST)
    public @ResponseBody
    Sensor SetSensor(@RequestBody Sensor sensor){
        //Entry entry = user.getEntry();


        //Login login1=login;
        String name = sensor.getName();
        if(aqsService.sensorNameExist(name))
        {
            //do nothing
            return null;
        }
        else
        {
            //create a new user
            //entry.setLoginAs("user");
            //login.setId(login.getId());
            //login.setEmail(login.getEmail());
            //login.setPassword(login.getPassword());
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            sensor.setCreatedat(time);
            sensor.setUpdatedat(time);

            return aqsService.setSensor(sensor);
            //return crudService.setUser(user);
        }
    }

    //Add a new sensor
    @RequestMapping(value={"/error"},method = RequestMethod.POST)
    public @ResponseBody
    Error SetError(@RequestBody Error error){
        //Entry entry = user.getEntry();


        //Login login1=login;
        Long errorcode = error.getErrorcode();
        if(aqsService.errorCodeExist(errorcode))
        {
            //do nothing
            return null;
        }
        else
        {
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

}
