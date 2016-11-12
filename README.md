Pi MagicMirror
=======
An application to display a GUI behind a observation mirror on an Raspberry Pi. Note: this is an early version, as it might break for other settings than mine. 

Some configuration, like changing access token and stuff is needed to run it yourself. Check out this version https://github.com/jakkra/MagicMirrorAPI of it for an easy to set up MagicMirror, but with less functionality.

## Info
At the moment it supports PIR motion detector and DS18B20 temperature sensor.
It displays upcoming buses for some location in Skåne with Skånetrafiken. 
News are pulled from Yahoo News and New York Times, Weather from from SMHI.

The motion sensor logs movement to my [server](https://github.com/jakkra/OneBackendToRuleThemAll) which then can notify my [app](https://github.com/jakkra/OneAppToRuleThemAll) 

Voice recognition, from eiher Google Cloud Speech or Sphinx (Sphinx won't work atm, since all parsing is done in Swedish, Sphinx is english only) which can:
  - Control Philips Hue Lights.
  - Create Reminders on my backend [server](https://github.com/jakkra/OneBackendToRuleThemAll), parses the voice in a "smart" way.
  - Change the source of the news that is displayed.
  - Ask when the next bus is leaving, answer will be read back to you.
  - Hide and show different information on the screen.
  
![Mirror](pics/mirror.jpg)


## TODO
- Make it compatible with Google Cloud Speech beta. Changed where made when they went from alpha to beta, which I have not fixed yet.
- Remove old code that's not used anymore.

## Installation
TBA

pr      mvn install:install-file -Dfile=/home/pi/Documents/MagicMirror/libs/huesdkresources-1.0.jar -DgroupId=com.philips.lightning -DartifactId=huesdkresources -Dversion=1.0 -Dpackaging=jar
[Ds18b20 temperature sensor installation](https://learn.adafruit.com/adafruits-raspberry-pi-lesson-11-ds18b20-temperature-sensing/hardware)

[PIR motion sensor of this type](https://learn.adafruit.com/pir-passive-infrared-proximity-motion-sensor)

[Voice recognition with Pocketsphinx](https://github.com/cmusphinx/pocketsphinx)


##Licence
[Licence](http://www.apache.org/licenses/LICENSE-2.0)
