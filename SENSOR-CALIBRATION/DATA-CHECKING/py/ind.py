class AV:
    LENGTH: int = 6
    TIMESTAMP: int = 0
    AQI: int = 1
    PM25: int = 2
    PM10: int = 3
    TEMPERATURE: int = 4
    HUMIDITY: int = 5


# Index of raw air visual data
class RAV:
    LENGTH = 14
    DATE = 0
    TIME = 1
    TIMESTAMP = 2
    PM2_5 = 3
    AQI_US = 4
    AQI_CN = 5
    PM10 = 6
    OUTDOOR_AQI_US = 7
    OUTDOOR_AQI_CN = 8
    TEMPERATURE_C = 9
    TEMPERATURE_F = 10
    HUMIDITY_RH = 11
    CO2_PPM = 12
    VOC_PPB = 13


# PMS3003: Data Index
class PMS:
    LENGTH = 7
    SP1 = 0
    SP25 = 1
    SP10 = 2
    AE1 = 3
    AE25 = 4
    AE10 = 5
    TIMESTAMP = 6
