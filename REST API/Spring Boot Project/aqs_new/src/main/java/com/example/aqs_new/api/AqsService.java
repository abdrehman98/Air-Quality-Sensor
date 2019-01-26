package com.example.aqs_new.api;

import com.example.aqs_new.Response.Response;
import com.example.aqs_new.codeversion.Codeversion;
import com.example.aqs_new.codeversion.CodeversionRepository;
import com.example.aqs_new.datarecord.DataRecord;
import com.example.aqs_new.datarecord.DataRecordRepository;
import com.example.aqs_new.device.Device;
import com.example.aqs_new.device.DeviceRepository;
import com.example.aqs_new.error.Error;
import com.example.aqs_new.error.ErrorRepository;
import com.example.aqs_new.errorlog.Errorlog;
import com.example.aqs_new.errorlog.ErrorlogRepository;
import com.example.aqs_new.location.Location;
import com.example.aqs_new.location.LocationRepository;
import com.example.aqs_new.partner.Partner;
import com.example.aqs_new.partner.PartnerRepository;
import com.example.aqs_new.partnersignin.Partnersignin;
import com.example.aqs_new.partnersignin.PartnersigninRepository;
import com.example.aqs_new.partnerpreviousaqi.Partnerpreviousaqi;
import com.example.aqs_new.partnerpreviousaqi.PartnerpreviousaqiRepository;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25;
import com.example.aqs_new.partnerpreviouspm25.Partnerpreviouspm25Repesitory;
import com.example.aqs_new.publicside.Publicside;
import com.example.aqs_new.publicside.PublicsideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AqsService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    CodeversionRepository codeversionRepository;

    @Autowired
    DataRecordRepository datarecordRepository;

    @Autowired
    ErrorRepository errorRepository;

    @Autowired
    ErrorlogRepository errorlogRepository;

    @Autowired
    LocationRepository locationRepository;

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


    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    //////////////////////////////FIND METHODS///////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////




    // To retrieve all previoue data values of PM2.5 for a particular device
    public Iterable<Partnerpreviouspm25> findAllPartnerPreviousPm25(Long deviceid) {
        return partnerpreviouspm25Repository.findAllByDeviceid(deviceid);
    }


    //To delete a particular device by device id...
    public Response deleteDevice(Long deviceid) {

        Response response = new Response();
        Device device = deviceRepository.findDeviceById(deviceid);
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

    //To get list of all devices from DB
    public Iterable<Device> findDeviceList()
    {
        return deviceRepository.findAll();
    }


    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    ////////////////////////EXISTENCE CHECK METHODS//////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////


    //to check if partner email exists already
    public boolean partnerEmailExist(String email) {
        Partner partner = partnerRepository.findByEmail(email);
        if (partner != null)
            return true;
        else
            return false;
    }


    //to check if device exists already
    public boolean deviceIdExist(Long deviceid) {
        Device device1 = deviceRepository.findDeviceById(deviceid);
        if (device1 != null)
            return true;

        return false;
    }



    //to check if device exists already
    public boolean deviceIdNotExist(Long deviceid) {
        Device device1 = deviceRepository.findDeviceById(deviceid);
        if (device1 == null)
            return true;

        return false;
    }

    //to check if device exists already
    public boolean errorIdNotExist(Long errorid) {
        Error error = errorRepository.findErrorById(errorid);
        if (error== null)
            return true;

        return false;
    }


    //to check if device exists already
    public boolean versionIdExist(Long versionid) {
        Codeversion codeversion = codeversionRepository.findCodeversionById(versionid);
        if (codeversion!= null)
            return true;

        return false;
    }

    //to check if device exists already
    public boolean codeVersionNotExist(Long versionid) {
        Codeversion codeversion = codeversionRepository.findCodeversionById(versionid);
        if (codeversion== null)
            return true;

        return false;
    }

    /*
    // To check if device is registered...if yes then update its location...if not exist then store in public side...
    public void deviceExistInPublicside(Location location) {
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
    */

    // To check if Password and email are entered correctly while partner signin
    public boolean partnerPasswordEmailMatchCorrectly(Partner partner) {
        Partner partner1 = partnerRepository.findByEmail(partner.getEmail());

        if (partner1.getPassword().equals(partner.getPassword()))

            return true;

        else
            return false;
    }


    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////HTTP METHODS////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////


    //to store partner login
    public void setPartnerSignin(Partnersignin partnersignin)

    {
        partnersigninRepository.save(partnersignin);
    }

    //to store device
    public Device setDevice(Device device) {
        deviceRepository.save(device);
        return device;
    }


    //to store new partner
    public void setPartner(Partner partner) {
        partnerRepository.save(partner);
    }


    //to store error
    public void setError(Error error) {
        errorRepository.save(error);
    }

    //  to store error log
    public void setErrorlog(Errorlog errorlog) {
        errorlogRepository.save(errorlog);
    }

    //to store codeveersion
    public Codeversion setCodeversion(Codeversion codeversion) {
        codeversionRepository.save(codeversion);
        return codeversion;
    }


    //To store a data record for a particular parameter e.g. PM2.5
    public void setDataRecord(DataRecord datarecord) {
        datarecordRepository.save(datarecord);
    }

    //to store location
    public Location setLocation(Location location) {
        locationRepository.save(location);
        return location;
    }

    // Update PM2.5 AND AQI in (partner previous PM2.5) AND (partner previous AQI)
    public void updatePublicsiePartnerPreviousPm25Aqi(DataRecord datarecord)
    {

        Partnerpreviousaqi partnerpreviousaqi = new Partnerpreviousaqi();
        Partnerpreviouspm25 partnerpreviouspm25 = new Partnerpreviouspm25();
        partnerpreviousaqi.setDeviceid(datarecord.getDeviceId());
        partnerpreviousaqi.setAqi(datarecord.getAqi());
        partnerpreviousaqiRepository.save(partnerpreviousaqi);
        partnerpreviouspm25.setDeviceid(datarecord.getDeviceId());
        partnerpreviouspm25.setPM25(datarecord.getAqi());
        partnerpreviouspm25Repository.save(partnerpreviouspm25);
        Publicside publicside = publicsideRepository.findPublicsideByDeviceid(datarecord.getId());
        Publicside publicside1 = new Publicside();
        publicside1.setDeviceid(datarecord.getId());
        publicside1.setLatitude(publicside.getLatitude());
        publicside1.setLongitude(publicside.getLongitude());
        publicside1.setLocationName(publicside.getLocationName());
        publicside1.setAqi(publicside.getAqi());
        publicsideRepository.delete(publicside);
        publicsideRepository.save(publicside1);
    }


    public void setPublicSideLocation(Location location)
    {
        Publicside publicside=publicsideRepository.findPublicsideByDeviceid(location.getDeviceId());
        Publicside publicside1=new Publicside();

        if(publicside!=null)
            publicside1.setAqi(publicside.getAqi());

        publicside1.setDeviceid(location.getDeviceId());
        publicside1.setLongitude(location.getLongitude());
        publicside1.setLatitude(location.getLatitude());
        //publicside1.setLocationName(location.getLocationName());

        publicsideRepository.delete(publicside);
        publicsideRepository.save(publicside1);
    }
    //to retrieve error log
    /*public Iterable<Errorlog> getErrorlog(Long deviceid) {
        return errorlogRepository.findAllByDeviceid(deviceid);
    }*/
/*
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
        Device device = deviceRepository.findDeviceByDeviceid(deviceid);
        device.setCode_version(code_version);
        deviceRepository.save(device);

        Response response = new Response();
        response.setResponse("FIRMWARE UPGRADED!");
        return response;
    }
*/
    // To get locations of all devices
    public Iterable<Location> getlocations() {
        return locationRepository.findAll();
    }

    // To get all records in public side table
    public Iterable<Publicside> getAllPublicside() {
        return publicsideRepository.findAll();
    }

    // To retrieve all previoue data values of AQI for a particular device
    public Iterable<Partnerpreviousaqi> findAllPartnerPreviousAqi(Long deviceid) {
        return partnerpreviousaqiRepository.findAllByDeviceid(deviceid);
    }

}
